package com.phoenix.common.lookup.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class UrlEncoderStringLookupTest {

    private static final String DATA = "Hello+World%21";

    @Test
    public void test() {
        Assertions.assertEquals(DATA, UrlEncoderStringLookup.INSTANCE.lookup("Hello World!"));
    }

    @Test
    public void testExceptionGettingString() throws UnsupportedEncodingException {
        final UrlEncoderStringLookup mockLookup = spy(UrlEncoderStringLookup.class);
        when(mockLookup.encode(DATA, StandardCharsets.UTF_8.displayName()))
                .thenThrow(UnsupportedEncodingException.class);
        assertThrows(IllegalArgumentException.class, () -> mockLookup.lookup(DATA));
    }

    @Test
    public void testNull() {
        Assertions.assertNull(UrlEncoderStringLookup.INSTANCE.lookup(null));
    }

    @Test
    public void testToString() {
        // does not blow up and gives some kind of string.
        Assertions.assertFalse(UrlEncoderStringLookup.INSTANCE.toString().isEmpty());
    }

}