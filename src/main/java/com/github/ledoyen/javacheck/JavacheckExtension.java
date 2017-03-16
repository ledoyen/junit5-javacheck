package com.github.ledoyen.javacheck;

import java.lang.reflect.Executable;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ContainerExtensionContext;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.TestExtensionContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContextProvider;
import org.junit.platform.commons.meta.API;

import static java.util.Collections.singletonList;
import static org.junit.platform.commons.meta.API.Usage.Internal;

@API(Internal)
class JavacheckExtension implements TestTemplateInvocationContextProvider, BeforeEachCallback, BeforeTestExecutionCallback {

    private GeneratorRegistry generatorRegistry = new GeneratorRegistry();

    @Override
    public boolean supports(ContainerExtensionContext context) {
        return context.getElement().map(ae -> ae.getAnnotation(Check.class)).isPresent();
    }

    @Override
    public Stream<TestTemplateInvocationContext> provide(ContainerExtensionContext context) {
        Executable testMethod = context.getTestMethod().get();

        Parameter[] parameters = testMethod.getParameters();
        List<CheckParameterContext> parameterContexts = new ArrayList<>();
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            final ListGenerator generator;
            if (parameter.isAnnotationPresent(Generated.class)) {
                generator = generatorRegistry.instanciate(parameter.getAnnotation(Generated.class).value());
            } else if (generatorRegistry.supports(parameter.getParameterizedType())) {
                generator = generatorRegistry.getForType(parameter.getParameterizedType());
            } else {
                generator = null;
            }
            if(generator != null) {
                parameterContexts.add(new CheckParameterContext(i, parameter, ListBuilder.asObjectList(generator.generateValues())));
            }
        }

        List<List<Object>> initialMatrix = createInitialMatrix(parameterContexts.get(0).getValues());

        List<List<Object>> elements = parameterContexts.subList(1, parameterContexts.size()).stream().map(CheckParameterContext::getValues).collect(Collectors.toList());

        List<List<Object>> valueMatrix = appendMultiplicity(initialMatrix, elements);

        List<TestTemplateInvocationContext> contexts = new ArrayList<>(valueMatrix.size());

        valueMatrix.forEach(line -> {
            contexts.add(buildInvocationContext(parameterContexts, line));
        });

        return contexts.stream();
    }

    private TestTemplateInvocationContext buildInvocationContext(List<CheckParameterContext> parameterContexts, List<Object> line) {
        return new TestTemplateInvocationContext() {
            public String getDisplayName(int invocationIndex) {
                return String.valueOf(line);
            }

            public List<Extension> getAdditionalExtensions() {
                List<Extension> extensions = new ArrayList<>();
                for (int i = 0; i < line.size(); i++) {
                    extensions.add(new FixedValueParameterResolver(parameterContexts.get(i).getIndex(), line.get(i)));
                }
                return extensions;
            }
        };
    }

    private List<List<Object>> createInitialMatrix(List<Object> elements) {
        List<List<Object>> matrix = new ArrayList<>();
        elements.forEach(e -> matrix.add(singletonList(e)));
        return matrix;
    }

    private List<List<Object>> appendMultiplicity(List<List<Object>> matrix, List<List<Object>> elements) {
        if (elements.size() == 0) {
            return matrix;
        } else {
            List<Object> head = elements.get(0);
            List<List<Object>> tail = elements.subList(1, elements.size());
            return appendMultiplicity(computeNewMatrix(matrix, head), tail);
        }
    }

    private List<List<Object>> computeNewMatrix(List<List<Object>> matrix, List<Object> elements) {
        List<List<Object>> newMatrix = new ArrayList<>();
        for (List<Object> existingItem : matrix) {
            for (Object element : elements) {
                List<Object> copy = new ArrayList<>(existingItem);
                copy.add(element);
                newMatrix.add(copy);
            }
        }
        return newMatrix;
    }

    @Override
    public void beforeEach(TestExtensionContext context) throws Exception {
        System.out.println("each: " + context.getDisplayName());
    }

    @Override
    public void beforeTestExecution(TestExtensionContext context) throws Exception {
        System.out.println("test: " + context.getDisplayName());
    }
}
