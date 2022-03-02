package com.jacoobia.jcommands.api.annotations;

import java.lang.annotation.*;

/**
 * The base command annotation for a multi-subcommand processor
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BaseCommand {
}
