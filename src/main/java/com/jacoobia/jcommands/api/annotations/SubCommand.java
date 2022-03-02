package com.jacoobia.jcommands.api.annotations;

import java.lang.annotation.*;

/**
 * The annotation for a subcommand
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SubCommand {
    /**
     * Returns the subcommand qualifiers
     * @return the subcommand qualifiers
     */
    String[] value() default {};
}