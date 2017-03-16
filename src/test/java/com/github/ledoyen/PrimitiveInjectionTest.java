package com.github.ledoyen;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

import com.github.ledoyen.javacheck.Check;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

public class PrimitiveInjectionTest {

    @Check
    public void absolute_value_should_not_be_negative(int a) {
        assertThat(Math.abs(a)).isGreaterThanOrEqualTo(0);
    }

    @Check
    public void multiplication_of_positive_values_should_be_positive(int a, Integer b) {
        assertThat(Math.abs(a) * Math.abs(b)).isGreaterThanOrEqualTo(0);
    }
}
