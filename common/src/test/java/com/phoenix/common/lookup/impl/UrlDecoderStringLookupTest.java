package com.phoenix.common.lookup.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

class UrlDecoderStringLookupTest {
    private static final String DATA = "Hello World!";

    @Test
    public void testAllPercent() {
        Assertions.assertEquals(DATA, UrlDecoderStringLookup.INSTANCE.lookup("Hello%20World%21"));
    }

    @Test
    public void testExceptionGettingString() throws UnsupportedEncodingException {
        final UrlDecoderStringLookup mockLookup = spy(UrlDecoderStringLookup.class);
        when(mockLookup.decode(DATA, StandardCharsets.UTF_8.displayName()))
                .thenThrow(UnsupportedEncodingException.class);
        assertThrows(IllegalArgumentException.class, () -> mockLookup.lookup(DATA));
    }

    @Test
    public void testExclamation() {
        Assertions.assertEquals(DATA, UrlDecoderStringLookup.INSTANCE.lookup("Hello%20World!"));
    }

    @Test
    public void testNull() {
        Assertions.assertNull(UrlDecoderStringLookup.INSTANCE.lookup(null));
    }

    @Test
    public void testPlus() {
        Assertions.assertEquals(DATA, UrlDecoderStringLookup.INSTANCE.lookup("Hello+World!"));
    }

    @Test
    public void testToString() {
        // does not blow up and gives some kind of string.
        Assertions.assertFalse(UrlDecoderStringLookup.INSTANCE.toString().isEmpty());
    }

}