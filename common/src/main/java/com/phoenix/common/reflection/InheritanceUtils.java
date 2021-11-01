package com.phoenix.common.reflection;

import com.phoenix.common.util.BooleanUtils;

/**
 * <p>Utility methods focusing on inheritance.</p>
 *
 */
public class InheritanceUtils {

    /**
     * <p>{@link InheritanceUtils} instances should NOT be constructed in standard programming.
     * Instead, the class should be used as
     * {@code MethodUtils.getAccessibleMethod(method)}.</p>
     *
     * <p>This constructor is {@code public} to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    public InheritanceUtils() {
    }

    /**
     * <p>Returns the number of inheritance hops between two classes.</p>
     *
     * @param child  the child class, may be {@code null}
     * @param parent the parent class, may be {@code null}
     * @return the number of generations between the child and parent; 0 if the same class;
     * -1 if the classes are not related as child and parent (includes where either class is null)
     */
    public static int distance(final Class<?> child, final Class<?> parent) {
        if (child == null || parent == null) {
            return -1;
        }

        if (child.equals(parent)) {
            return 0;
        }

        final Class<?> cParent = child.getSuperclass();
        int d = BooleanUtils.toInteger(parent.equals(cParent));

        if (d == 1) {
            return d;
        }
        d += distance(cParent, parent);
        return d > 0 ? d + 1 : -1;
    }
}
