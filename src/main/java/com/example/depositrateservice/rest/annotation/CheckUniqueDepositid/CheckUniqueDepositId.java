package com.example.depositrateservice.rest.annotation.CheckUniqueDepositid;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(ElementType.FIELD) // Применяется к полям
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DepositIdValidator.class)
public @interface CheckUniqueDepositId {


    String message() default "Deposit id is not unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
