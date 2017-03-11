# JavaCheck: Property-based testing for JUnit 5

Java library for property-based testing, inpired by Scalacheck.

## Quick start

```java
public class SampleTest {

    @Check
    public void absolute_value_should_not_be_negative(int a) {
        assertThat(Math.abs(a)).isGreaterThanOrEqualTo(0);
    }
}
```

This test method will be executed several times with various values of `a`.
This test method will *fail*, as `Integer.MIN_VALUE` as no matching positive value.

This is exactly the kind of thing *JavaCheck* will demonstrate, by injecting both specific values and random values.

## Extension
### Specific Generator

Specific **Generators** can be declared using the `@Generated` annotation :

```java
@Check
public void absolute_value_should_not_be_negative(@Generated(IntGenerator.class) int a) {
    assertThat(Math.abs(a)).isGreaterThanOrEqualTo(0);
}
```

### Registering Generator

**Generators** can be registered using the java `ServiceLoader` mechanism.

Add you custom-made `Generator` in the file `classpath:META-INF/services/com.github.ledoyen.javacheck.Generator` to make in available for all `@Check` marked test method.