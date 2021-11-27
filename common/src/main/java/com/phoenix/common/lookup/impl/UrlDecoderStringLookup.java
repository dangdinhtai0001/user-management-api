package com.phoenix.common.lookup.impl;

import com.phoenix.common.exceptions.IllegalArgumentExceptions;
import com.phoenix.common.lookup.AbstractStringLookup;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * Decodes URL Strings using the UTF-8 encoding.
 */
public class UrlDecoderStringLookup extends AbstractStringLookup {
    /**
     * Defines the singleton for this class.
     */
    static final UrlDecoderStringLookup INSTANCE = new UrlDecoderStringLookup();

    /**
     * This ctor is not private to allow Mockito spying.
     */
    UrlDecoderStringLookup() {
        // empty
    }

    String decode(final String key, final String enc) throws UnsupportedEncodingException {
        return URLDecoder.decode(key, enc);
    }

    @Override
    public String lookup(final String key) {
        if (key == null) {
            return null;
        }
        final String enc = StandardCharsets.UTF_8.name();
        try {
            return decode(key, enc);
        } catch (final UnsupportedEncodingException e) {
            // Can't happen since UTF-8 is required by the Java specification.
            throw IllegalArgumentExceptions.format(e, "%s: source=%s, encoding=%s", e, key, enc);
        }
    }
}
