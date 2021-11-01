package com.phoenix.common.reflection.test;

/**
 */
class Parent implements Foo {
    public String s = "s";
    protected boolean b = false;
    int i = 0;
    @SuppressWarnings("unused")
    private final double d = 0.0;

    @Override
    public void doIt() {
    }

    @Annotated
    protected void parentProtectedAnnotatedMethod() {
    }

    public void parentNotAnnotatedMethod() {
    }
}