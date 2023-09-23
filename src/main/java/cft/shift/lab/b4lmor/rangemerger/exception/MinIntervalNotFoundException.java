package cft.shift.lab.b4lmor.rangemerger.exception;

public class MinIntervalNotFoundException extends RuntimeException {
    public MinIntervalNotFoundException() {
        super("api.intervals.get-min.api-responses.404.description");
    }
}
