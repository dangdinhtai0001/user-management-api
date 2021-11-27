package com.phoenix.common.text.similarity.impl;

import com.phoenix.common.text.similarity.Tokenizer;
import com.phoenix.common.util.ArrayUtils;
import com.phoenix.common.util.StringUtils;
import com.phoenix.common.util.Validate;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple word tokenizer that utilizes regex to find words. It applies a regex
 * {@code (\w)+} over the input text to extract words from a given character
 * sequence.
 *
 */
public class RegexTokenizer implements Tokenizer<CharSequence> {
    /** The whitespace pattern. */
    private static final Pattern PATTERN = Pattern.compile("(\\w)+");

    /**
     * {@inheritDoc}
     *
     * @throws IllegalArgumentException if the input text is blank
     */
    @Override
    public CharSequence[] tokenize(final CharSequence text) {
        Validate.isTrue(StringUtils.isNotBlank(text), "Invalid text");
        final Matcher matcher = PATTERN.matcher(text);
        final List<String> tokens = new ArrayList<>();
        while (matcher.find()) {
            tokens.add(matcher.group(0));
        }
        return tokens.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
    }

}
