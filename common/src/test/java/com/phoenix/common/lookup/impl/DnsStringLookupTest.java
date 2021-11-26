package com.phoenix.common.lookup.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

class DnsStringLookupTest {

    @Test
    public void testAddressFromHostAddress() throws UnknownHostException {
        final InetAddress localHost = InetAddress.getLocalHost();
        Assertions.assertEquals(localHost.getHostAddress(),
                DnsStringLookup.INSTANCE.lookup("address|" + localHost.getHostAddress()));
        System.out.println(DnsStringLookup.INSTANCE.lookup("address|" + localHost.getHostAddress()));
    }

    @Test
    public void testAddressFromHostName() throws UnknownHostException {
        final InetAddress localHost = InetAddress.getLocalHost();
        Assertions.assertEquals(localHost.getHostAddress(),
                DnsStringLookup.INSTANCE.lookup("address|" + localHost.getHostName()));
        System.out.println(DnsStringLookup.INSTANCE.lookup("address|" + localHost.getHostName()));
    }

    @Test
    public void testCanonicalNameFromHostAddress() throws UnknownHostException {
        final InetAddress localHost = InetAddress.getLocalHost();
        Assertions.assertEquals(localHost.getCanonicalHostName(),
                DnsStringLookup.INSTANCE.lookup("canonical-name|" + localHost.getHostAddress()));
        System.out.println(DnsStringLookup.INSTANCE.lookup("canonical-name|" + localHost.getHostAddress()));
    }

    @Test
    public void testCanonicalNameFromHostName() throws UnknownHostException {
        final InetAddress localHost = InetAddress.getLocalHost();
        Assertions.assertEquals(localHost.getCanonicalHostName(),
                DnsStringLookup.INSTANCE.lookup("canonical-name|" + localHost.getHostName()));
        System.out.println(DnsStringLookup.INSTANCE.lookup("canonical-name|" + localHost.getHostName()));
    }

    @Test
    public void testName() throws UnknownHostException {
        final String address = InetAddress.getLocalHost().getHostAddress();
        final InetAddress[] localHostAll = InetAddress.getAllByName(address);
        boolean matched = false;
        for (final InetAddress localHost : localHostAll) {
            if (localHost.getHostName().equals(DnsStringLookup.INSTANCE.lookup("name|" + address + ""))) {
                matched = true;
            }
        }
        Assertions.assertTrue(matched);
        System.out.println(DnsStringLookup.INSTANCE.lookup("name|" + address + ""));
    }

    @Test
    public void testNull() {
        Assertions.assertNull(DnsStringLookup.INSTANCE.lookup(null));
    }

    @Test
    public void testToString() {
        // does not blow up and gives some kind of string.
        Assertions.assertFalse(DnsStringLookup.INSTANCE.toString().isEmpty());
    }

}