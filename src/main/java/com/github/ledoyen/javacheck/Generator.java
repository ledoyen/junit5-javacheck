package com.github.ledoyen.javacheck;

import org.junit.platform.commons.meta.API;

import java.lang.reflect.Type;
import java.util.List;

import static org.junit.platform.commons.meta.API.Usage.Experimental;

/**
 * Generate values for a specific {@link Type}.<br/>
 * A {@link Generator} can be declared two ways:
 * <ul>
 * <li>by using the {@link Generated @Generated} annotation on a test method parameter</li>
 * <li>by declaring it through the {@link java.util.ServiceLoader} mechanism.
 * <br/>In this case, it is available for any supported parameter.
 * </li>
 * </ul>
 * @see Generated
 */
@API(Experimental)
public interface Generator {

    boolean supports(Type type);

    List<Object> generateValues();
}
