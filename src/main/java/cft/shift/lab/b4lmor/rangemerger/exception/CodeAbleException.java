package cft.shift.lab.b4lmor.rangemerger.exception;

public class CodeAbleException extends RuntimeException {
    protected final int code;

    public CodeAbleException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
