package com.github.ledoyen.javacheck.generator;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import org.junit.platform.commons.meta.API;

import com.github.ledoyen.javacheck.Gen;
import com.github.ledoyen.javacheck.ListGenerator;

import static org.junit.platform.commons.meta.API.Usage.Experimental;

/**
 * {@link ListGenerator} for {@link int}.
 */
@API(Experimental)
public class BooleanGenerator implements ListGenerator {

    @Override
    public boolean supports(Type type) {
        return boolean.class.equals(type) || Boolean.class.equals(type);
    }

    @Override
    public List<?> generateValues() {
        return Arrays.asList(true, false);
    }
}
