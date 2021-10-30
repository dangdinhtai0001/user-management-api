package com.phoenix.common.structure.imp;

import com.phoenix.common.exceptions.SupportException;
import com.phoenix.common.structure.DefaultTuple;
import junit.framework.TestCase;

public class TriDefaultTupleImplTest extends TestCase {

    public void testCreate() throws SupportException {
        String[] exprs = {"a", "b", "c"};
        Class[] types = {String.class, Integer.class, String.class};

        DefaultTuple defaultTuple = new TriDefaultTupleImpl(exprs, types, "a", 1, "c");


        System.out.println(defaultTuple.get(0, String.class));
        String tmp = defaultTuple.get(0);
        System.out.println(tmp);
    }

}