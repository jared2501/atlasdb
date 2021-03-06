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
package com.palantir.atlasdb.persister;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Throwables;
import com.palantir.atlasdb.persist.api.Persister;

/**
 * A {@link Persister} that uses an {@link ObjectMapper} to serialize and deserialize objects
 * of type {@code T}.
 */
public abstract class JacksonPersister<T> implements Persister<T> {

    private final Class<T> typeRef;
    private final ObjectMapper mapper;

    public JacksonPersister(Class<T> typeRef, ObjectMapper mapper) {
        this.typeRef = typeRef;
        this.mapper = mapper;
    }

    @Override
    public final T hydrateFromBytes(byte[] input) {
        try {
            return mapper.readValue(input, typeRef);
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    @Override
    public final byte[] persistToBytes(T value) {
        try {
            return mapper.writeValueAsBytes(value);
        } catch (JsonProcessingException e) {
            throw Throwables.propagate(e);
        }
    }

    @Override
    public final Class<T> getPersistingClassType() {
        return typeRef;
    }

}
