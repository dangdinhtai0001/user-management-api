package com.phoenix.common.exceptions;

/**
        * Shorthands creating {@link IllegalArgumentException} instances using formatted strings.
        *
        */
public final class IllegalArgumentExceptions {

    /**
     * Creates an {@link IllegalArgumentException} with a message
     * formatted with {@link String#format(String,Object...)}.
     *
     * @param format See {@link String#format(String,Object...)}
     * @param args See {@link String#format(String,Object...)}
     * @return an {@link IllegalArgumentException} with a message formatted with {@link String#format(String,Object...)}
     */
    public static IllegalArgumentException format(final String format, final Object... args) {
        return new IllegalArgumentException(String.format(format, args));
    }

    /**
     * Creates an {@link IllegalArgumentException} with a message
     * formatted with {@link String#format(String,Object...)}.
     *
     * @param t the throwable cause
     * @param format See {@link String#format(String,Object...)}
     * @param args See {@link String#format(String,Object...)}
     * @return an {@link IllegalArgumentException} with a message formatted with {@link String#format(String,Object...)}
     */
    public static IllegalArgumentException format(final Throwable t, final String format, final Object... args) {
        return new IllegalArgumentException(String.format(format, args), t);
    }

    /**
     * No need to build instances.
     */
    private IllegalArgumentExceptions() {
        // empty
    }
}
