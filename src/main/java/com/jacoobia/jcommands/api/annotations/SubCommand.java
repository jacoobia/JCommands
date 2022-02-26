package com.jacoobia.jcommands.api.annotations;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SubCommand {
    String[] value() default {};
}