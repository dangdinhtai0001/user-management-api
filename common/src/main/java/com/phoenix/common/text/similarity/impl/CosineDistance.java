package com.phoenix.common.text.similarity.impl;

import com.phoenix.common.text.similarity.Counter;
import com.phoenix.common.text.similarity.EditDistance;
import com.phoenix.common.text.similarity.Tokenizer;

import java.util.Map;

/**
 * Measures the cosine distance between two character sequences.
 *
 * <p>It utilizes the {@link CosineSimilarity} to compute the distance. Character sequences
 * are converted into vectors through a simple tokenizer that works with a regular expression
 * to split words in a sentence.</p>
 *
 * <p>
 * For further explanation about Cosine Similarity and Cosine Distance, refer to
 * http://en.wikipedia.org/wiki/Cosine_similarity.
 * </p>
 *
 * @see CosineSimilarity
 */
public class CosineDistance implements EditDistance<Double> {
    /**
     * Tokenizer used to convert the character sequence into a vector.
     */
    private final Tokenizer<CharSequence> tokenizer = new RegexTokenizer();

    /**
     * Cosine similarity.
     */
    private final CosineSimilarity cosineSimilarity = new CosineSimilarity();

    @Override
    public Double apply(final CharSequence left, final CharSequence right) {
        final CharSequence[] leftTokens = tokenizer.tokenize(left);
        final CharSequence[] rightTokens = tokenizer.tokenize(right);

        final Map<CharSequence, Integer> leftVector = Counter.of(leftTokens);
        final Map<CharSequence, Integer> rightVector = Counter.of(rightTokens);
        final double similarity = cosineSimilarity.cosineSimilarity(leftVector, rightVector);
        return 1.0 - similarity;
    }
}
