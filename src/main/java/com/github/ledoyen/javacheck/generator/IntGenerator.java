package com.github.ledoyen.javacheck.generator;

import com.github.ledoyen.javacheck.Generator;
import org.junit.platform.commons.meta.API;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import static org.junit.platform.commons.meta.API.Usage.Experimental;

/**
 * {@link Generator} for {@link int} and {@link Integer} values.
 */
@API(Experimental)
public class IntGenerator implements Generator {

    @Override
    public boolean supports(Type type) {
        return int.class.equals(type) || Integer.class.equals(type);
    }

    @Override
    public List<Object> generateValues() {
        return Arrays.asList(Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 1);
    }
}
