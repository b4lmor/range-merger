package cft.shift.lab.b4lmor.rangemerger.utils.validation.validator;

import cft.shift.lab.b4lmor.rangemerger.DTO.impl.IntervalDTO;
import cft.shift.lab.b4lmor.rangemerger.utils.validation.IntervalDTOOrderValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IntervalDTOOrderValidator
        implements ConstraintValidator<IntervalDTOOrderValidation, IntervalDTO<?>> {

    @Override
    public boolean isValid(IntervalDTO<?> comparables, ConstraintValidatorContext constraintValidatorContext) {
        if (comparables.get(0) instanceof String) {
            return ((String) comparables.get(0))
                    .compareTo((String) comparables.get(1)) <= 0;
        } else if (comparables.get(0) instanceof Integer) {
            return ((Integer) comparables.get(0))
                    .compareTo((Integer) comparables.get(1)) <= 0;
        } else {
            return false;
        }
    }
}