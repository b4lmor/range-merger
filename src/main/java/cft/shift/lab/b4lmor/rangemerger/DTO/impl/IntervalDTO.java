package cft.shift.lab.b4lmor.rangemerger.DTO.impl;

import cft.shift.lab.b4lmor.rangemerger.DTO.IIntervalDTO;
import cft.shift.lab.b4lmor.rangemerger.exception.EmptyIntervalDTOException;
import cft.shift.lab.b4lmor.rangemerger.utils.validation.IntervalDTOLengthValidation;
import cft.shift.lab.b4lmor.rangemerger.utils.validation.IntervalDTOOrderValidation;
import cft.shift.lab.b4lmor.rangemerger.utils.validation.IntervalDTOValueValidation;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;

@IntervalDTOLengthValidation
@IntervalDTOValueValidation
@IntervalDTOOrderValidation
@Schema(name = "IntervalDTO", example = "[-100, 55]", description = "interval of digits or letters (!)")
public class IntervalDTO<T extends Comparable<T>>
        extends ArrayList<T>
        implements IIntervalDTO<T> {
    public IntervalDTO() {
        super();
    }
    public IntervalDTO(T start, T end) {
        super();
        if (this.isEmpty()) {
            this.add(start);
            this.add(end);
        }
    }
    @Override
    public T getStart() {
        if (!this.isEmpty()) {
            return this.get(0);
        } else {
            throw new EmptyIntervalDTOException();
        }
    }
    @Override
    public T getEnd() {
        if (!this.isEmpty()) {
            return this.get(1);
        } else {
            throw new EmptyIntervalDTOException();
        }
    }
}
