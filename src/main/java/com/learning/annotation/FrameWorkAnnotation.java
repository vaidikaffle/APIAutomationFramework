package com.learning.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(METHOD)
public @interface FrameWorkAnnotation {

    public String[] author() default "Vaidik";
    public String[] category() default "Regression";
}
