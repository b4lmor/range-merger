package cft.shift.lab.b4lmor.rangemerger.exception;

public class WrongKindNameException extends RuntimeException {
    public WrongKindNameException() {
        super("api.intervals.api-responses.400.wrong-kind.description");
    }
}
