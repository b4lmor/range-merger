package cft.shift.lab.b4lmor.rangemerger.DTO;

public interface IIntervalDTO<T extends Comparable<T>> {
    T getStart();
    T getEnd();
}
