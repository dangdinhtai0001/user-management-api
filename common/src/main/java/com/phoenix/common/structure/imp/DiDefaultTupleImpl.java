package com.phoenix.common.structure.imp;

import com.google.common.collect.ImmutableList;
import com.phoenix.common.exceptions.SupportException;

import java.util.Arrays;

public class DiDefaultTupleImpl extends AbstractDefaultTuple {
    public DiDefaultTupleImpl(String[] expression, Object... args) {
        this.args = args;
        this.expression = ImmutableList.copyOf(expression);
        this.bindings = this.createBindings(Arrays.asList(expression));
    }

    @Override
    public <T> T get(int index) throws SupportException {
        throw new SupportException("This object does not support this method");
    }

    @Override
    public <T> T get(String expr) throws SupportException {
        throw new SupportException("This object does not support this method");
    }
}
