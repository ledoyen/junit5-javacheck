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

    private final Set<ListGenerator> listGenerators = new HashSet<>();

    GeneratorRegistry() {
        ServiceLoader.load(ListGenerator.class).forEach(listGenerators::add);
    }

    boolean supports(Type type) {
        return listGenerators.stream().filter(g -> g.supports(type)).findAny().isPresent();
    }

    ListGenerator instanciate(Class<? extends ListGenerator> generatorClass) {
        return newInstance(generatorClass);
    }

    ListGenerator getForType(Type type) {
        return listGenerators.stream().filter(g -> g.supports(type)).findFirst().get();
    }
}
