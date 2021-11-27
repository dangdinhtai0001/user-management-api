package com.phoenix.common.text.similarity;

/**
 * A tokenizer. Can produce arrays of tokens from a given type.
 *
 * @param <T> given type
 * @since 1.0
 */
public interface Tokenizer<T> {

    /**
     * Returns an array of tokens.
     *
     * @param text input text
     * @return array of tokens
     */
    T[] tokenize(CharSequence text);

}
