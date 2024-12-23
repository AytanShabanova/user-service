package com.example.userserviceapidesign.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EmailOrNumberValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailOrNumber {
    String message() default "Either email or number must be provided";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
