package com.github.ledoyen.javacheck;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.platform.commons.meta.API;

import static org.junit.platform.commons.meta.API.Usage.Internal;

@API(Internal)
class FixedValueParameterResolver implements ParameterResolver {

    private final int position;
    private final Object value;

    FixedValueParameterResolver(int position, Object value) {
        this.position = position;
        this.value = value;
    }
    
    @Override
    public boolean supports(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getIndex() == position;
    }

    @Override
    public Object resolve(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return value;
    }
}
