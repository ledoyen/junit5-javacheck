package com.github.ledoyen.javacheck.generator;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.junit.platform.commons.meta.API;

import com.github.ledoyen.javacheck.ListGenerator;

import static org.junit.platform.commons.meta.API.Usage.Experimental;

/**
 * {@link ListGenerator} for {@link int}.
 */
@API(Experimental)
public class DoubleGenerator implements ListGenerator {

    @Override
    public boolean supports(Type type) {
        return double.class.equals(type) || Double.class.equals(type);
    }

    @Override
    public List<?> generateValues() {
        List<Double> values = new ArrayList<>();
        for(long s : pair(0L, 1L)) {
            for(long e : pair(0L, 0x7feL)) {
                for(long m : pair(0L, 0xfffffffffffffL)) {
                    values.add(Double.longBitsToDouble((s << 63) | (e << 52) | m));
                }
            }
        }
        return values;
    }

    private long[] pair(long a, long b) {
        return new long[]{a, b};
    }
}
