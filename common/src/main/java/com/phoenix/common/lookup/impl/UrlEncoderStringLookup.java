package com.phoenix.common.lookup.impl;

import com.phoenix.common.exceptions.IllegalArgumentExceptions;
import com.phoenix.common.lookup.AbstractStringLookup;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 *  Encodes URL Strings using the UTF-8 encoding.
 */
public class UrlEncoderStringLookup  extends AbstractStringLookup {
    /**
     * Defines the singleton for this class.
     */
    static final UrlEncoderStringLookup INSTANCE = new UrlEncoderStringLookup();

    /**
     * This ctor is not private to allow Mockito spying.
     */
    UrlEncoderStringLookup() {
        // empty
    }

    String encode(final String key, final String enc) throws UnsupportedEncodingException {
        return URLEncoder.encode(key, enc);
    }

    @Override
    public String lookup(final String key) {
        if (key == null) {
            return null;
        }
        final String enc = StandardCharsets.UTF_8.name();
        try {
            return encode(key, enc);
        } catch (final UnsupportedEncodingException e) {
            // Can't happen since UTF-8 is required by the Java specification.
            throw IllegalArgumentExceptions.format(e, "%s: source=%s, encoding=%s", e, key, enc);
        }
    }
}
