package com.example.tablephone.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.FIELD})
public @interface Form {
    String value() default "";
    String message() default "";
    String errorMessage() default "";
}
