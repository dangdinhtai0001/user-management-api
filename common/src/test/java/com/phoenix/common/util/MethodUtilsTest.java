package com.phoenix.common.util;

import com.phoenix.common.reflection.MethodUtils;
import junit.framework.TestCase;

public class MethodUtilsTest extends TestCase {

    public void testGetVarArgs() {
        String[] args = {"1", "2", "3"};
        Class<?>[] types = {String.class, String.class, String.class};
        MethodUtils.getVarArgs(args, types);
    }
}