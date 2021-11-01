package com.phoenix.common.reflection.test;

/**
 */
public interface Foo {
    String VALUE = "foo";

    @Annotated
    void doIt();
}
