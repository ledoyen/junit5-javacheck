package com.github.ledoyen.javacheck;

import org.junit.platform.commons.meta.API;

import java.lang.annotation.*;

import static org.junit.platform.commons.meta.API.Usage.Experimental;

/**
 * Mark a JUnit 5 test-method parameter as generated.
 * @see Generator
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@API(Experimental)
public @interface Generated {

    Class<? extends Generator> value();
}
