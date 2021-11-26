package com.phoenix.common.lookup;

import java.net.InetAddress;

public final class InetAddressKeys {
    /**
     * Constants for referring to {@link InetAddress#getAddress()}.
     */
    public static final String KEY_ADDRESS = "address";

    /**
     * Constants for referring to {@link InetAddress#getCanonicalHostName()}.
     */
    public static final String KEY_CANONICAL_NAME = "canonical-name";

    /**
     * Constants for referring to {@link InetAddress#getHostName()}.
     */
    public static final String KEY_NAME = "name";

    private InetAddressKeys() {
        // noop
    }
}
