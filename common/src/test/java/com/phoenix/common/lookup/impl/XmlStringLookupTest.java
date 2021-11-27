package com.phoenix.common.lookup.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class XmlStringLookupTest {
    private static final String DOC_PATH = "src/test/resources/document.xml";

    @Test
    public void testBadXPath() {
        assertThrows(IllegalArgumentException.class, () -> XmlStringLookup.INSTANCE.lookup("docName"));
    }

    @Test
    public void testMissingXPath() {
        assertThrows(IllegalArgumentException.class, () -> XmlStringLookup.INSTANCE.lookup(DOC_PATH + ":" + "!JUNK!"));
    }

    @Test
    public void testNull() {
        Assertions.assertNull(XmlStringLookup.INSTANCE.lookup(null));
    }

    @Test
    public void testOne() {
        final String xpath = "/root/path/to/node";
        Assertions.assertEquals("Hello World!", XmlStringLookup.INSTANCE.lookup(DOC_PATH + ":" + xpath));
    }

    @Test
    public void testToString() {
        // does not blow up and gives some kind of string.
        Assertions.assertFalse(XmlStringLookup.INSTANCE.toString().isEmpty());
    }


}