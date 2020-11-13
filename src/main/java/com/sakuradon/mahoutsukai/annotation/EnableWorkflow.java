package com.sakuradon.mahoutsukai.annotation;

import java.lang.annotation.*;

/**
 * @author SakuraDon
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EnableWorkflow {

    String value() default "";

}
