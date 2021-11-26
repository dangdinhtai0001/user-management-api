package com.phoenix.common.lookup.impl;

import com.phoenix.common.lookup.AbstractStringLookup;
import com.phoenix.common.lookup.InetAddressKeys;

import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * Looks up keys related to DNS entries like host name, canonical host name, host address.
 */
public class DnsStringLookup extends AbstractStringLookup {
    /**
     * Defines the singleton for this class.
     */
    static final DnsStringLookup INSTANCE = new DnsStringLookup();

    /**
     * No need to build instances for now.
     */
    private DnsStringLookup() {
        // empty
    }

    /**
     * Looks up the DNS value of the key.
     *
     * @param key the key to be looked up, may be null
     * @return The DNS value.
     */
    @Override
    public String lookup(final String key) {
        if (key == null) {
            return null;
        }
        final String[] keys = key.trim().split("\\|");
        final int keyLen = keys.length;
        final String subKey = keys[0].trim();
        final String subValue = keyLen < 2 ? key : keys[1].trim();
        try {
            final InetAddress inetAddress = InetAddress.getByName(subValue);
            switch (subKey) {
                case InetAddressKeys.KEY_NAME:
                    return inetAddress.getHostName();
                case InetAddressKeys.KEY_CANONICAL_NAME:
                    return inetAddress.getCanonicalHostName();
                default:
                    return inetAddress.getHostAddress();
            }
        } catch (final UnknownHostException e) {
            return null;
        }
    }
}
