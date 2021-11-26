package com.phoenix.common.text.similarity;

import java.util.HashMap;
import java.util.Map;

/**
 * Java implementation of Python's collections Counter module.
 *
 * <p>It counts how many times each element provided occurred in an array and
 * returns a dict with the element as key and the count as value.</p>
 *
 * @see <a href="https://docs.python.org/dev/library/collections.html#collections.Counter">
 * https://docs.python.org/dev/library/collections.html#collections.Counter</a>
 *
 */
public final class Counter {
    /**
     * Hidden constructor.
     */
    private Counter() {
    }

    /**
     * It counts how many times each element provided occurred in an array and
     * returns a dict with the element as key and the count as value.
     *
     * @param tokens array of tokens
     * @return dict, where the elements are key, and the count the value
     */
    public static Map<CharSequence, Integer> of(final CharSequence[] tokens) {
        final Map<CharSequence, Integer> innerCounter = new HashMap<>();
        for (final CharSequence token : tokens) {
            if (innerCounter.containsKey(token)) {
                int value = innerCounter.get(token);
                innerCounter.put(token, ++value);
            } else {
                innerCounter.put(token, 1);
            }
        }
        return innerCounter;
    }
}
