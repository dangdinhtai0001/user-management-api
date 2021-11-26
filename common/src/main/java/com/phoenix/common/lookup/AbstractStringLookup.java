package com.phoenix.common.lookup;

public abstract class AbstractStringLookup implements StringLookup {
    /**
     * The default split char.
     */
    protected static final char SPLIT_CH = ':';

    /**
     * The default split string.
     */
    protected static final String SPLIT_STR = String.valueOf(SPLIT_CH);

    /**
     * Creates a lookup key for a given file and key.
     */
    static String toLookupKey(final String left, final String right) {
        return toLookupKey(left, SPLIT_STR, right);
    }

    /**
     * Creates a lookup key for a given file and key.
     */
    static String toLookupKey(final String left, final String separator, final String right) {
        return left + separator + right;
    }
}
