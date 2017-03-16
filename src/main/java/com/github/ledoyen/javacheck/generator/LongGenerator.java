package com.github.ledoyen.javacheck.generator;

import java.lang.reflect.Type;
import java.util.List;

import org.junit.platform.commons.meta.API;

import com.github.ledoyen.javacheck.Gen;
import com.github.ledoyen.javacheck.ListGenerator;

import static org.junit.platform.commons.meta.API.Usage.Experimental;

/**
 * {@link ListGenerator} for {@link long}.
 */
@API(Experimental)
public class LongGenerator implements ListGenerator {

    @Override
    public boolean supports(Type type) {
        return long.class.equals(type) || Long.class.equals(type);
    }

    @Override
    public List<?> generateValues() {
        return Gen.chooseNum(Long.MIN_VALUE, Long.MAX_VALUE);
    }
}
