package com.phoenix.common.util;

import junit.framework.TestCase;

import java.util.List;

public class ClassUtilsTest extends TestCase {
    public void testConvertClassesToClassNames() {
        List<String> list = ClassUtils.convertClassesToClassNames(List.of(new Class[]{String.class, Integer.class}));

        for (String className : list) {
            System.out.println(className);
        }
    }

    public void testConvertClassNamesToClasses() {
        List<Class<?>> list = ClassUtils.convertClassNamesToClasses(List.of(new String[]{"java.lang.String", "java.lang.Integer"}));

        for (Class<?> class_: list) {
            System.out.println(class_);
        }
    }
}