package com.URI.URL_Shortner.Validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.hibernate.validator.internal.constraintvalidators.bv.NotBlankValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotBlocklistedValidator.class)
public @interface NotBlocklisted {
    String message() default "This domain is not allowed";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
