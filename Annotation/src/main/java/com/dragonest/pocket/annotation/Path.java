package com.dragonest.pocket.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Dsh  imkobedroid@gmail.com
 * @date 11/30/21
 * @project Plugin Dev
 * @instructions
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface Path {
    String value() default "";
}