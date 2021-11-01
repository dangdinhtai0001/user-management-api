package com.phoenix.common.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

public class ObjectUtils {
    /**
     * Checks that the specified object reference is not {@code null} or empty per {@link #isEmpty(Object)}. Use this
     * method for validation, for example:
     *
     * <blockquote>
     *
     * <pre>
     * public Foo(Bar bar) {
     *     this.bar = Objects.requireNonEmpty(bar);
     * }
     * </pre>
     *
     * </blockquote>
     *
     * @param <T> the type of the reference.
     * @param obj the object reference to check for nullity.
     * @return {@code obj} if not {@code null}.
     * @throws NullPointerException     if {@code obj} is {@code null}.
     * @throws IllegalArgumentException if {@code obj} is empty per {@link #isEmpty(Object)}.
     * @see #isEmpty(Object)
     */
    public static <T> T requireNonEmpty(final T obj) {
        return requireNonEmpty(obj, "object");
    }

    /**
     * Checks that the specified object reference is not {@code null} or empty per {@link #isEmpty(Object)}. Use this
     * method for validation, for example:
     *
     * <blockquote>
     *
     * <pre>
     * public Foo(Bar bar) {
     *     this.bar = Objects.requireNonEmpty(bar, "bar");
     * }
     * </pre>
     *
     * </blockquote>
     *
     * @param <T>     the type of the reference.
     * @param obj     the object reference to check for nullity.
     * @param message the exception message.
     * @return {@code obj} if not {@code null}.
     * @throws NullPointerException     if {@code obj} is {@code null}.
     * @throws IllegalArgumentException if {@code obj} is empty per {@link #isEmpty(Object)}.
     * @see #isEmpty(Object)
     */
    public static <T> T requireNonEmpty(final T obj, final String message) {
        // check for null first to give the most precise exception.
        Objects.requireNonNull(obj, message);
        if (isEmpty(obj)) {
            throw new IllegalArgumentException(message);
        }
        return obj;
    }


    /**
     * <p>Checks if an Object is empty or null.</p>
     * <p>
     * The following types are supported:
     * <ul>
     * <li>{@link CharSequence}: Considered empty if its length is zero.</li>
     * <li>{@code Array}: Considered empty if its length is zero.</li>
     * <li>{@link Collection}: Considered empty if it has zero elements.</li>
     * <li>{@link Map}: Considered empty if it has zero key-value mappings.</li>
     * </ul>
     *
     * <pre>
     * ObjectUtils.isEmpty(null)             = true
     * ObjectUtils.isEmpty("")               = true
     * ObjectUtils.isEmpty("ab")             = false
     * ObjectUtils.isEmpty(new int[]{})      = true
     * ObjectUtils.isEmpty(new int[]{1,2,3}) = false
     * ObjectUtils.isEmpty(1234)             = false
     * </pre>
     *
     * @param object the {@code Object} to test, may be {@code null}
     * @return {@code true} if the object has a supported type and is empty or null,
     * {@code false} otherwise
     * @since 3.9
     */
    public static boolean isEmpty(final Object object) {
        if (object == null) {
            return true;
        }
        if (object instanceof CharSequence) {
            return ((CharSequence) object).length() == 0;
        }
        if (isArray(object)) {
            return Array.getLength(object) == 0;
        }
        if (object instanceof Collection<?>) {
            return ((Collection<?>) object).isEmpty();
        }
        if (object instanceof Map<?, ?>) {
            return ((Map<?, ?>) object).isEmpty();
        }
        return false;
    }

    /**
     * <p>
     * Checks, whether the given object is an Object array or a primitive array in a null-safe manner.
     * </p>
     *
     * <p>
     * A {@code null} {@code object} Object will return {@code false}.
     * </p>
     *
     * <pre>
     * ObjectUtils.isArray(null)             = false
     * ObjectUtils.isArray("")               = false
     * ObjectUtils.isArray("ab")             = false
     * ObjectUtils.isArray(new int[]{})      = true
     * ObjectUtils.isArray(new int[]{1,2,3}) = true
     * ObjectUtils.isArray(1234)             = false
     * </pre>
     *
     * @param object the object to check, may be {@code null}
     * @return {@code true} if the object is an {@code array}, {@code false} otherwise
     * @since 3.13.0
     */
    public static boolean isArray(final Object object) {
        return object != null && object.getClass().isArray();
    }


}
