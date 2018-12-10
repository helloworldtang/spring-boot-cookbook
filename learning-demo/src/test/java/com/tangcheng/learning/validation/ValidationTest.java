package com.tangcheng.learning.validation;

import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Spring MVC - @Valid on list of beans in REST service
 * <p>
 * The @Valid annotation is part of the standard JSR-303 Bean Validation API, and is not a Spring-specific construct.
 * Spring MVC will validate a @Valid object after binding so-long as an appropriate Validator has been configured.
 *
 * @Valid is a JSR-303 annotation and JSR-303 applies to validation on JavaBeans.
 * A java.util.List is not a JavaBean (according to the official description of a JavaBean), hence it cannot be validated directly using a JSR-303 compliant validator.
 * This is supported by two observations.
 * <p>
 * https://stackoverflow.com/questions/17207766/spring-mvc-valid-on-list-of-beans-in-rest-service
 * https://github.com/manish-in-java/stackoverflow-questions/tree/master/17207766
 * @author: tangcheng
 * @description:
 * @since: Created in 2018/12/07 16:37
 */
public class ValidationTest {


    @Test
    public void testBeanValidationDirectly() {
        final List<Bean> beans = Arrays.asList(new Bean(0, null), new Bean(1000, null));
        final Set<ConstraintViolation<List<Bean>>> violations = getValidator().validate(beans);

        System.out.println(violations);

        assertThat(violations).isNotNull();
        assertThat(violations).isEmpty();
    }


    @Test
    public void givenWrappedBean_thenValidation() {
        final Wrapper wrapper = new Wrapper(new Bean(0, null), new Bean(1000, null));
        final Set<ConstraintViolation<Wrapper>> violations = getValidator().validate(wrapper);

        System.out.println(violations);

        assertThat(violations).isNotNull();
        assertThat(violations).isNotEmpty();
    }

    private Validator getValidator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

}