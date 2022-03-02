package com.jacoobia.jcommands.api.annotations;

import java.lang.annotation.*;

/**
 * The annotation for a command
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Command {
    /**
     * Returns the command qualifiers
     * @return the command qualifiers
     */
    String[] value() default {};
}