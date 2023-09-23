package cft.shift.lab.b4lmor.rangemerger.utils.validation;

import cft.shift.lab.b4lmor.rangemerger.utils.validation.validator.IntervalDTOLengthValidator;
import cft.shift.lab.b4lmor.rangemerger.utils.validation.validator.IntervalDTOValueValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = IntervalDTOValueValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface IntervalDTOValueValidation {

    String message() default "(Bad letter format) Wrong interval format. Try to use only 1 letter in interval borders.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
