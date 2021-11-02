package com.phoenix.common.structure.imp;


import com.phoenix.common.structure.DefaultTuple;
import org.junit.Test;

public class DoubleDefaultTupleImplTest {

    @Test
    public void testCreateTuple() {
        String[] exprs = {"a", "b", "c"};
        DefaultTuple defaultTuple = new DoubleDefaultTupleImpl(exprs, "1", 2, "3", 4);

        System.out.println(defaultTuple.size());
        System.out.println(defaultTuple.get(3, Integer.class));
        System.out.println(defaultTuple.get("a", String.class));
    }

}