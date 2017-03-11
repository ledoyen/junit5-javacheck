package com.github.ledoyen.javacheck;

import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.meta.API;

import java.lang.annotation.*;

import static org.junit.platform.commons.meta.API.Usage.Experimental;

/**
 * Tag a JUnit 5 test method for value injection, depending on available generators.
 * @see Generator
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@TestTemplate
@ExtendWith(JavacheckExtension.class)
@API(Experimental)
public @interface Check {

}
