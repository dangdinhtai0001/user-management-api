package com.phoenix.common.lookup.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateStringLookupTest {
    @Test
    public void testBadFormat() {
        assertThrows(IllegalArgumentException.class,
                () -> DateStringLookup.INSTANCE.lookup("this-is-a-bad-format-dontcha-know"));
    }

    @Test
    public void testDefault() throws ParseException {
        final String formatted = DateStringLookup.INSTANCE.lookup(null);
        DateFormat.getInstance().parse(formatted); // throws ParseException
//        System.out.println(formatted);
    }

    @Test
    public void testFormat() {
        final String format = "yyyy-MM-dd";
        final String value = DateStringLookup.INSTANCE.lookup(format);
        // System.out.println(value);
        assertNotNull(value, "No Date");
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        final String today = simpleDateFormat.format(new Date());
        assertEquals(value, today);
//        System.out.println(value);
    }

    @Test
    public void testToString() {
        // does not blow up and gives some kind of string.
        Assertions.assertFalse(DateStringLookup.INSTANCE.toString().isEmpty());
    }

}