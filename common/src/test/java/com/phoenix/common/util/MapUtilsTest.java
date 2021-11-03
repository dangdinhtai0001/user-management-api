package com.phoenix.common.util;

import com.phoenix.common.util.clone.Foo;
import com.phoenix.common.util.clone.Parent;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;


public class MapUtilsTest {

    @Test
    public void testConvertMap2Objects() throws Exception {
        Map<String, Object> map = new HashMap<>();

        map.put("name", "name__");
        map.put("address", "address__");
        map.put("age", 18);

        Foo foo = MapUtils.convertMap2Object(map, Foo.class);

        System.out.println(foo);
    }

    @Test
    public void testConvertMap2Objects2() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        Map<String, Object> parentMap = new HashMap<>();
        Map<String, Object> childMap = new HashMap<>();
        Map<String, Object> fooMap = new HashMap<>();

        fooMap.put("name", "name__");
        fooMap.put("address", "address__");
        fooMap.put("age", 18);

        childMap.put("id", "child id__");
        childMap.put("foo", fooMap);

        parentMap.put("id", "pid__");
        parentMap.put("child", childMap);

        Parent parent = MapUtils.convertMap2Object(parentMap, Parent.class);

        System.out.println(parent);
    }

    @Test
    public void testGetProperty() throws Exception {
        Map<String, Object> parentMap = new HashMap<>();
        Map<String, Object> childMap = new HashMap<>();
        Map<String, Object> fooMap = new HashMap<>();

        fooMap.put("name", "name__");
        fooMap.put("address", "address__");
        fooMap.put("age", 18);

        childMap.put("id", "child id__");
        childMap.put("foo", fooMap);

        parentMap.put("id", "pid__");
        parentMap.put("child", childMap);

        String id = MapUtils.getProperty(parentMap, "id", ".");
        String childId = MapUtils.getProperty(parentMap, "child.id", ".");
        String fooName = MapUtils.getProperty(parentMap, "child.foo.name", ".");
        Integer fooAge = MapUtils.getProperty(parentMap, "child.foo.age", ".");

        System.out.println("id = " + id);
        System.out.println("child id = " + childId);
        System.out.println("foo name = " + fooName);
        System.out.println("foo age = " + fooAge);
    }
}