package com.phoenix.common.lookup.impl;

public final class StringLookupFactory {
    /**
     * Defines the singleton for this class.
     */
    public static final StringLookupFactory INSTANCE = new StringLookupFactory();

    /**
     * Defines the FunctionStringLookup singleton for looking up system properties.
     */
    static final FunctionStringLookup<String> INSTANCE_SYSTEM_PROPERTIES = FunctionStringLookup.on(System::getProperty);

    /**
     * Gets the given system property.
     *
     * @param name a system property name.
     * @return a system property value.
     */
    private String getSystemProperty(final String name) {
        return StringLookupFactory.INSTANCE_SYSTEM_PROPERTIES.lookup(name);
    }
}
