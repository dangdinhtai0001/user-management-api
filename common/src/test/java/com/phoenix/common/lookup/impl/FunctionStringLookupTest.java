package com.phoenix.common.lookup.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

class FunctionStringLookupTest {
    @Test
    public void testConcurrentHashMapNull() {
        Assertions.assertNull(FunctionStringLookup.on(new ConcurrentHashMap<>()).lookup(null));
    }

    @Test
    public void testHashMapNull() {
        Assertions.assertNull(FunctionStringLookup.on(new HashMap<>()).lookup(null));
    }

    @Test
    public void testNullFunction() {
        Assertions.assertNull(FunctionStringLookup.on((Function<String, Object>) null).lookup(null));
    }

    @Test
    public void testOne() {
        final String key = "key";
        final String value = "value";
        final Map<String, String> map = new HashMap<>();
        map.put(key, value);
        Assertions.assertEquals(value, FunctionStringLookup.on(map).lookup(key));
    }

    @Test
    public void testToString() {
        // does not blow up and gives some kind of string.
        Assertions.assertFalse(FunctionStringLookup.on(new HashMap<>()).toString().isEmpty());
    }

}