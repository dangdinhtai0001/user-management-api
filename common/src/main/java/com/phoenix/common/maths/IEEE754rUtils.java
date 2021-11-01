package com.phoenix.common.maths;

import com.phoenix.common.util.Validate;

/**
 * <p>Provides IEEE-754r variants of NumberUtils methods. </p>
 *
 * <p>See: <a href="http://en.wikipedia.org/wiki/IEEE_754r">http://en.wikipedia.org/wiki/IEEE_754r</a></p>
 */
public class IEEE754rUtils {
    /**
     * <p>Returns the minimum value in an array.</p>
     *
     * @param array an array, must not be null or empty
     * @return the minimum value in the array
     * @throws NullPointerException     if {@code array} is {@code null}
     * @throws IllegalArgumentException if {@code array} is empty
     * @since 3.4 Changed signature from min(double[]) to min(double...)
     */
    public static double min(final double... array) {
        Validate.notNull(array, "array");
        Validate.isTrue(array.length != 0, "Array cannot be empty.");

        // Finds and returns min
        double min = array[0];
        for (int i = 1; i < array.length; i++) {
            min = min(array[i], min);
        }

        return min;
    }

    /**
     * <p>Returns the minimum value in an array.</p>
     *
     * @param array an array, must not be null or empty
     * @return the minimum value in the array
     * @throws NullPointerException     if {@code array} is {@code null}
     * @throws IllegalArgumentException if {@code array} is empty
     * @since 3.4 Changed signature from min(float[]) to min(float...)
     */
    public static float min(final float... array) {
        Validate.notNull(array, "array");
        Validate.isTrue(array.length != 0, "Array cannot be empty.");

        // Finds and returns min
        float min = array[0];
        for (int i = 1; i < array.length; i++) {
            min = min(array[i], min);
        }

        return min;
    }

    /**
     * <p>Gets the minimum of three {@code double} values.</p>
     *
     * <p>NaN is only returned if all numbers are NaN as per IEEE-754r. </p>
     *
     * @param a value 1
     * @param b value 2
     * @param c value 3
     * @return the smallest of the values
     */
    public static double min(final double a, final double b, final double c) {
        return min(min(a, b), c);
    }

    /**
     * <p>Gets the minimum of two {@code double} values.</p>
     *
     * <p>NaN is only returned if all numbers are NaN as per IEEE-754r. </p>
     *
     * @param a value 1
     * @param b value 2
     * @return the smallest of the values
     */
    public static double min(final double a, final double b) {
        if (Double.isNaN(a)) {
            return b;
        }
        if (Double.isNaN(b)) {
            return a;
        }
        return Math.min(a, b);
    }

    /**
     * <p>Gets the minimum of three {@code float} values.</p>
     *
     * <p>NaN is only returned if all numbers are NaN as per IEEE-754r. </p>
     *
     * @param a value 1
     * @param b value 2
     * @param c value 3
     * @return the smallest of the values
     */
    public static float min(final float a, final float b, final float c) {
        return min(min(a, b), c);
    }

    /**
     * <p>Gets the minimum of two {@code float} values.</p>
     *
     * <p>NaN is only returned if all numbers are NaN as per IEEE-754r. </p>
     *
     * @param a value 1
     * @param b value 2
     * @return the smallest of the values
     */
    public static float min(final float a, final float b) {
        if (Float.isNaN(a)) {
            return b;
        }
        if (Float.isNaN(b)) {
            return a;
        }
        return Math.min(a, b);
    }

    /**
     * <p>Returns the maximum value in an array.</p>
     *
     * @param array an array, must not be null or empty
     * @return the minimum value in the array
     * @throws NullPointerException     if {@code array} is {@code null}
     * @throws IllegalArgumentException if {@code array} is empty
     * @since 3.4 Changed signature from max(double[]) to max(double...)
     */
    public static double max(final double... array) {
        Validate.notNull(array, "array");
        Validate.isTrue(array.length != 0, "Array cannot be empty.");

        // Finds and returns max
        double max = array[0];
        for (int j = 1; j < array.length; j++) {
            max = max(array[j], max);
        }

        return max;
    }

    /**
     * <p>Returns the maximum value in an array.</p>
     *
     * @param array an array, must not be null or empty
     * @return the minimum value in the array
     * @throws NullPointerException     if {@code array} is {@code null}
     * @throws IllegalArgumentException if {@code array} is empty
     * @since 3.4 Changed signature from max(float[]) to max(float...)
     */
    public static float max(final float... array) {
        Validate.notNull(array, "array");
        Validate.isTrue(array.length != 0, "Array cannot be empty.");

        // Finds and returns max
        float max = array[0];
        for (int j = 1; j < array.length; j++) {
            max = max(array[j], max);
        }

        return max;
    }

    /**
     * <p>Gets the maximum of three {@code double} values.</p>
     *
     * <p>NaN is only returned if all numbers are NaN as per IEEE-754r. </p>
     *
     * @param a value 1
     * @param b value 2
     * @param c value 3
     * @return the largest of the values
     */
    public static double max(final double a, final double b, final double c) {
        return max(max(a, b), c);
    }

    /**
     * <p>Gets the maximum of two {@code double} values.</p>
     *
     * <p>NaN is only returned if all numbers are NaN as per IEEE-754r. </p>
     *
     * @param a value 1
     * @param b value 2
     * @return the largest of the values
     */
    public static double max(final double a, final double b) {
        if (Double.isNaN(a)) {
            return b;
        }
        if (Double.isNaN(b)) {
            return a;
        }
        return Math.max(a, b);
    }

    /**
     * <p>Gets the maximum of three {@code float} values.</p>
     *
     * <p>NaN is only returned if all numbers are NaN as per IEEE-754r. </p>
     *
     * @param a value 1
     * @param b value 2
     * @param c value 3
     * @return the largest of the values
     */
    public static float max(final float a, final float b, final float c) {
        return max(max(a, b), c);
    }

    /**
     * <p>Gets the maximum of two {@code float} values.</p>
     *
     * <p>NaN is only returned if all numbers are NaN as per IEEE-754r. </p>
     *
     * @param a value 1
     * @param b value 2
     * @return the largest of the values
     */
    public static float max(final float a, final float b) {
        if (Float.isNaN(a)) {
            return b;
        }
        if (Float.isNaN(b)) {
            return a;
        }
        return Math.max(a, b);
    }

}
