package com.palantir.leader.proxy;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.google.common.base.Optional;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.net.HostAndPort;
import com.palantir.leader.LeaderElectionService;

public class AwaitingLeadershipProxyTest {

    @Test
    public void shouldAllowObjectMethodsWhenLeading() throws Exception {
        Runnable mockRunnable = mock(Runnable.class);
        Supplier<Runnable> delegateSupplier = Suppliers.ofInstance(mockRunnable);
        LeaderElectionService mockLeader = mock(LeaderElectionService.class);

        when(mockLeader.getSuspectedLeaderInMemory()).thenReturn(Optional.<HostAndPort>absent());
        when(mockLeader.isStillLeading(any(LeaderElectionService.LeadershipToken.class)))
                .thenReturn(LeaderElectionService.StillLeadingStatus.LEADING);

        Runnable proxy = AwaitingLeadershipProxy.newProxyInstance(Runnable.class, delegateSupplier, mockLeader);

        assertNotNull(proxy.hashCode());
        assertThat(proxy, equalTo(proxy));
        assertThat(proxy.equals(null), equalTo(false));
        assertThat(proxy.toString(), startsWith("com.palantir.leader.proxy.AwaitingLeadershipProxy@"));
    }

    @Test
    public void shouldAllowObjectMethodsWhenNotLeading() throws Exception {
        Runnable mockRunnable = mock(Runnable.class);
        Supplier<Runnable> delegateSupplier = Suppliers.ofInstance(mockRunnable);
        LeaderElectionService mockLeader = mock(LeaderElectionService.class);

        when(mockLeader.getSuspectedLeaderInMemory()).thenReturn(Optional.<HostAndPort>absent());
        when(mockLeader.isStillLeading(any(LeaderElectionService.LeadershipToken.class)))
                .thenReturn(LeaderElectionService.StillLeadingStatus.NOT_LEADING);

        Runnable proxy = AwaitingLeadershipProxy.newProxyInstance(Runnable.class, delegateSupplier, mockLeader);

        assertNotNull(proxy.hashCode());
        assertThat(proxy.equals(proxy), equalTo(true));
        assertThat(proxy.equals(null), equalTo(false));
        assertThat(proxy.toString(), startsWith("com.palantir.leader.proxy.AwaitingLeadershipProxy@"));
    }
}
