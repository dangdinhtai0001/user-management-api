package com.phoenix.common.util;

import org.junit.Test;

public class ValidateTest {
    @Test
    public void testIsTrueWithExpression() {
        Validate.isTrue(false, "The expression is false");
    }

    @Test()
    public void testIsTrueWithExpressionAndLongValue() {
        Validate.isTrue(false, "The Value must be: %s", 1L);
    }

    @Test()
    public void testIsTrueWithExpressionAndDoubleValue() {
        Validate.isTrue(false, "The Value must be: %s", 1.0);
    }
}
