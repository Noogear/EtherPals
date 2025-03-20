package cn.etherPals.config.impl;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Comments {
    String[] value() default {};

    String[] cn_value() default {};
}