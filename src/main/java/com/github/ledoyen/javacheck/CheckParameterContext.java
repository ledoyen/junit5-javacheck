package com.github.ledoyen.javacheck;

import org.junit.platform.commons.meta.API;

import java.lang.reflect.Parameter;
import java.util.Collections;
import java.util.List;

import static org.junit.platform.commons.meta.API.Usage.Internal;

@API(Internal)
class CheckParameterContext {

    private final int index;
    private final Parameter parameter;
    private final List<Object> values;

    CheckParameterContext(int index, Parameter parameter, List<Object> values) {
        this.index = index;
        this.parameter = parameter;
        this.values = values;
    }

    Parameter getParameter() {
        return parameter;
    }

    int getIndex() {
        return index;
    }

    List<Object> getValues() {
        return Collections.unmodifiableList(values);
    }
}
