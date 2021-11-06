package com.phoenix.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ApplicationResourceAction {
    String description() default "";

    String httpMethod() default "GET";

    String displayPath() default "";

    boolean isEnabled() default true;
}
