package com.github.ledoyen.javacheck.generator;

import com.github.ledoyen.javacheck.Gen;
import com.github.ledoyen.javacheck.ListBuilder;
import com.github.ledoyen.javacheck.ListGenerator;
import org.junit.platform.commons.meta.API;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import static org.junit.platform.commons.meta.API.Usage.Experimental;

/**
 * {@link ListGenerator} for {@link int}.
 */
@API(Experimental)
public class IntGenerator implements ListGenerator {

    @Override
    public boolean supports(Type type) {
        return int.class.equals(type) || Integer.class.equals(type);
    }

    @Override
    public List<?> generateValues() {
        return Gen.chooseNum(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
}
