package com.phoenix.common.reflection.test;

/**
 */
public class PublicChild extends Parent {
    static final String VALUE = "child";

    @Override
    public void parentProtectedAnnotatedMethod() {
    }

    @Override
    public void parentNotAnnotatedMethod() {
    }

    @Annotated
    private void privateAnnotatedMethod() {
    }

    @Annotated
    public void publicAnnotatedMethod() {
    }
}

