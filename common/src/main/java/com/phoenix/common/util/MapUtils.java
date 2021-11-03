package com.phoenix.common.util;

import com.phoenix.common.reflection.ConstructorUtils;
import com.phoenix.common.reflection.MethodUtils;

import java.util.Map;

public class MapUtils {
    public static <T> T convertMap2Object(Map<String, Object> map, Class<T> clazz) {
        if (map == null || map.isEmpty()) {
            return null;
        }

        try {
            T obj = ConstructorUtils.invokeConstructor(clazz);

            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String propertyName = entry.getKey();
                Object value = entry.getValue();

                if (value instanceof Map) {
                    System.out.println("value is map");
                } else {
                    String setMethodName = "set" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
                    MethodUtils.invokeMethod(obj, true, setMethodName, value);
                }
            }
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * <ul>
     *   <li>Hàm trả về giá trị của 1 map theo key</li>
     *   <li>Trong trường hợp key không tồn tại trong map hoặc map null thì trả về null</li>
     * </ul>
     *
     * <p> Ví dụ: map = {a=1, b=2, c=3, d={e=4, f=5}, g={h=6, i=7}} </p>
     * <p> Để lấy giá trị của map.d.e (4) thì các </p>
     * <ul>
     *     <li>propertyName: d.e</li>
     *     <li>splitRegex: .</li>
     * </ul>
     *
     * @param map          Map cần lấy thông tin
     * @param propertyName Tên thuộc tính cần lấy
     * @param splitRegex   Phân tách các phần tử của thuộc tính
     * @param <T>          Kiểu trả về
     * @return
     */
    public static <T> T getProperty(Map<String, Object> map, String propertyName, String splitRegex) {
        // Kiểm tra xem nếu map ban đầu rỗng thì trả về null
        if (map == null || map.isEmpty()) {
            return null;
        }

        // Kiểm tra xem propertyName có phải là một chuỗi có chứa dấu phân cách hay không
        boolean isKeepGoing = propertyName.contains(splitRegex);

        String property;
        // Nếu propertyName có chứa dấu phân cách thì lấy phần tử đầu tiên trước dấu phân cách
        if (isKeepGoing) {
            property = propertyName.substring(0, propertyName.indexOf(splitRegex));
        }
        // Nếu không thì lấy toàn bộ chuỗi
        else {
            property = propertyName;
        }

        // Nếu map chứa propertyName thì trả về giá trị của property
        Object propertyValue = map.getOrDefault(property, null);

        // Nếu propertyValue là một map thì gọi lại hàm này nhưng với map là propertyValue và propertyName là subProperty
        if (isKeepGoing && propertyValue instanceof Map) {
            String subProperty = propertyName.substring(propertyName.indexOf(splitRegex) + 1);
            //noinspection unchecked
            return getProperty((Map<String, Object>) propertyValue, subProperty, splitRegex);
        }

        //noinspection unchecked
        return (T) propertyValue;
    }
}
