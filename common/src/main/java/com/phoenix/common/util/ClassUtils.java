package com.phoenix.common.util;

import java.util.*;
import java.util.stream.Collectors;

public class ClassUtils {

    /**
     * Maps primitive {@code Class}es to their corresponding wrapper {@code Class}.
     */
    private static final Map<Class<?>, Class<?>> primitiveWrapperMap = new HashMap<>();

    static {
        primitiveWrapperMap.put(Boolean.TYPE, Boolean.class);
        primitiveWrapperMap.put(Byte.TYPE, Byte.class);
        primitiveWrapperMap.put(Character.TYPE, Character.class);
        primitiveWrapperMap.put(Short.TYPE, Short.class);
        primitiveWrapperMap.put(Integer.TYPE, Integer.class);
        primitiveWrapperMap.put(Long.TYPE, Long.class);
        primitiveWrapperMap.put(Double.TYPE, Double.class);
        primitiveWrapperMap.put(Float.TYPE, Float.class);
        primitiveWrapperMap.put(Void.TYPE, Void.TYPE);
    }


    /**
     * Maps wrapper {@code Class}es to their corresponding primitive types.
     */
    private static final Map<Class<?>, Class<?>> wrapperPrimitiveMap = new HashMap<>();

    static {
        for (final Map.Entry<Class<?>, Class<?>> entry : primitiveWrapperMap.entrySet()) {
            final Class<?> primitiveClass = entry.getKey();
            final Class<?> wrapperClass = entry.getValue();
            if (!primitiveClass.equals(wrapperClass)) {
                wrapperPrimitiveMap.put(wrapperClass, primitiveClass);
            }
        }
    }

    /**
     * <p>
     * Given a {@code List} of {@code Class} objects, this method converts them into class names.
     * </p>
     *
     * <p>
     * A new {@code List} is returned. {@code null} objects will be copied into the returned list as {@code null}.
     * </p>
     *
     * @param classes the classes to change
     * @return a {@code List} of class names corresponding to the Class objects, {@code null} if null input
     * @throws ClassCastException if {@code classes} contains a non-{@code Class} entry
     */
    public static List<String> convertClassesToClassNames(final List<Class<?>> classes) {
        return classes == null ? null : classes.stream().map(e -> getName(e, null)).collect(Collectors.toList());
    }

    /**
     * <p>
     * Given a {@code List} of class names, this method converts them into classes.
     * </p>
     *
     * <p>
     * A new {@code List} is returned. If the class name cannot be found, {@code null} is stored in the {@code List}. If the
     * class name in the {@code List} is {@code null}, {@code null} is stored in the output {@code List}.
     * </p>
     *
     * @param classNames the classNames to change
     * @return a {@code List} of Class objects corresponding to the class names, {@code null} if null input
     * @throws ClassCastException if classNames contains a non String entry
     */
    public static List<Class<?>> convertClassNamesToClasses(final List<String> classNames) {
        if (classNames == null) {
            return null;
        }
        final List<Class<?>> classes = new ArrayList<>(classNames.size());
        for (final String className : classNames) {
            try {
                classes.add(Class.forName(className));
            } catch (final Exception ex) {
                classes.add(null);
            }
        }
        return classes;
    }

    /**
     * <p>
     * Gets a {@code List} of all interfaces implemented by the given class and its superclasses.
     * </p>
     *
     * <p>
     * The order is determined by looking through each interface in turn as declared in the source file and following its
     * hierarchy up. Then each superclass is considered in the same way. Later duplicates are ignored, so the order is
     * maintained.
     * </p>
     *
     * @param cls the class to look up, may be {@code null}
     * @return the {@code List} of interfaces in order, {@code null} if null input
     */
    public static List<Class<?>> getAllInterfaces(final Class<?> cls) {
        if (cls == null) {
            return null;
        }

        final LinkedHashSet<Class<?>> interfacesFound = new LinkedHashSet<>();
        getAllInterfaces(cls, interfacesFound);

        return new ArrayList<>(interfacesFound);
    }

    /**
     * Gets the interfaces for the specified class.
     *
     * @param cls             the class to look up, may be {@code null}
     * @param interfacesFound the {@code Set} of interfaces for the class
     */
    private static void getAllInterfaces(Class<?> cls, final HashSet<Class<?>> interfacesFound) {
        while (cls != null) {
            final Class<?>[] interfaces = cls.getInterfaces();

            for (final Class<?> i : interfaces) {
                if (interfacesFound.add(i)) {
                    getAllInterfaces(i, interfacesFound);
                }
            }

            cls = cls.getSuperclass();
        }
    }

    /**
     * <p>
     * Gets a {@code List} of superclasses for the given class.
     * </p>
     *
     * @param cls the class to look up, may be {@code null}
     * @return the {@code List} of superclasses in order going up from this one {@code null} if null input
     */
    public static List<Class<?>> getAllSuperclasses(final Class<?> cls) {
        if (cls == null) {
            return null;
        }
        final List<Class<?>> classes = new ArrayList<>();
        Class<?> superclass = cls.getSuperclass();
        while (superclass != null) {
            classes.add(superclass);
            superclass = superclass.getSuperclass();
        }
        return classes;
    }


    /**
     * <p>
     * Converts một mảng {@code Object} thành một mảng {@code Class} objects. Nếu có bất kỳ object nào là null thì giá trị
     * được convert sẽ là null.
     * </p>
     *
     * <p>
     * Hàm này trả về {@code null} cho {@code null} mảng.
     * </p>
     *
     * @param array an {@code Object} array
     * @return a {@code Class} array, {@code null} if null array input
     */
    public static Class<?>[] toClass(final Object... array) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return ArrayUtils.EMPTY_CLASS_ARRAY;
        }
        final Class<?>[] classes = new Class[array.length];
        for (int i = 0; i < array.length; i++) {
            classes[i] = array[i] == null ? null : array[i].getClass();
        }
        return classes;
    }

    /**
     * <p>
     * Converts the specified wrapper class to its corresponding primitive class.
     * </p>
     *
     * <p>
     * This method is the counter part of {@code primitiveToWrapper()}. If the passed in class is a wrapper class for a
     * primitive type, this primitive type will be returned (e.g. {@code Integer.TYPE} for {@code Integer.class}). For other
     * classes, or if the parameter is <b>null</b>, the return value is <b>null</b>.
     * </p>
     *
     * @param cls the class to convert, may be <b>null</b>
     * @return the corresponding primitive type if {@code cls} is a wrapper class, <b>null</b> otherwise
     * @see #primitiveToWrapper(Class)
     */
    public static Class<?> wrapperToPrimitive(final Class<?> cls) {
        return wrapperPrimitiveMap.get(cls);
    }

    /**
     * <p>
     * Converts the specified array of wrapper Class objects to an array of its corresponding primitive Class objects.
     * </p>
     *
     * <p>
     * This method invokes {@code wrapperToPrimitive()} for each element of the passed in array.
     * </p>
     *
     * @param classes the class array to convert, may be null or empty
     * @return an array which contains for each given class, the primitive class or <b>null</b> if the original class is not
     * a wrapper class. {@code null} if null input. Empty array if an empty array passed in.
     * @see #wrapperToPrimitive(Class)
     */
    public static Class<?>[] wrappersToPrimitives(final Class<?>... classes) {
        if (classes == null) {
            return null;
        }

        if (classes.length == 0) {
            return classes;
        }

        final Class<?>[] convertedClasses = new Class[classes.length];
        for (int i = 0; i < classes.length; i++) {
            convertedClasses[i] = wrapperToPrimitive(classes[i]);
        }
        return convertedClasses;
    }

    /**
     * <p>
     * Converts the specified array of primitive Class objects to an array of its corresponding wrapper Class objects.
     * </p>
     *
     * @param classes the class array to convert, may be null or empty
     * @return an array which contains for each given class, the wrapper class or the original class if class is not a
     * primitive. {@code null} if null input. Empty array if an empty array passed in.
     * @since 2.1
     */
    public static Class<?>[] primitivesToWrappers(final Class<?>... classes) {
        if (classes == null) {
            return null;
        }

        if (classes.length == 0) {
            return classes;
        }

        final Class<?>[] convertedClasses = new Class[classes.length];
        for (int i = 0; i < classes.length; i++) {
            convertedClasses[i] = primitiveToWrapper(classes[i]);
        }
        return convertedClasses;
    }

    /**
     * <p>
     * Converts the specified primitive Class object to its corresponding wrapper Class object.
     * </p>
     *
     * <p>
     * NOTE: From v2.2, this method handles {@code Void.TYPE}, returning {@code Void.TYPE}.
     * </p>
     *
     * @param cls the class to convert, may be null
     * @return the wrapper class for {@code cls} or {@code cls} if {@code cls} is not a primitive. {@code null} if null
     * input.
     * @since 2.1
     */
    public static Class<?> primitiveToWrapper(final Class<?> cls) {
        Class<?> convertedClass = cls;
        if (cls != null && cls.isPrimitive()) {
            convertedClass = primitiveWrapperMap.get(cls);
        }
        return convertedClass;
    }

    /**
     * <p>
     * Null-safe version of {@code cls.getName()}
     * </p>
     *
     * @param cls the class for which to get the class name; may be null
     * @return the class name or the empty string in case the argument is {@code null}
     * @see Class#getSimpleName()
     */
    public static String getName(final Class<?> cls) {
        return getName(cls, StringUtils.EMPTY);
    }

    /**
     * <p>
     * Null-safe version of {@code cls.getName()}
     * </p>
     *
     * @param cls         the class for which to get the class name; may be null
     * @param valueIfNull the return value if the argument {@code cls} is {@code null}
     * @return the class name or {@code valueIfNull}
     * @see Class#getName()
     */
    public static String getName(final Class<?> cls, final String valueIfNull) {
        return cls == null ? valueIfNull : cls.getName();
    }

    /**
     * <p>
     * Checks if one {@code Class} can be assigned to a variable of another {@code Class}.
     * </p>
     *
     * <p>
     * Unlike the {@link Class#isAssignableFrom(java.lang.Class)} method, this method takes into account widenings of
     * primitive classes and {@code null}s.
     * </p>
     *
     * <p>
     * Primitive widenings allow an int to be assigned to a long, float or double. This method returns the correct result
     * for these cases.
     * </p>
     *
     * <p>
     * {@code Null} may be assigned to any reference type. This method will return {@code true} if {@code null} is passed in
     * and the toClass is non-primitive.
     * </p>
     *
     * <p>
     * Specifically, this method tests whether the type represented by the specified {@code Class} parameter can be
     * converted to the type represented by this {@code Class} object via an identity conversion widening primitive or
     * widening reference conversion. See <em><a href="http://docs.oracle.com/javase/specs/">The Java Language
     * Specification</a></em>, sections 5.1.1, 5.1.2 and 5.1.4 for details.
     * </p>
     *
     * <p>
     * <strong>Since Lang 3.0,</strong> this method will default behavior for calculating assignability between primitive
     * and wrapper types <em>corresponding to the running Java version</em>; i.e. autoboxing will be the default behavior in
     * VMs running Java versions &gt; 1.5.
     * </p>
     *
     * @param cls     the Class to check, may be null
     * @param toClass the Class to try to assign into, returns false if null
     * @return {@code true} if assignment possible
     */
    public static boolean isAssignable(final Class<?> cls, final Class<?> toClass) {
        return isAssignable(cls, toClass, true);
    }

    /**
     * <p>
     * Checks if one {@code Class} can be assigned to a variable of another {@code Class}.
     * </p>
     *
     * <p>
     * Unlike the {@link Class#isAssignableFrom(java.lang.Class)} method, this method takes into account widenings of
     * primitive classes and {@code null}s.
     * </p>
     *
     * <p>
     * Primitive widenings allow an int to be assigned to a long, float or double. This method returns the correct result
     * for these cases.
     * </p>
     *
     * <p>
     * {@code Null} may be assigned to any reference type. This method will return {@code true} if {@code null} is passed in
     * and the toClass is non-primitive.
     * </p>
     *
     * <p>
     * Specifically, this method tests whether the type represented by the specified {@code Class} parameter can be
     * converted to the type represented by this {@code Class} object via an identity conversion widening primitive or
     * widening reference conversion. See <em><a href="http://docs.oracle.com/javase/specs/">The Java Language
     * Specification</a></em>, sections 5.1.1, 5.1.2 and 5.1.4 for details.
     * </p>
     *
     * @param cls        the Class to check, may be null
     * @param toClass    the Class to try to assign into, returns false if null
     * @param autoboxing whether to use implicit autoboxing/unboxing between primitives and wrappers
     * @return {@code true} if assignment possible
     */
    public static boolean isAssignable(Class<?> cls, final Class<?> toClass, final boolean autoboxing) {
        if (toClass == null) {
            return false;
        }
        // have to check for null, as isAssignableFrom doesn't
        if (cls == null) {
            return !toClass.isPrimitive();
        }
        // autoboxing:
        if (autoboxing) {
            if (cls.isPrimitive() && !toClass.isPrimitive()) {
                cls = primitiveToWrapper(cls);
                if (cls == null) {
                    return false;
                }
            }
            if (toClass.isPrimitive() && !cls.isPrimitive()) {
                cls = wrapperToPrimitive(cls);
                if (cls == null) {
                    return false;
                }
            }
        }
        if (cls.equals(toClass)) {
            return true;
        }
        if (cls.isPrimitive()) {
            if (!toClass.isPrimitive()) {
                return false;
            }
            if (Integer.TYPE.equals(cls)) {
                return Long.TYPE.equals(toClass) || Float.TYPE.equals(toClass) || Double.TYPE.equals(toClass);
            }
            if (Long.TYPE.equals(cls)) {
                return Float.TYPE.equals(toClass) || Double.TYPE.equals(toClass);
            }
            if (Boolean.TYPE.equals(cls)) {
                return false;
            }
            if (Double.TYPE.equals(cls)) {
                return false;
            }
            if (Float.TYPE.equals(cls)) {
                return Double.TYPE.equals(toClass);
            }
            if (Character.TYPE.equals(cls) || Short.TYPE.equals(cls)) {
                return Integer.TYPE.equals(toClass) || Long.TYPE.equals(toClass) || Float.TYPE.equals(toClass) || Double.TYPE.equals(toClass);
            }
            if (Byte.TYPE.equals(cls)) {
                return Short.TYPE.equals(toClass) || Integer.TYPE.equals(toClass) || Long.TYPE.equals(toClass) || Float.TYPE.equals(toClass)
                        || Double.TYPE.equals(toClass);
            }
            // should never get here
            return false;
        }
        return toClass.isAssignableFrom(cls);
    }

    /**
     * <p>
     * Checks if an array of Classes can be assigned to another array of Classes.
     * </p>
     *
     * <p>
     * This method calls {@link #isAssignable(Class, Class) isAssignable} for each Class pair in the input arrays. It can be
     * used to check if a set of arguments (the first parameter) are suitably compatible with a set of method parameter
     * types (the second parameter).
     * </p>
     *
     * <p>
     * Unlike the {@link Class#isAssignableFrom(java.lang.Class)} method, this method takes into account widenings of
     * primitive classes and {@code null}s.
     * </p>
     *
     * <p>
     * Primitive widenings allow an int to be assigned to a {@code long}, {@code float} or {@code double}. This method
     * returns the correct result for these cases.
     * </p>
     *
     * <p>
     * {@code Null} may be assigned to any reference type. This method will return {@code true} if {@code null} is passed in
     * and the toClass is non-primitive.
     * </p>
     *
     * <p>
     * Specifically, this method tests whether the type represented by the specified {@code Class} parameter can be
     * converted to the type represented by this {@code Class} object via an identity conversion widening primitive or
     * widening reference conversion. See <em><a href="http://docs.oracle.com/javase/specs/">The Java Language
     * Specification</a></em>, sections 5.1.1, 5.1.2 and 5.1.4 for details.
     * </p>
     *
     * <p>
     * <strong>Since Lang 3.0,</strong> this method will default behavior for calculating assignability between primitive
     * and wrapper types <em>corresponding to the running Java version</em>; i.e. autoboxing will be the default behavior in
     * VMs running Java versions &gt; 1.5.
     * </p>
     *
     * @param classArray   the array of Classes to check, may be {@code null}
     * @param toClassArray the array of Classes to try to assign into, may be {@code null}
     * @return {@code true} if assignment possible
     */
    public static boolean isAssignable(final Class<?>[] classArray, final Class<?>... toClassArray) {
        return isAssignable(classArray, toClassArray, true);
    }

    /**
     * <p>
     * Checks if an array of Classes can be assigned to another array of Classes.
     * </p>
     *
     * <p>
     * This method calls {@link #isAssignable(Class, Class) isAssignable} for each Class pair in the input arrays. It can be
     * used to check if a set of arguments (the first parameter) are suitably compatible with a set of method parameter
     * types (the second parameter).
     * </p>
     *
     * <p>
     * Unlike the {@link Class#isAssignableFrom(java.lang.Class)} method, this method takes into account widenings of
     * primitive classes and {@code null}s.
     * </p>
     *
     * <p>
     * Primitive widenings allow an int to be assigned to a {@code long}, {@code float} or {@code double}. This method
     * returns the correct result for these cases.
     * </p>
     *
     * <p>
     * {@code Null} may be assigned to any reference type. This method will return {@code true} if {@code null} is passed in
     * and the toClass is non-primitive.
     * </p>
     *
     * <p>
     * Specifically, this method tests whether the type represented by the specified {@code Class} parameter can be
     * converted to the type represented by this {@code Class} object via an identity conversion widening primitive or
     * widening reference conversion. See <em><a href="http://docs.oracle.com/javase/specs/">The Java Language
     * Specification</a></em>, sections 5.1.1, 5.1.2 and 5.1.4 for details.
     * </p>
     *
     * @param classArray   the array of Classes to check, may be {@code null}
     * @param toClassArray the array of Classes to try to assign into, may be {@code null}
     * @param autoboxing   whether to use implicit autoboxing/unboxing between primitives and wrappers
     * @return {@code true} if assignment possible
     */
    public static boolean isAssignable(Class<?>[] classArray, Class<?>[] toClassArray, final boolean autoboxing) {
        if (!ArrayUtils.isSameLength(classArray, toClassArray)) {
            return false;
        }
        if (classArray == null) {
            classArray = ArrayUtils.EMPTY_CLASS_ARRAY;
        }
        if (toClassArray == null) {
            toClassArray = ArrayUtils.EMPTY_CLASS_ARRAY;
        }
        for (int i = 0; i < classArray.length; i++) {
            if (!isAssignable(classArray[i], toClassArray[i], autoboxing)) {
                return false;
            }
        }
        return true;
    }



}
