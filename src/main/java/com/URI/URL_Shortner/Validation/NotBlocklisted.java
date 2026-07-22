package com.URI.URL_Shortner.Validation;


import jakarta.validation.Constraint;
import org.hibernate.validator.internal.constraintvalidators.bv.NotBlankValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
//@Constraint(validatedBy = NotBlocklistedValidator.class)
public @interface NotBlocklisted {
}
