package com.github.ledoyen.javacheck;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.meta.API;

import static org.junit.platform.commons.meta.API.Usage.Experimental;

/**
 * Tag a JUnit 5 test method for value injection, depending on available generators.
 *
 * @see ListGenerator
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@TestTemplate
@ExtendWith(JavacheckExtension.class)
@API(Experimental)
public @interface Check {

}
