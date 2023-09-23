package cft.shift.lab.b4lmor.rangemerger.utils.validation;

import cft.shift.lab.b4lmor.rangemerger.utils.validation.validator.IntervalDTOLengthValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = IntervalDTOLengthValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface IntervalDTOLengthValidation {

    String message() default "(Bad length) Wrong interval format. Try: '[a; b]', where a <= b.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
