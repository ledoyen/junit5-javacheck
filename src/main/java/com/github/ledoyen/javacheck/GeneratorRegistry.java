package com.github.ledoyen.javacheck;

import org.junit.platform.commons.meta.API;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.ServiceLoader;
import java.util.Set;

import static org.junit.platform.commons.meta.API.Usage.Internal;
import static org.junit.platform.commons.util.ReflectionUtils.newInstance;

@API(Internal)
class GeneratorRegistry {

    private final Set<Generator> generators = new HashSet<>();

    GeneratorRegistry() {
        ServiceLoader.load(Generator.class).forEach(generators::add);
    }

    boolean supports(Type type) {
        return generators.stream().filter(g -> g.supports(type)).findAny().isPresent();
    }

    Generator instanciate(Class<? extends Generator> generatorClass) {
        return newInstance(generatorClass);
    }

    Generator getForType(Type type) {
        return generators.stream().filter(g -> g.supports(type)).findFirst().get();
    }
}
