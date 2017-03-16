package com.github.ledoyen.javacheck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class ListBuilder<T> {
    private final List<T> list = new ArrayList<T>();

    private ListBuilder() {}

    public static List<Object> asObjectList(List<?> list) {
        return (List<Object>) (Object) list;
    }

    public static <T> ListBuilder<T> of(T... elements) {
        ListBuilder<T> builder = new ListBuilder<>();
        Arrays.stream(elements).forEach(builder.list::add);
        return builder;
    }

    public ListBuilder<T> add(T element) {
        list.add(element);
        return this;
    }

    public List<T> build() {
        return Collections.unmodifiableList(list);
    }
}
