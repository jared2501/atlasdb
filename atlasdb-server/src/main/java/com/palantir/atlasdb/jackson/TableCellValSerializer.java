/**
 * Copyright 2015 Palantir Technologies
 *
 * Licensed under the BSD-3 License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://opensource.org/licenses/BSD-3-Clause
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.palantir.atlasdb.jackson;

import java.io.IOException;
import java.util.Map.Entry;
import java.util.Set;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.google.common.base.Preconditions;
import com.palantir.atlasdb.api.TableCellVal;
import com.palantir.atlasdb.encoding.PtBytes;
import com.palantir.atlasdb.impl.TableMetadataCache;
import com.palantir.atlasdb.keyvalue.api.Cell;
import com.palantir.atlasdb.table.description.ColumnMetadataDescription;
import com.palantir.atlasdb.table.description.DynamicColumnDescription;
import com.palantir.atlasdb.table.description.NamedColumnDescription;
import com.palantir.atlasdb.table.description.TableMetadata;

public class TableCellValSerializer extends StdSerializer<TableCellVal> {
    private static final long serialVersionUID = 1L;
    private final TableMetadataCache metadataCache;

    public TableCellValSerializer(TableMetadataCache metadataCache) {
        super(TableCellVal.class);
        this.metadataCache = metadataCache;
    }

    @Override
    public void serialize(TableCellVal value,
                          JsonGenerator jgen,
                          SerializerProvider provider) throws IOException, JsonGenerationException {
        TableMetadata metadata = metadataCache.getMetadata(value.getTableName());
        Preconditions.checkNotNull(metadata, "Unknown table %s", value.getTableName());
        jgen.writeStartObject(); {
            jgen.writeStringField("table", value.getTableName());
            jgen.writeArrayFieldStart("data"); {
                for (Entry<Cell, byte[]> result : value.getResults().entrySet()) {
                    serialize(jgen, metadata, result);
                }
            } jgen.writeEndArray();
        } jgen.writeEndObject();
    }

    private static void serialize(JsonGenerator jgen,
                                  TableMetadata metadata,
                                  Entry<Cell, byte[]> result) throws IOException, JsonGenerationException {
        Cell cell = result.getKey();
        byte[] row = cell.getRowName();
        byte[] col = cell.getColumnName();
        byte[] val = result.getValue();

        jgen.writeStartObject(); {
            AtlasSerializers.serializeRow(jgen, metadata.getRowMetadata(), row);

            ColumnMetadataDescription columns = metadata.getColumns();
            if (columns.hasDynamicColumns()) {
                DynamicColumnDescription dynamicColumn = columns.getDynamicColumn();
                AtlasSerializers.serializeDynamicColumn(jgen, dynamicColumn, col);
                jgen.writeFieldName("val");
                AtlasSerializers.serializeVal(jgen, dynamicColumn.getValue(), val);
            } else {
                String shortName = PtBytes.toString(col);
                Set<NamedColumnDescription> namedColumns = columns.getNamedColumns();
                for (NamedColumnDescription description : namedColumns) {
                    if (shortName.equals(description.getShortName())) {
                        AtlasSerializers.serializeNamedCol(jgen, description, val);
                        break;
                    }
                }
            }
        } jgen.writeEndObject();
    }
}
