/*
 * @Author: Đặng Đình Tài
 * @Created date: 7/15/21, 10:12 AM
 */

/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

/*
 * @Author: Đặng Đình Tài
 * @Created_date: 6/26/21, 4:24 PM
 */

package com.phoenix.common.util;

import com.phoenix.common.structure.Pair;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Class gồm các hàm static hỗ trợ reflection
 * <p>
 * Chuyển sang sử dụng ConstructorUtils, MethodUtils, FieldUtils
 */
@Deprecated
public class ReflectionUtil {

    // -------------------------------------------------------------------------------------------------------------
    // ------------------------------------------------ Constructor
    // -------------------------------------------------------------------------------------------------------------

    /**
     * @param aClass Class cần tạo instance mới. Lưu ý, class này cần phải có hàm dựng không đối số
     * @return Instance của class
     * @throws NoSuchMethodException     Nếu không tồn tại hàm dựng không đối số
     * @throws InvocationTargetException khi khởi tạo instance mới
     * @throws InstantiationException    khi khởi tạo instance mới
     * @throws IllegalAccessException    khi khởi tạo instance mới
     */
    public static <T> T createInstance(Class<T> aClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<T> constructor = aClass.getConstructor();
        return constructor.newInstance();
    }

    // -------------------------------------------------------------------------------------------------------------
    // ------------------------------------------------ Methods
    // -------------------------------------------------------------------------------------------------------------

    /**
     * @param aClass Class cần xử lí
     * @return List<String> tên các declared method của class
     */
    public static List<String> getAllDeclaredMethodNames(Class<?> aClass) {
        return Arrays.stream(aClass.getDeclaredMethods()).map(Method::getName).collect(Collectors.toList());
    }

    /**
     * @param aClass Class cần xử lí
     * @return List<String> tên các public  method của class
     */
    public static List<String> getAllPublicMethodNames(Class<?> aClass) {
        return Arrays.stream(aClass.getDeclaredMethods())
//                .filter(p -> "public".equals(Modifier.toString(p.getModifiers())))
                .filter(p -> Modifier.isPublic(p.getModifiers()))
                .map(Method::getName)
                .collect(Collectors.toList());
    }

    /**
     * @param aClass         Class chứa method cần tìm
     * @param methodName     Tên methods
     * @param parameterTypes Mảng các Class các tham số của hàm (Theo đúng thứ tự)
     * @return {@link Method} Bao gồm cả public và private methods
     * @throws NoSuchMethodException Khi không tìm thấy method trên class đấy
     */
    public static Method getMethod(Class<?> aClass, String methodName, Class<?>... parameterTypes) throws NoSuchMethodException {
        return aClass.getMethod(methodName, parameterTypes);
    }

    /**
     * @param className      Tên class chứa method cần tìm
     * @param methodName     Tên methods
     * @param parameterTypes Mảng các Class các tham số của hàm (Theo đúng thứ tự)
     * @return Bao gồm cả public và private methods
     * @throws NoSuchMethodException  Khi không tìm thấy method trên class đấy
     * @throws ClassNotFoundException Khi không tìm thấy class
     */
    public static Method getMethod(String className, String methodName, Class<?>... parameterTypes) throws NoSuchMethodException, ClassNotFoundException {
        Class<?> aClass = Class.forName(className);
        return aClass.getMethod(methodName, parameterTypes);
    }


    /**
     * @param instance {@link Object} Instace của class chứa hàm cần thực thi
     * @param method   {@link Method} Đối tượng Method của hàm cần thực thi
     * @param args     {@link Object} danh sách các tham số
     * @return {@link Object} Kết quả trả về của hàm
     * @throws InvocationTargetException if the underlying method throws an exception.
     * @throws IllegalAccessException    ___
     */
    public static Object invokeMethod(Object instance, Method method, Object... args) throws InvocationTargetException, IllegalAccessException {
        return method.invoke(instance, args);
    }

    /**
     * @param instance   Instance của class chứa hàm cần thực thi
     * @param methodName Tên hàm cần thực thi
     * @param args       Danh sách tham số (Đúng thứ tự)
     * @return {@link Object}
     * @throws NoSuchMethodException     Khi không tìm tháy hàm
     * @throws InvocationTargetException The reflection layer wraps any exception in an InvocationTargetException
     * @throws IllegalAccessException    ___
     */
    public static Object invokeMethod(Object instance, String methodName, Object... args)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?>[] parameterTypes = Arrays.stream(args).map(Object::getClass).toArray(Class<?>[]::new);

        Method method = getMethod(instance.getClass(), methodName, parameterTypes);
        return invokeMethod(instance, method, args);
    }

    /**
     * @param instance   Instance của class chứa hàm cần thực thi
     * @param methodName Tên hàm cần thực thi
     * @param returnType Kiểu trả về
     * @param args       Danh sách tham số (Đúng thứ tự)
     * @return Kết cả đã đc cast sang T
     * @throws NoSuchMethodException     Khi không tìm tháy hàm
     * @throws InvocationTargetException ___
     * @throws IllegalAccessException    ___
     */
    public static <T> T invokeMethod(Object instance, String methodName, Class<T> returnType, Object... args)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return returnType.cast(invokeMethod(instance, methodName, args));
    }

    // -------------------------------------------------------------------------------------------------------------
    // ------------------------------------------------ Fields
    // -------------------------------------------------------------------------------------------------------------

    /**
     * @param aClass Class cần xử lí
     * @return Mảng chứa tất cả các field của class (Không bao gồm các field của supper class)
     */
    public static Field[] getDeclaredFields(Class<?> aClass) {
        return aClass.getDeclaredFields();
    }

    /**
     * @param aClass : Class
     * @return List chứa tất cả các field của class (Bao gồm các field của supper class), dùng đệ quy
     */
    public static List<Field> getAllFields(Class<?> aClass) {
        if (aClass == null) {
            return new LinkedList<>();
        }

        Field[] fields = aClass.getDeclaredFields();

        LinkedList<Field> list = new LinkedList<>(Arrays.asList(fields));

        list.addAll(getAllFields(aClass.getSuperclass()));

        return list;
    }

    /**
     * @param instanceClass Class cần xét
     * @param name          tên trường
     * @return class của field
     * @throws NoSuchFieldException khi không tìm thấy field
     */
    public static <T> Class<?> getTypeOfFieldByName(Class<T> instanceClass, String name) throws NoSuchFieldException {
        List<Field> fields = getAllFields(instanceClass);

        Field field = fields.stream()
                .filter(f -> f.getName().equals(name))
                .findAny()
                .orElse(null);
        // Nếu field == null thì return null
        return field == null ? null : field.getType();
    }

    /**
     * @param name   tên field cần tìm
     * @param aClass Class cần xử lí
     * @return Field có tên == name, nếu không tìm thấy trả về null
     */
    public static Field findFieldByName(String name, Class<?> aClass) {
        List<Field> list = getAllFields(aClass);

        Optional<Field> optional = list.stream()
                .filter(field -> name.equals(field.getName()))
                .findFirst();

        return optional.orElse(null);
    }

    /**
     * @param obj       Instance để set giá trị
     * @param fieldName Tên field cần set
     * @param value     Giá trị muốn set
     * @throws NoSuchFieldException   nếu không tìm thấy field
     * @throws IllegalAccessException Sai kiểu dữ liệu
     */
    public static void setField(Object obj, String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field;
        Class<?> objClass;

        objClass = obj.getClass();
        try {
            field = objClass.getField(fieldName);
        } catch (NoSuchFieldException e) {
            field = findFieldByName(fieldName, objClass);
        }

        if (field == null) {
            throw new NoSuchFieldException(String.format("Can't find field: %s of class: %s", fieldName, objClass.getSimpleName()));
        }

        field.setAccessible(true);

        field.set(obj, value);

    }

    /**
     * @param fieldName    Tên field cần set
     * @param classOfField Kiểu dữ liệu của field cần set
     * @param value        Giá trị muốn set (dạng String)
     * @param obj          Instance để set giá trị
     * @throws NoSuchFieldException nếu không tìm thấy field
     */
    public static void setField(String fieldName, Class<?> classOfField, String value, Object obj) throws NoSuchFieldException, IllegalAccessException {
        Class<?> objClass = obj.getClass();
        Field field;

        try {
            field = objClass.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            field = findFieldByName(fieldName, objClass);
        }

        if (field == null) {
            throw new NoSuchFieldException(String.format("Can't find field: %s of class: %s", fieldName, objClass.getSimpleName()));
        }

        field.setAccessible(true);

        if (int.class.equals(classOfField) || Integer.class.equals(classOfField)) {
            field.set(obj, Integer.parseInt(value));
        } else if (long.class.equals(classOfField) || Long.class.equals(classOfField)) {
            field.set(obj, Long.parseLong(value));
        } else if (double.class.equals(classOfField) || Double.class.equals(classOfField)) {
            field.set(obj, Double.parseDouble(value));
        } else if (Date.class.equals(classOfField)) {
            field.set(obj, DateUtil.convertString2Date(value, DateUtil.DEFAULT_DATE_FORMAT));
        } else {
            field.set(obj, classOfField.cast(value));
        }
    }

    /**
     * @param field Đối tượng {@link Field} cần cập nhật giá trị
     * @param value giá trị cần set (dạng String)
     * @param obj   Instance cần cập nhật giá trị
     * @throws NoSuchFieldException   khi không tìm thấy Filed trong class
     * @throws IllegalAccessException Khi có lỗi parse String value sang giá trị phù hợp
     */
    public static void setField(Field field, String value, Object obj) throws NoSuchFieldException, IllegalAccessException {
        setField(field.getName(), field.getType(), value, obj);
    }

    /**
     * @param instance  instance cần lấy giá trị
     * @param fieldName Tên field cần lấy giá trị
     * @return gia trị của field của instance
     * @throws IllegalAccessException xem tại {@link Field#get(Object)}
     */
    public static Object getFieldValue(Object instance, String fieldName) throws IllegalAccessException {
        Field field = findFieldByName(fieldName, instance.getClass());

        return field.get(instance);
    }

    /**
     * @param instance  instance cần lấy giá trị
     * @param fieldName Tên field cần lấy giá trị
     * @param type      Kiểu dữ liệu của field
     * @param <T>       Kiểu dữ liệu của Field
     * @return gia trị của field của instance (Đã được cast sang type)
     * @throws IllegalAccessException xem tại {@link Field#get(Object)}
     */
    public static <T> T getFieldValue(Object instance, String fieldName, Class<T> type) throws IllegalAccessException {
        Field field = findFieldByName(fieldName, instance.getClass());
        field.setAccessible(true);
        return type.cast(field.get(instance));
    }

    // -------------------------------------------------------------------------------------------------------------
    // ------------------------------------------------ Annotation
    // -------------------------------------------------------------------------------------------------------------

    public static <T extends Annotation> Annotation getAnnotationOfField(Field field, Class<T> annotationClass) {
        return field.getAnnotation(annotationClass);
    }

    public static Annotation getAnnotationOfField(String fieldName, Class ObjectClass, Class annotationClass) {
        Field field = findFieldByName(fieldName, ObjectClass);
        return field.getAnnotation(annotationClass);
    }

    /**
     * @param instanceClass Class cần lấy ra mảng các annotation
     * @return Mảng các annotation của class
     */
    public static Annotation[] getAnnotationOfClass(Class<?> instanceClass) {
        return instanceClass.getDeclaredAnnotations();
    }

    /**
     * @param instanceClass   Class cần xét
     * @param annotationClass Class của annotation
     * @return {@link Annotation} Annotation đầu tiên có class là annotationClass
     */
    public static Annotation getAnnotationOfClass(Class<?> instanceClass, Class<?> annotationClass) {
        Annotation[] annotations = instanceClass.getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().equals(annotationClass)) {
                return annotation;
            }
        }
        return null;
    }

    public static Object getValueOfAnnotationAttribute(Class<?> instanceClass, Class<?> annotationClass, String attribute)
            throws Exception {
        Annotation annotation = getAnnotationOfClass(instanceClass, annotationClass);
        Class<? extends Annotation> type;
        if (annotation != null) {
            type = annotation.annotationType();
        } else {
            throw new Exception("Cant not found annotation.");
        }

        for (Method method : type.getDeclaredMethods()) {
            if (method.getName().contentEquals(attribute)) {
                return method.invoke(annotation, (Object[]) null);
            }
        }

        throw new Exception("Cant not found attribute.");
    }

    // -------------------------------------------------------------------------------------------------------------
    // ------------------------------------------------ Others
    // -------------------------------------------------------------------------------------------------------------

    /**
     * @param map    {@link Map} chứa thông tin của đối tượng cần convert với key => tên trường, value => giá trị của trường đó
     * @param aClass Class cần convert thành
     * @return Một instance của aCLass
     * @throws InstantiationException Khi aClass không có hàm dựng không đối số
     * @throws IllegalAccessException Khi có lỗi parse String value sang giá trị phù hợp
     * @throws NoSuchFieldException   khi không tìm thấy Filed trong class
     */
    public static Object convertMapToObject(Map<String, String> map, Class aClass) throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        Object target = aClass.newInstance();

        List<Field> fields = getAllFields(aClass);

        for (Field field : fields) {
            setField(field, map.get(field.getName()), target);
        }

        return target;

    }

    /**
     * @param source Instance của aClass cần convert thành map
     * @param aClass Class của đối tượng cần xử lí
     * @return Một map chứa thông tin của đối tượng cần convert với key => tên trường, value => giá trị của trường đó
     * @throws InstantiationException Khi aClass không có hàm dựng không đối số
     * @throws IllegalAccessException có lỗi lúc lấy giá trị
     */
    public static Map<String, String> convertObjectToMap(Object source, Class aClass) throws InstantiationException, IllegalAccessException {
        Map<String, String> map = new LinkedHashMap<>();

        List<Field> fields = getAllFields(aClass);

        Class<?> targetType;
        Object objectValue;
        for (Field field : fields) {
            field.setAccessible(true);
            targetType = field.getType();
            objectValue = targetType.newInstance();

            map.put(field.getName(), String.valueOf(field.get(objectValue)));
        }

        return map;
    }

    public static List<Pair<String, Class>> getFieldAsPairList(Class aClass, String... properties) {
        List<Field> allFields = getAllFields(aClass);
        List<Pair<String, Class>> result = new LinkedList<>();


        for (String property : properties) {
            for (Field field : allFields) {
                if (field.getName().equals(property)) {
                    result.add(new Pair<>(field.getName(), field.getType()));
                }
            }
        }
        return result;
    }

    public static Map getFieldAsMap(Class aClass, String... properties) {
        Map<String, Class> result = new LinkedHashMap();
        List<Field> allFields = getAllFields(aClass);


        for (String property : properties) {
            for (Field field : allFields) {
                if (field.getName().equals(property)) {
                    result.put(field.getName(), field.getType());
                }
            }
        }
        return result;
    }


}
