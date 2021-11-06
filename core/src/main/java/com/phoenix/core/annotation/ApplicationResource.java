package com.phoenix.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ApplicationResource {
    String displayResource() default "";

    String description() default "";

    String httpMethod() default "POST";

    boolean isEnabled() default true;
}
