package com.phoenix.common.lookup.impl;

import com.phoenix.common.exceptions.IllegalArgumentExceptions;
import com.phoenix.common.lookup.AbstractStringLookup;
import org.apache.logging.log4j.core.util.datetime.FastDateFormat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Formats the current date with the format given in the key in a format compatible with
 * {@link SimpleDateFormat}.
 */
public final class DateStringLookup extends AbstractStringLookup {
    /**
     * Defines the singleton for this class.
     */
    static final DateStringLookup INSTANCE = new DateStringLookup();

    /**
     * No need to build instances for now.
     */
    private DateStringLookup() {
        // empty
    }

    /**
     * Formats the given {@code date} long with the given {@code format}.
     *
     * @param dateMillis the date to format
     * @param format     the format string for {@link SimpleDateFormat}.
     * @return The formatted date
     */
    private String formatDate(final long dateMillis, final String format) {
        FastDateFormat dateFormat = null;
        if (format != null) {
            try {
                dateFormat = FastDateFormat.getInstance(format);
            } catch (final Exception ex) {
                throw IllegalArgumentExceptions.format(ex, "Invalid date format: [%s]", format);
            }
        }
        if (dateFormat == null) {
            dateFormat = FastDateFormat.getInstance();
        }
        return dateFormat.format(new Date(dateMillis));
    }

    /**
     * Formats the current date with the format given in the key in a format compatible with
     * {@link SimpleDateFormat}.
     *
     * @param key the format to use. If null, the default {@link DateFormat} will be used.
     * @return The value of the environment variable.
     */
    @Override
    public String lookup(final String key) {
        return formatDate(System.currentTimeMillis(), key);
    }
}
