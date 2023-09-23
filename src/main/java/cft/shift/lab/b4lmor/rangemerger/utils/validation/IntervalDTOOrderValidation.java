package cft.shift.lab.b4lmor.rangemerger.utils.validation;

import cft.shift.lab.b4lmor.rangemerger.utils.validation.validator.IntervalDTOOrderValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = IntervalDTOOrderValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface IntervalDTOOrderValidation {

    String message() default "(Bad borders) Wrong interval format. Left border must be lower than right border.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
