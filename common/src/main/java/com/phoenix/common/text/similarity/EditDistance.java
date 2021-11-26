package com.phoenix.common.text.similarity;

/**
 * Interface for <a href="http://en.wikipedia.org/wiki/Edit_distance">Edit Distances</a>.
 *
 * <p>
 * An edit distance is a formal metric on the Kleene closure ({@code X<sup>*</sup>}) over an
 * alphabet ({@code X}). Note, that a <a href="https://en.wikipedia.org/wiki/Metric_(mathematics)">metric</a>
 * on a set {@code S} is a function {@code d: [S * S] -&gt; [0, INFINITY)} such
 * that the following hold for {@code x,y,z} in
 * the set {@code S}:
 * </p>
 * <ul>
 *     <li>{@code d(x,y) &gt;= 0}, non-negativity or separation axiom</li>
 *     <li>{@code d(x,y) == 0}, if and only if, {@code x == y}</li>
 *     <li>{@code d(x,y) == d(y,x)}, symmetry, and</li>
 *     <li>{@code d(x,z) &lt;=  d(x,y) + d(y,z)}, the triangle inequality</li>
 * </ul>
 *
 *
 * <p>
 * This is a BiFunction&lt;CharSequence, CharSequence, R&gt;.
 * The {@code apply} method
 * accepts a pair of {@link CharSequence} parameters
 * and returns an {@code R} type similarity score.
 * </p>
 *
 * @param <R> The type of similarity score unit used by this EditDistance.
 */
public interface EditDistance<R> extends SimilarityScore<R> {

    /**
     * Compares two CharSequences.
     *
     * @param left the first CharSequence
     * @param right the second CharSequence
     * @return The similarity score between two CharSequences
     */
    @Override
    R apply(CharSequence left, CharSequence right);

}
