package cft.shift.lab.b4lmor.rangemerger.utils.validation.validator;

import cft.shift.lab.b4lmor.rangemerger.DTO.impl.IntervalDTO;
import cft.shift.lab.b4lmor.rangemerger.utils.validation.IntervalDTOLengthValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IntervalDTOLengthValidator
        implements ConstraintValidator<IntervalDTOLengthValidation, IntervalDTO<?>> {

    @Override
    public boolean isValid(IntervalDTO<?> comparables, ConstraintValidatorContext constraintValidatorContext) {
        return comparables.size() == 2;
    }
}