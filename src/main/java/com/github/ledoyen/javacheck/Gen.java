package com.github.ledoyen.javacheck;

import java.util.List;

import org.junit.platform.commons.meta.API;

import static org.junit.platform.commons.meta.API.Usage.Internal;


@API(Internal)
public final class Gen {

    private static Number zero = 0;
    private static Number one = 1;
    private static Number minusOne = -1;

    private Gen() {
    }

    /**
     * Generates numbers within the given inclusive range, with
     * extra weight on zero, +/- unity and both extremities.
     */
    public static <T extends Number> List<T> chooseNum(T minT, T maxT) {
        return ListBuilder.of(minT, maxT, (T) zero, (T) one, (T) minusOne).build();
    }
}
