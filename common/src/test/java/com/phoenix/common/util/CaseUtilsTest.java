package com.phoenix.common.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CaseUtilsTest {

    @Test
    public void testToCamelCase(){
        String raw = "abc_def";
        String result =CaseUtils.toCamelCase(raw,false, '_');

        Assertions.assertEquals(result, "abcDef");
    }

}