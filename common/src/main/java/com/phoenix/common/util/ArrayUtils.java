package com.phoenix.common.util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class ArrayUtils {
    /**
     * An empty immutable {@code Boolean} array.
     */
    public static final Boolean[] EMPTY_BOOLEAN_OBJECT_ARRAY = {};

    /**
     * An empty immutable {@code byte} array.
     */
    public static final byte[] EMPTY_BYTE_ARRAY = {};

    /**
     * An empty immutable {@code Byte} array.
     */
    public static final Byte[] EMPTY_BYTE_OBJECT_ARRAY = {};

    /**
     * An empty immutable {@code char} array.
     */
    public static final char[] EMPTY_CHAR_ARRAY = {};

    /**
     * An empty immutable {@code Character} array.
     */
    public static final Character[] EMPTY_CHARACTER_OBJECT_ARRAY = {};

    /**
     * An empty immutable {@code Class} array.
     */
    public static final Class<?>[] EMPTY_CLASS_ARRAY = new Class[0];

    /**
     * An empty immutable {@code double} array.
     */
    public static final double[] EMPTY_DOUBLE_ARRAY = {};

    /**
     * An empty immutable {@code Double} array.
     */
    public static final Double[] EMPTY_DOUBLE_OBJECT_ARRAY = {};

    /**
     * An empty immutable {@code Field} array.
     */
    public static final Field[] EMPTY_FIELD_ARRAY = {};

    /**
     * An empty immutable {@code float} array.
     */
    public static final float[] EMPTY_FLOAT_ARRAY = {};

    /**
     * An empty immutable {@code Float} array.
     */
    public static final Float[] EMPTY_FLOAT_OBJECT_ARRAY = {};

    /**
     * An empty immutable {@code int} array.
     */
    public static final int[] EMPTY_INT_ARRAY = {};

    /**
     * An empty immutable {@code Integer} array.
     */
    public static final Integer[] EMPTY_INTEGER_OBJECT_ARRAY = {};

    /**
     * An empty immutable {@code long} array.
     */
    public static final long[] EMPTY_LONG_ARRAY = {};

    /**
     * An empty immutable {@code Long} array.
     */
    public static final Long[] EMPTY_LONG_OBJECT_ARRAY = {};

    /**
     * An empty immutable {@code Method} array.
     */
    public static final Method[] EMPTY_METHOD_ARRAY = {};

    /**
     * An empty immutable {@code Object} array.
     */
    public static final Object[] EMPTY_OBJECT_ARRAY = {};

    /**
     * An empty immutable {@code short} array.
     */
    public static final short[] EMPTY_SHORT_ARRAY = {};

    /**
     * An empty immutable {@code Short} array.
     */
    public static final Short[] EMPTY_SHORT_OBJECT_ARRAY = {};

    /**
     * An empty immutable {@code String} array.
     */
    public static final String[] EMPTY_STRING_ARRAY = {};

    /**
     * An empty immutable {@code Throwable} array.
     */
    public static final Throwable[] EMPTY_THROWABLE_ARRAY = {};

    /**
     * An empty immutable {@code Type} array.
     */
    public static final Type[] EMPTY_TYPE_ARRAY = {};

    /**
     * An empty immutable {@code boolean} array.
     */
    public static final boolean[] EMPTY_BOOLEAN_ARRAY = {};

    // nullToEmpty - BEGIN
    //---------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------

    /**
     * <p>
     * Phương thức này trả về một mảng trống cho một mảng đầu vào {@code null}.
     * </p>
     * <p>
     * Là một kỹ thuật tối ưu hóa bộ nhớ, một mảng {@code null} được truyền vào sẽ bị ghi đè bởi các tham số
     * mặc định trong class này
     * </p>
     *
     * @param array array cần kiểm tra {@code null} or empty
     * @return Một array tương tự, {@code public static} empty array nếu {@code null} hoặc tham số đầu vào là empty.
     */
    public static boolean[] nullToEmpty(final boolean[] array) {
        if (isEmpty(array)) {
            return EMPTY_BOOLEAN_ARRAY;
        }
        return array;
    }

    /**
     * <p>
     * Phương thức này trả về một mảng trống cho một mảng đầu vào {@code null}.
     * </p>
     * <p>
     * Là một kỹ thuật tối ưu hóa bộ nhớ, một mảng {@code null} được truyền vào sẽ bị ghi đè bởi các tham số
     * mặc định trong class này
     * </p>
     *
     * @param array array cần kiểm tra {@code null} or empty
     * @return Một array tương tự, {@code public static} empty array nếu {@code null} hoặc tham số đầu vào là empty.
     */
    public static byte[] nullToEmpty(final byte[] array) {
        if (isEmpty(array)) {
            return EMPTY_BYTE_ARRAY;
        }
        return array;
    }

    /**
     * <p>
     * Phương thức này trả về một mảng trống cho một mảng đầu vào {@code null}.
     * </p>
     * <p>
     * Là một kỹ thuật tối ưu hóa bộ nhớ, một mảng {@code null} được truyền vào sẽ bị ghi đè bởi các tham số
     * mặc định trong class này
     * </p>
     *
     * @param array array cần kiểm tra {@code null} or empty
     * @return Một array tương tự, {@code public static} empty array nếu {@code null} hoặc tham số đầu vào là empty.
     */
    public static Byte[] nullToEmpty(final Byte[] array) {
        if (isEmpty(array)) {
            return EMPTY_BYTE_OBJECT_ARRAY;
        }
        return array;
    }

    /**
     * <p>
     * Phương thức này trả về một mảng trống cho một mảng đầu vào {@code null}.
     * </p>
     * <p>
     * Là một kỹ thuật tối ưu hóa bộ nhớ, một mảng {@code null} được truyền vào sẽ bị ghi đè bởi các tham số
     * mặc định trong class này
     * </p>
     *
     * @param array array cần kiểm tra {@code null} or empty
     * @return Một array tương tự, {@code public static} empty array nếu {@code null} hoặc tham số đầu vào là empty.
     */
    public static char[] nullToEmpty(final char[] array) {
        if (isEmpty(array)) {
            return EMPTY_CHAR_ARRAY;
        }
        return array;
    }

    /**
     * <p>
     * Phương thức này trả về một mảng trống cho một mảng đầu vào {@code null}.
     * </p>
     * <p>
     * Là một kỹ thuật tối ưu hóa bộ nhớ, một mảng {@code null} được truyền vào sẽ bị ghi đè bởi các tham số
     * mặc định trong class này
     * </p>
     *
     * @param array array cần kiểm tra {@code null} or empty
     * @return Một array tương tự, {@code public static} empty array nếu {@code null} hoặc tham số đầu vào là empty.
     */
    public static Character[] nullToEmpty(final Character[] array) {
        if (isEmpty(array)) {
            return EMPTY_CHARACTER_OBJECT_ARRAY;
        }
        return array;
    }

    /**
     * <p>
     * Phương thức này trả về một mảng trống cho một mảng đầu vào {@code null}.
     * </p>
     * <p>
     * Là một kỹ thuật tối ưu hóa bộ nhớ, một mảng {@code null} được truyền vào sẽ bị ghi đè bởi các tham số
     * mặc định trong class này
     * </p>
     *
     * @param array array cần kiểm tra {@code null} or empty
     * @return Một array tương tự, {@code public static} empty array nếu {@code null} hoặc tham số đầu vào là empty.
     */
    public static Class<?>[] nullToEmpty(final Class<?>[] array) {
        if (isEmpty(array)) {
            return EMPTY_CLASS_ARRAY;
        }
        return array;
    }

    /**
     * <p>
     * Phương thức này trả về một mảng trống cho một mảng đầu vào {@code null}.
     * </p>
     * <p>
     * Là một kỹ thuật tối ưu hóa bộ nhớ, một mảng {@code null} được truyền vào sẽ bị ghi đè bởi các tham số
     * mặc định trong class này
     * </p>
     *
     * @param array array cần kiểm tra {@code null} or empty
     * @return Một array tương tự, {@code public static} empty array nếu {@code null} hoặc tham số đầu vào là empty.
     */
    public static double[] nullToEmpty(final double[] array) {
        if (isEmpty(array)) {
            return EMPTY_DOUBLE_ARRAY;
        }
        return array;
    }

    /**
     * <p>
     * Phương thức này trả về một mảng trống cho một mảng đầu vào {@code null}.
     * </p>
     * <p>
     * Là một kỹ thuật tối ưu hóa bộ nhớ, một mảng {@code null} được truyền vào sẽ bị ghi đè bởi các tham số
     * mặc định trong class này
     * </p>
     *
     * @param array array cần kiểm tra {@code null} or empty
     * @return Một array tương tự, {@code public static} empty array nếu {@code null} hoặc tham số đầu vào là empty.
     */
    public static Double[] nullToEmpty(final Double[] array) {
        if (isEmpty(array)) {
            return EMPTY_DOUBLE_OBJECT_ARRAY;
        }
        return array;
    }

    /**
     * <p>
     * Phương thức này trả về một mảng trống cho một mảng đầu vào {@code null}.
     * </p>
     * <p>
     * Là một kỹ thuật tối ưu hóa bộ nhớ, một mảng {@code null} được truyền vào sẽ bị ghi đè bởi các tham số
     * mặc định trong class này
     * </p>
     *
     * @param array array cần kiểm tra {@code null} or empty
     * @return Một array tương tự, {@code public static} empty array nếu {@code null} hoặc tham số đầu vào là empty.
     */
    public static float[] nullToEmpty(final float[] array) {
        if (isEmpty(array)) {
            return EMPTY_FLOAT_ARRAY;
        }
        return array;
    }

    /**
     * <p>
     * Phương thức này trả về một mảng trống cho một mảng đầu vào {@code null}.
     * </p>
     * <p>
     * Là một kỹ thuật tối ưu hóa bộ nhớ, một mảng {@code null} được truyền vào sẽ bị ghi đè bởi các tham số
     * mặc định trong class này
     * </p>
     *
     * @param array array cần kiểm tra {@code null} or empty
     * @return Một array tương tự, {@code public static} empty array nếu {@code null} hoặc tham số đầu vào là empty.
     */
    public static Float[] nullToEmpty(final Float[] array) {
        if (isEmpty(array)) {
            return EMPTY_FLOAT_OBJECT_ARRAY;
        }
        return array;
    }

    /**
     * <p>
     * Phương thức này trả về một mảng trống cho một mảng đầu vào {@code null}.
     * </p>
     * <p>
     * Là một kỹ thuật tối ưu hóa bộ nhớ, một mảng {@code null} được truyền vào sẽ bị ghi đè bởi các tham số
     * mặc định trong class này
     * </p>
     *
     * @param array array cần kiểm tra {@code null} or empty
     * @return Một array tương tự, {@code public static} empty array nếu {@code null} hoặc tham số đầu vào là empty.
     */
    public static int[] nullToEmpty(final int[] array) {
        if (isEmpty(array)) {
            return EMPTY_INT_ARRAY;
        }
        return array;
    }

    /**
     * <p>
     * Phương thức này trả về một mảng trống cho một mảng đầu vào {@code null}.
     * </p>
     * <p>
     * Là một kỹ thuật tối ưu hóa bộ nhớ, một mảng {@code null} được truyền vào sẽ bị ghi đè bởi các tham số
     * mặc định trong class này
     * </p>
     *
     * @param array array cần kiểm tra {@code null} or empty
     * @return Một array tương tự, {@code public static} empty array nếu {@code null} hoặc tham số đầu vào là empty.
     */
    public static Integer[] nullToEmpty(final Integer[] array) {
        if (isEmpty(array)) {
            return EMPTY_INTEGER_OBJECT_ARRAY;
        }
        return array;
    }

    /**
     * <p>
     * Phương thức này trả về một mảng trống cho một mảng đầu vào {@code null}.
     * </p>
     * <p>
     * Là một kỹ thuật tối ưu hóa bộ nhớ, một mảng {@code null} được truyền vào sẽ bị ghi đè bởi các tham số
     * mặc định trong class này
     * </p>
     *
     * @param array array cần kiểm tra {@code null} or empty
     * @return Một array tương tự, {@code public static} empty array nếu {@code null} hoặc tham số đầu vào là empty.
     */
    public static long[] nullToEmpty(final long[] array) {
        if (isEmpty(array)) {
            return EMPTY_LONG_ARRAY;
        }
        return array;
    }

    /**
     * <p>
     * Phương thức này trả về một mảng trống cho một mảng đầu vào {@code null}.
     * </p>
     * <p>
     * Là một kỹ thuật tối ưu hóa bộ nhớ, một mảng {@code null} được truyền vào sẽ bị ghi đè bởi các tham số
     * mặc định trong class này
     * </p>
     *
     * @param array array cần kiểm tra {@code null} or empty
     * @return Một array tương tự, {@code public static} empty array nếu {@code null} hoặc tham số đầu vào là empty.
     */
    public static Long[] nullToEmpty(final Long[] array) {
        if (isEmpty(array)) {
            return EMPTY_LONG_OBJECT_ARRAY;
        }
        return array;
    }

    /**
     * <p>
     * Phương thức này trả về một mảng trống cho một mảng đầu vào {@code null}.
     * </p>
     * <p>
     * Là một kỹ thuật tối ưu hóa bộ nhớ, một mảng {@code null} được truyền vào sẽ bị ghi đè bởi các tham số
     * mặc định trong class này
     * </p>
     *
     * @param array array cần kiểm tra {@code null} or empty
     * @return Một array tương tự, {@code public static} empty array nếu {@code null} hoặc tham số đầu vào là empty.
     */
    public static Object[] nullToEmpty(final Object[] array) {
        if (isEmpty(array)) {
            return EMPTY_OBJECT_ARRAY;
        }
        return array;
    }

    /**
     * <p>
     * Phương thức này trả về một mảng trống cho một mảng đầu vào {@code null}.
     * </p>
     * <p>
     * Là một kỹ thuật tối ưu hóa bộ nhớ, một mảng {@code null} được truyền vào sẽ bị ghi đè bởi các tham số
     * mặc định trong class này
     * </p>
     *
     * @param array array cần kiểm tra {@code null} or empty
     * @return Một array tương tự, {@code public static} empty array nếu {@code null} hoặc tham số đầu vào là empty.
     */
    public static short[] nullToEmpty(final short[] array) {
        if (isEmpty(array)) {
            return EMPTY_SHORT_ARRAY;
        }
        return array;
    }

    /**
     * <p>
     * Phương thức này trả về một mảng trống cho một mảng đầu vào {@code null}.
     * </p>
     * <p>
     * Là một kỹ thuật tối ưu hóa bộ nhớ, một mảng {@code null} được truyền vào sẽ bị ghi đè bởi các tham số
     * mặc định trong class này
     * </p>
     *
     * @param array array cần kiểm tra {@code null} or empty
     * @return Một array tương tự, {@code public static} empty array nếu {@code null} hoặc tham số đầu vào là empty.
     */
    public static Short[] nullToEmpty(final Short[] array) {
        if (isEmpty(array)) {
            return EMPTY_SHORT_OBJECT_ARRAY;
        }
        return array;
    }

    /**
     * <p>
     * Phương thức này trả về một mảng trống cho một mảng đầu vào {@code null}.
     * </p>
     * <p>
     * Là một kỹ thuật tối ưu hóa bộ nhớ, một mảng {@code null} được truyền vào sẽ bị ghi đè bởi các tham số
     * mặc định trong class này
     * </p>
     *
     * @param array array cần kiểm tra {@code null} or empty
     * @return Một array tương tự, {@code public static} empty array nếu {@code null} hoặc tham số đầu vào là empty.
     */
    public static String[] nullToEmpty(final String[] array) {
        if (isEmpty(array)) {
            return EMPTY_STRING_ARRAY;
        }
        return array;
    }

    /**
     * Defensive programming technique to change a {@code null}
     * reference to an empty one.
     * <p>
     * This method returns an empty array for a {@code null} input array.
     * </p>
     *
     * @param array the array to check for {@code null} or empty
     * @param type  the class representation of the desired array
     * @param <T>   the class type
     * @return the same array, {@code public static} empty array if {@code null}
     * @throws IllegalArgumentException if the type argument is null
     * @since 3.5
     */
    public static <T> T[] nullToEmpty(final T[] array, final Class<T[]> type) {
        if (type == null) {
            throw new IllegalArgumentException("The type must not be null");
        }

        if (array == null) {
            return type.cast(Array.newInstance(type.getComponentType(), 0));
        }
        return array;
    }

    // nullToEmpty - END
    //---------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------

    /**
     * Trả về độ dài của một mảng cụ thể.
     * THàm này hoạt động với cả mảng {@code Object} và primitive arrays.
     * <p>
     * If the input array is {@code null}, {@code 0} is returned.
     * </p>
     * <pre>
     * ArrayUtils.getLength(null)            = 0
     * ArrayUtils.getLength([])              = 0
     * ArrayUtils.getLength([null])          = 1
     * ArrayUtils.getLength([true, false])   = 2
     * ArrayUtils.getLength([1, 2, 3])       = 3
     * ArrayUtils.getLength(["a", "b", "c"]) = 3
     * </pre>
     *
     * @param array mảng cần kiểm tra, có thể {@code null}
     * @return ĐỘ dài của mảng, hoặc {@code 0} nếu mảng có giá trị {@code null}
     * @throws IllegalArgumentException Nếu tham số không phải là array.
     */
    public static int getLength(final Object array) {
        if (array == null) {
            return 0;
        }
        return Array.getLength(array);
    }

    // isEmpty - BEGIN
    //---------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------

    /**
     * Checks if an array of primitive booleans is empty or {@code null}.
     *
     * @param array the array to test
     * @return {@code true} if the array is empty or {@code null}
     */
    public static boolean isEmpty(final boolean[] array) {
        return getLength(array) == 0;
    }

    /**
     * Checks if an array of primitive bytes is empty or {@code null}.
     *
     * @param array the array to test
     * @return {@code true} if the array is empty or {@code null}
     */
    public static boolean isEmpty(final byte[] array) {
        return getLength(array) == 0;
    }

    /**
     * Checks if an array of primitive chars is empty or {@code null}.
     *
     * @param array the array to test
     * @return {@code true} if the array is empty or {@code null}
     */
    public static boolean isEmpty(final char[] array) {
        return getLength(array) == 0;
    }

    /**
     * Checks if an array of primitive doubles is empty or {@code null}.
     *
     * @param array the array to test
     * @return {@code true} if the array is empty or {@code null}
     */
    public static boolean isEmpty(final double[] array) {
        return getLength(array) == 0;
    }

    /**
     * Checks if an array of primitive floats is empty or {@code null}.
     *
     * @param array the array to test
     * @return {@code true} if the array is empty or {@code null}
     */
    public static boolean isEmpty(final float[] array) {
        return getLength(array) == 0;
    }

    /**
     * Checks if an array of primitive ints is empty or {@code null}.
     *
     * @param array the array to test
     * @return {@code true} if the array is empty or {@code null}
     */
    public static boolean isEmpty(final int[] array) {
        return getLength(array) == 0;
    }

    /**
     * Checks if an array of primitive longs is empty or {@code null}.
     *
     * @param array the array to test
     * @return {@code true} if the array is empty or {@code null}
     */
    public static boolean isEmpty(final long[] array) {
        return getLength(array) == 0;
    }

    /**
     * Checks if an array of Objects is empty or {@code null}.
     *
     * @param array the array to test
     * @return {@code true} if the array is empty or {@code null}
     */
    public static boolean isEmpty(final Object[] array) {
        return getLength(array) == 0;
    }

    /**
     * Checks if an array of primitive shorts is empty or {@code null}.
     *
     * @param array the array to test
     * @return {@code true} if the array is empty or {@code null}
     */
    public static boolean isEmpty(final short[] array) {
        return getLength(array) == 0;
    }

    // isEmpty - END
    //---------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------


    // toPrimitive - BEGIN
    //---------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------

    /**
     * Converts an array of object Booleans to primitives.
     * <p>
     * This method returns {@code null} for a {@code null} input array.
     * </p>
     *
     * @param array a {@code Boolean} array, may be {@code null}
     * @return a {@code boolean} array, {@code null} if null array input
     * @throws NullPointerException if array content is {@code null}
     */
    public static boolean[] toPrimitive(final Boolean[] array) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return EMPTY_BOOLEAN_ARRAY;
        }
        final boolean[] result = new boolean[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i].booleanValue();
        }
        return result;
    }

    /**
     * Converts an array of object Booleans to primitives handling {@code null}.
     * <p>
     * This method returns {@code null} for a {@code null} input array.
     * </p>
     *
     * @param array        a {@code Boolean} array, may be {@code null}
     * @param valueForNull the value to insert if {@code null} found
     * @return a {@code boolean} array, {@code null} if null array input
     */
    public static boolean[] toPrimitive(final Boolean[] array, final boolean valueForNull) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return EMPTY_BOOLEAN_ARRAY;
        }
        final boolean[] result = new boolean[array.length];
        for (int i = 0; i < array.length; i++) {
            final Boolean b = array[i];
            result[i] = b == null ? valueForNull : b.booleanValue();
        }
        return result;
    }

    /**
     * Converts an array of object Bytes to primitives.
     * <p>
     * This method returns {@code null} for a {@code null} input array.
     * </p>
     *
     * @param array a {@code Byte} array, may be {@code null}
     * @return a {@code byte} array, {@code null} if null array input
     * @throws NullPointerException if array content is {@code null}
     */
    public static byte[] toPrimitive(final Byte[] array) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return EMPTY_BYTE_ARRAY;
        }
        final byte[] result = new byte[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i].byteValue();
        }
        return result;
    }

    /**
     * Converts an array of object Bytes to primitives handling {@code null}.
     * <p>
     * This method returns {@code null} for a {@code null} input array.
     * </p>
     *
     * @param array        a {@code Byte} array, may be {@code null}
     * @param valueForNull the value to insert if {@code null} found
     * @return a {@code byte} array, {@code null} if null array input
     */
    public static byte[] toPrimitive(final Byte[] array, final byte valueForNull) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return EMPTY_BYTE_ARRAY;
        }
        final byte[] result = new byte[array.length];
        for (int i = 0; i < array.length; i++) {
            final Byte b = array[i];
            result[i] = b == null ? valueForNull : b.byteValue();
        }
        return result;
    }

    /**
     * Converts an array of object Characters to primitives.
     * <p>
     * This method returns {@code null} for a {@code null} input array.
     * </p>
     *
     * @param array a {@code Character} array, may be {@code null}
     * @return a {@code char} array, {@code null} if null array input
     * @throws NullPointerException if array content is {@code null}
     */
    public static char[] toPrimitive(final Character[] array) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return EMPTY_CHAR_ARRAY;
        }
        final char[] result = new char[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i].charValue();
        }
        return result;
    }

    /**
     * Converts an array of object Character to primitives handling {@code null}.
     * <p>
     * This method returns {@code null} for a {@code null} input array.
     * </p>
     *
     * @param array        a {@code Character} array, may be {@code null}
     * @param valueForNull the value to insert if {@code null} found
     * @return a {@code char} array, {@code null} if null array input
     */
    public static char[] toPrimitive(final Character[] array, final char valueForNull) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return EMPTY_CHAR_ARRAY;
        }
        final char[] result = new char[array.length];
        for (int i = 0; i < array.length; i++) {
            final Character b = array[i];
            result[i] = b == null ? valueForNull : b.charValue();
        }
        return result;
    }

    /**
     * Converts an array of object Doubles to primitives.
     * <p>
     * This method returns {@code null} for a {@code null} input array.
     * </p>
     *
     * @param array a {@code Double} array, may be {@code null}
     * @return a {@code double} array, {@code null} if null array input
     * @throws NullPointerException if array content is {@code null}
     */
    public static double[] toPrimitive(final Double[] array) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return EMPTY_DOUBLE_ARRAY;
        }
        final double[] result = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i].doubleValue();
        }
        return result;
    }

    /**
     * Converts an array of object Doubles to primitives handling {@code null}.
     * <p>
     * This method returns {@code null} for a {@code null} input array.
     * </p>
     *
     * @param array        a {@code Double} array, may be {@code null}
     * @param valueForNull the value to insert if {@code null} found
     * @return a {@code double} array, {@code null} if null array input
     */
    public static double[] toPrimitive(final Double[] array, final double valueForNull) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return EMPTY_DOUBLE_ARRAY;
        }
        final double[] result = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            final Double b = array[i];
            result[i] = b == null ? valueForNull : b.doubleValue();
        }
        return result;
    }

    /**
     * Converts an array of object Floats to primitives.
     * <p>
     * This method returns {@code null} for a {@code null} input array.
     * </p>
     *
     * @param array a {@code Float} array, may be {@code null}
     * @return a {@code float} array, {@code null} if null array input
     * @throws NullPointerException if array content is {@code null}
     */
    public static float[] toPrimitive(final Float[] array) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return EMPTY_FLOAT_ARRAY;
        }
        final float[] result = new float[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i].floatValue();
        }
        return result;
    }

    /**
     * Converts an array of object Floats to primitives handling {@code null}.
     * <p>
     * This method returns {@code null} for a {@code null} input array.
     * </p>
     *
     * @param array        a {@code Float} array, may be {@code null}
     * @param valueForNull the value to insert if {@code null} found
     * @return a {@code float} array, {@code null} if null array input
     */
    public static float[] toPrimitive(final Float[] array, final float valueForNull) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return EMPTY_FLOAT_ARRAY;
        }
        final float[] result = new float[array.length];
        for (int i = 0; i < array.length; i++) {
            final Float b = array[i];
            result[i] = b == null ? valueForNull : b.floatValue();
        }
        return result;
    }

    /**
     * Converts an array of object Integers to primitives.
     * <p>
     * This method returns {@code null} for a {@code null} input array.
     * </p>
     *
     * @param array a {@code Integer} array, may be {@code null}
     * @return an {@code int} array, {@code null} if null array input
     * @throws NullPointerException if array content is {@code null}
     */
    public static int[] toPrimitive(final Integer[] array) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return EMPTY_INT_ARRAY;
        }
        final int[] result = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i].intValue();
        }
        return result;
    }

    /**
     * Converts an array of object Integer to primitives handling {@code null}.
     * <p>
     * This method returns {@code null} for a {@code null} input array.
     * </p>
     *
     * @param array        a {@code Integer} array, may be {@code null}
     * @param valueForNull the value to insert if {@code null} found
     * @return an {@code int} array, {@code null} if null array input
     */
    public static int[] toPrimitive(final Integer[] array, final int valueForNull) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return EMPTY_INT_ARRAY;
        }
        final int[] result = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            final Integer b = array[i];
            result[i] = b == null ? valueForNull : b.intValue();
        }
        return result;
    }

    /**
     * Converts an array of object Longs to primitives.
     * <p>
     * This method returns {@code null} for a {@code null} input array.
     * </p>
     *
     * @param array a {@code Long} array, may be {@code null}
     * @return a {@code long} array, {@code null} if null array input
     * @throws NullPointerException if array content is {@code null}
     */
    public static long[] toPrimitive(final Long[] array) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return EMPTY_LONG_ARRAY;
        }
        final long[] result = new long[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i].longValue();
        }
        return result;
    }

    /**
     * Converts an array of object Long to primitives handling {@code null}.
     * <p>
     * This method returns {@code null} for a {@code null} input array.
     * </p>
     *
     * @param array        a {@code Long} array, may be {@code null}
     * @param valueForNull the value to insert if {@code null} found
     * @return a {@code long} array, {@code null} if null array input
     */
    public static long[] toPrimitive(final Long[] array, final long valueForNull) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return EMPTY_LONG_ARRAY;
        }
        final long[] result = new long[array.length];
        for (int i = 0; i < array.length; i++) {
            final Long b = array[i];
            result[i] = b == null ? valueForNull : b.longValue();
        }
        return result;
    }

    /**
     * Create an array of primitive type from an array of wrapper types.
     * <p>
     * This method returns {@code null} for a {@code null} input array.
     * </p>
     *
     * @param array an array of wrapper object
     * @return an array of the corresponding primitive type, or the original array
     * @since 3.5
     */
    public static Object toPrimitive(final Object array) {
        if (array == null) {
            return null;
        }
        final Class<?> ct = array.getClass().getComponentType();
        final Class<?> pt = ClassUtils.wrapperToPrimitive(ct);
        if (Boolean.TYPE.equals(pt)) {
            return toPrimitive((Boolean[]) array);
        }
        if (Character.TYPE.equals(pt)) {
            return toPrimitive((Character[]) array);
        }
        if (Byte.TYPE.equals(pt)) {
            return toPrimitive((Byte[]) array);
        }
        if (Integer.TYPE.equals(pt)) {
            return toPrimitive((Integer[]) array);
        }
        if (Long.TYPE.equals(pt)) {
            return toPrimitive((Long[]) array);
        }
        if (Short.TYPE.equals(pt)) {
            return toPrimitive((Short[]) array);
        }
        if (Double.TYPE.equals(pt)) {
            return toPrimitive((Double[]) array);
        }
        if (Float.TYPE.equals(pt)) {
            return toPrimitive((Float[]) array);
        }
        return array;
    }

    /**
     * Converts an array of object Shorts to primitives.
     * <p>
     * This method returns {@code null} for a {@code null} input array.
     * </p>
     *
     * @param array a {@code Short} array, may be {@code null}
     * @return a {@code byte} array, {@code null} if null array input
     * @throws NullPointerException if array content is {@code null}
     */
    public static short[] toPrimitive(final Short[] array) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return EMPTY_SHORT_ARRAY;
        }
        final short[] result = new short[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i].shortValue();
        }
        return result;
    }

    /**
     * Converts an array of object Short to primitives handling {@code null}.
     * <p>
     * This method returns {@code null} for a {@code null} input array.
     * </p>
     *
     * @param array        a {@code Short} array, may be {@code null}
     * @param valueForNull the value to insert if {@code null} found
     * @return a {@code byte} array, {@code null} if null array input
     */
    public static short[] toPrimitive(final Short[] array, final short valueForNull) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return EMPTY_SHORT_ARRAY;
        }
        final short[] result = new short[array.length];
        for (int i = 0; i < array.length; i++) {
            final Short b = array[i];
            result[i] = b == null ? valueForNull : b.shortValue();
        }
        return result;
    }

    // toPrimitive - END
    //---------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------

    // isSameLength - BEGIN
    //---------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------

    /**
     * Checks whether two arrays are the same length, treating
     * {@code null} arrays as length {@code 0}.
     *
     * @param array1 the first array, may be {@code null}
     * @param array2 the second array, may be {@code null}
     * @return {@code true} if length of arrays matches, treating
     * {@code null} as an empty array
     */
    public static boolean isSameLength(final boolean[] array1, final boolean[] array2) {
        return getLength(array1) == getLength(array2);
    }

    /**
     * Checks whether two arrays are the same length, treating
     * {@code null} arrays as length {@code 0}.
     *
     * @param array1 the first array, may be {@code null}
     * @param array2 the second array, may be {@code null}
     * @return {@code true} if length of arrays matches, treating
     * {@code null} as an empty array
     */
    public static boolean isSameLength(final byte[] array1, final byte[] array2) {
        return getLength(array1) == getLength(array2);
    }

    /**
     * Checks whether two arrays are the same length, treating
     * {@code null} arrays as length {@code 0}.
     *
     * @param array1 the first array, may be {@code null}
     * @param array2 the second array, may be {@code null}
     * @return {@code true} if length of arrays matches, treating
     * {@code null} as an empty array
     */
    public static boolean isSameLength(final char[] array1, final char[] array2) {
        return getLength(array1) == getLength(array2);
    }

    /**
     * Checks whether two arrays are the same length, treating
     * {@code null} arrays as length {@code 0}.
     *
     * @param array1 the first array, may be {@code null}
     * @param array2 the second array, may be {@code null}
     * @return {@code true} if length of arrays matches, treating
     * {@code null} as an empty array
     */
    public static boolean isSameLength(final double[] array1, final double[] array2) {
        return getLength(array1) == getLength(array2);
    }

    /**
     * Checks whether two arrays are the same length, treating
     * {@code null} arrays as length {@code 0}.
     *
     * @param array1 the first array, may be {@code null}
     * @param array2 the second array, may be {@code null}
     * @return {@code true} if length of arrays matches, treating
     * {@code null} as an empty array
     */
    public static boolean isSameLength(final float[] array1, final float[] array2) {
        return getLength(array1) == getLength(array2);
    }

    /**
     * Checks whether two arrays are the same length, treating
     * {@code null} arrays as length {@code 0}.
     *
     * @param array1 the first array, may be {@code null}
     * @param array2 the second array, may be {@code null}
     * @return {@code true} if length of arrays matches, treating
     * {@code null} as an empty array
     */
    public static boolean isSameLength(final int[] array1, final int[] array2) {
        return getLength(array1) == getLength(array2);
    }

    /**
     * Checks whether two arrays are the same length, treating
     * {@code null} arrays as length {@code 0}.
     *
     * @param array1 the first array, may be {@code null}
     * @param array2 the second array, may be {@code null}
     * @return {@code true} if length of arrays matches, treating
     * {@code null} as an empty array
     */
    public static boolean isSameLength(final long[] array1, final long[] array2) {
        return getLength(array1) == getLength(array2);
    }

    /**
     * Checks whether two arrays are the same length, treating
     * {@code null} arrays as length {@code 0}.
     * <p>
     * Any multi-dimensional aspects of the arrays are ignored.
     * </p>
     *
     * @param array1 the first array, may be {@code null}
     * @param array2 the second array, may be {@code null}
     * @return {@code true} if length of arrays matches, treating
     * {@code null} as an empty array
     * @since 3.11
     */
    public static boolean isSameLength(final Object array1, final Object array2) {
        return getLength(array1) == getLength(array2);
    }

    /**
     * Checks whether two arrays are the same length, treating
     * {@code null} arrays as length {@code 0}.
     * <p>
     * Any multi-dimensional aspects of the arrays are ignored.
     * </p>
     *
     * @param array1 the first array, may be {@code null}
     * @param array2 the second array, may be {@code null}
     * @return {@code true} if length of arrays matches, treating
     * {@code null} as an empty array
     */
    public static boolean isSameLength(final Object[] array1, final Object[] array2) {
        return getLength(array1) == getLength(array2);
    }


    /**
     * Checks whether two arrays are the same length, treating
     * {@code null} arrays as length {@code 0}.
     *
     * @param array1 the first array, may be {@code null}
     * @param array2 the second array, may be {@code null}
     * @return {@code true} if length of arrays matches, treating
     * {@code null} as an empty array
     */
    public static boolean isSameLength(final short[] array1, final short[] array2) {
        return getLength(array1) == getLength(array2);
    }

    // isSameLength - END
    //---------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------
}
