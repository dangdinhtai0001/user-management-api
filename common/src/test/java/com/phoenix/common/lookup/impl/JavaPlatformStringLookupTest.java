package com.phoenix.common.lookup.impl;

import com.phoenix.common.util.ArrayUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JavaPlatformStringLookupTest {
    @Test
    public void testBadKey() {
        assertThrows(IllegalArgumentException.class, () -> JavaPlatformStringLookup.INSTANCE.lookup("BADKEY"));
    }

    @Test
    void testMain() {
        JavaPlatformStringLookup.main(ArrayUtils.EMPTY_STRING_ARRAY);
    }

    @Test
    public void testNull() {
        Assertions.assertNull(JavaPlatformStringLookup.INSTANCE.lookup(null));
    }

    @Test
    public void testToString() {
        // does not blow up and gives some kind of string.
        Assertions.assertFalse(JavaPlatformStringLookup.INSTANCE.toString().isEmpty());
    }

    @Test
    public void testVm() {
        final String key = "vm";
        assertTrue(JavaPlatformStringLookup.INSTANCE.lookup(key).contains(System.getProperty("java.vm.name")));
    }
}