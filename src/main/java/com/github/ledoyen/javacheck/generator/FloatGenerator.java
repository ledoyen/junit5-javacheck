package com.github.ledoyen.javacheck.generator;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.platform.commons.meta.API;

import com.github.ledoyen.javacheck.ListGenerator;

import static org.junit.platform.commons.meta.API.Usage.Experimental;

/**
 * {@link ListGenerator} for {@link int}.
 */
@API(Experimental)
public class FloatGenerator implements ListGenerator {

    @Override
    public boolean supports(Type type) {
        return float.class.equals(type) || Float.class.equals(type);
    }

    @Override
    public List<?> generateValues() {
        List<Float> values = new ArrayList<>();
        for(int s : pair(0, 1)) {
            for(int e : pair(0, 0xfe)) {
                for(int m : pair(0, 0x7fffff)) {
                    values.add(Float.intBitsToFloat((s << 31) | (e << 23) | m));
                }
            }
        }
        return values;
    }

    private int[] pair(int a, int b) {
        return new int[]{a, b};
    }
}
