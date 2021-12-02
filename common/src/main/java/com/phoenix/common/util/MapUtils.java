package com.phoenix.common.util;

import com.phoenix.common.reflection.ConstructorUtils;
import com.phoenix.common.reflection.FieldUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapUtils {
    /**
     * <h1> Hàm convert một map sang thành object</h1>
     *
     * <ul>
     *     <li>Hàm trả về 1 instance của đối tượng cần đc convert thành</li>
     *     <li>Nếu map ban đầu rỗng thì trả về null</li>
     *     <li>Hàm này có đệ quy</li>
     * </ul>
     *
     * @param map   Map cần lấy thông tin
     * @param clazz Kiểu của thuộc tính cần lấy
     * @param <T>   Kiểu trả về
     * @return Một instance dựa theo kiểu trả về
     * @throws InvocationTargetException @see {@link ConstructorUtils#invokeConstructor(Class, Object...)}
     * @throws NoSuchMethodException     @see {@link ConstructorUtils#invokeConstructor(Class, Object...)}
     * @throws IllegalAccessException    @see {@link ConstructorUtils#invokeConstructor(Class, Object...)}
     * @throws InstantiationException    @see {@link ConstructorUtils#invokeConstructor(Class, Object...)}
     */
    public static <T> T convertMap2Object(Map<String, Object> map, Class<T> clazz) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        // Kiểm tra xem map có rỗng hay không
        if (map == null || map.isEmpty()) {
            return null;
        }

        // Tạo instance của class kết quả
        T instance = ConstructorUtils.invokeConstructor(clazz);

        //Lấy ra danh sách các field của class
        List<Field> fieldNames = FieldUtils.getAllFieldsList(clazz);

        Object value = null;
        // Duyệt qua từng field
        for (Field field : fieldNames) {
            // Lấy ra giá trị value trên map theo key là fieldName
            value = map.get(field.getName());

            if (value instanceof Map) {
                //Nếu value là một instance của map thì gọi hàm convertMap2Object để convert sang object
                //noinspection unchecked
                Object temp = convertMap2Object((Map<String, Object>) value, field.getType());
                FieldUtils.writeField(field, instance, temp, true);
            } else {
                // Nếu value không phải là một instance của map thì gán giá trị value cho field
                FieldUtils.writeField(field, instance, map.getOrDefault(field.getName(), null), true);
            }
        }

        return instance;
    }


    /**
     * <h1> Hàm convert list Map thành list Object</h1>
     * <p>
     * Chạy vòng lặp theo list và gọi hàm {@link #convertMap2Object(Map, Class)} để convert từng map thành object
     */
    public static <T> List<T> convertMap2Object(List<Map<String, Object>> list, Class<T> clazz) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        List<T> result = new ArrayList<>(list.size());

        if (list.isEmpty()) {
            return result;
        }

        for (Map<String, Object> map : list) {
            result.add(convertMap2Object(map, clazz));
        }

        return result;
    }

    /**
     * <h1> Hàm lấy ra value của map nhiều level theo key path </h1>
     *
     * <ul>
     *   <li>Hàm trả về giá trị của 1 map theo key path</li>
     *   <li>Trong trường hợp key không tồn tại trong map hoặc map null thì trả về null</li>
     *   <li>Hàm này có đệ quy</li>
     * </ul>
     *
     * <p> Ví dụ: map = {a=1, b=2, c=3, d={e=4, f=5}, g={h=6, i=7}} </p>
     * <p> Để lấy giá trị của map.d.e (4) thì các </p>
     * <ul>
     *     <li>propertyPath: d.e</li>
     *     <li>splitRegex: .</li>
     * </ul>
     *
     * @param map          Map cần lấy thông tin
     * @param propertyPath Tên thuộc tính cần lấy
     * @param splitRegex   Phân tách các phần tử của thuộc tính
     * @param <T>          Kiểu trả về
     * @return Value của thuộc tính theo propertyPath
     */
    public static <T> T getProperty(Map<String, Object> map, String propertyPath, String splitRegex) {
        // Kiểm tra xem nếu map ban đầu rỗng thì trả về null
        if (map == null || map.isEmpty()) {
            return null;
        }

        // Kiểm tra xem propertyPath có phải là một chuỗi có chứa dấu phân cách hay không
        boolean isKeepGoing = propertyPath.contains(splitRegex);

        String property;
        // Nếu propertyPath có chứa dấu phân cách thì lấy phần tử đầu tiên trước dấu phân cách
        if (isKeepGoing) {
            property = propertyPath.substring(0, propertyPath.indexOf(splitRegex));
        }
        // Nếu không thì lấy toàn bộ chuỗi
        else {
            property = propertyPath;
        }

        // Nếu map chứa propertyPath thì trả về giá trị của property
        Object propertyValue = map.getOrDefault(property, null);

        // Nếu propertyValue là một map thì gọi lại hàm này nhưng với map là propertyValue và propertyPath là subProperty
        if (isKeepGoing && propertyValue instanceof Map) {
            String subProperty = propertyPath.substring(propertyPath.indexOf(splitRegex) + 1);
            //noinspection unchecked
            return getProperty((Map<String, Object>) propertyValue, subProperty, splitRegex);
        }

        //noinspection unchecked
        return (T) propertyValue;
    }

    /**
     * <h1> Hàm lấy tra value của map nhiều level theo key path </h1>
     *
     * @param map          Map cần lấy thông tin
     * @param propertyPath Tên thuộc tính cần lấy
     * @param <T>          Kiểu trả về
     * @return Value của thuộc tính theo propertyPath
     * @see #getProperty(Map, String, String)
     */
    public static <T> T getProperty(Map<String, Object> map, String propertyPath) {
        return getProperty(map, propertyPath, ".");
    }
}
