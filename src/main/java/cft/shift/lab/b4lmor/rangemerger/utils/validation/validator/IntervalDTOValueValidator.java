package cft.shift.lab.b4lmor.rangemerger.utils.validation.validator;

import cft.shift.lab.b4lmor.rangemerger.DTO.impl.IntervalDTO;
import cft.shift.lab.b4lmor.rangemerger.utils.validation.IntervalDTOValueValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IntervalDTOValueValidator
        implements ConstraintValidator<IntervalDTOValueValidation, IntervalDTO<?>> {

    @Override
    public boolean isValid(IntervalDTO<?> comparables, ConstraintValidatorContext constraintValidatorContext) {
        if (comparables.get(0) instanceof String) {
            return ((String) comparables.get(0)).length() == 1
                    && ((String) comparables.get(1)).length() == 1;
        }
        return true;
    }
}