package cft.shift.lab.b4lmor.rangemerger.controller;

import cft.shift.lab.b4lmor.rangemerger.exception.CodeAbleException;
import cft.shift.lab.b4lmor.rangemerger.exception.EmptyIntervalDTOException;
import cft.shift.lab.b4lmor.rangemerger.exception.MinIntervalNotFoundException;
import cft.shift.lab.b4lmor.rangemerger.exception.WrongKindNameException;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springdoc.core.utils.PropertyResolverUtils;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestControllerAdvice
public class ErrorControllerAdvice {
    private static final Logger log = LoggerFactory.getLogger(ErrorControllerAdvice.class);

    private final PropertyResolverUtils propertyResolverUtils;

    @Autowired
    public ErrorControllerAdvice(PropertyResolverUtils propertyResolverUtils) {
        this.propertyResolverUtils = propertyResolverUtils;
    }

    @ExceptionHandler(EmptyIntervalDTOException.class)
    public ResponseEntity<ErrorResponse> handleEmptyIntervalException(EmptyIntervalDTOException exception) {
        return handleCustomException(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MinIntervalNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMinIntervalNotFoundException(MinIntervalNotFoundException exception) {
        return handleCustomException(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WrongKindNameException.class)
    public ResponseEntity<ErrorResponse> handleWrongKindNameException(WrongKindNameException exception) {
        return handleCustomException(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        log.error(exception.getMessage());
        return handleCodeAbleException(HttpStatus.INTERNAL_SERVER_ERROR, new CodeAbleException(1, message("api.server.error")));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return handleBindValidationException(exception);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException exception) {
        return handleCustomException(exception, HttpStatus.BAD_REQUEST);
    }

    public record ErrorResponse(LocalDateTime timestamp, String message, int code) {
    }

    protected ResponseEntity<ErrorResponse> handleCustomException(Exception exception, HttpStatus status) {
        return ResponseEntity.status(status).body(body(exception.getMessage(), status.value()));
    }
    protected ResponseEntity<ErrorResponse> handleCodeAbleException(HttpStatus status, CodeAbleException exception) {
        return ResponseEntity.status(status).body(body(exception));
    }
    protected ErrorResponse body(CodeAbleException exception) {
        return body(message(exception.getMessage()), exception.getCode());
    }
    protected ResponseEntity<ErrorResponse> handleBindValidationException(MethodArgumentNotValidException exception) {
        String message = IntStream.range(0, exception.getErrorCount()).mapToObj(i -> i + 1 + "." + exception.getAllErrors()
                .get(i).getDefaultMessage()).collect(Collectors.joining("; "));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body(message, 400));
    }

    protected ErrorResponse body(String message, Integer code) {
        return new ErrorResponse(LocalDateTime.now(), message(message), code);
    }

    private String message(String property) {
        return this.propertyResolverUtils.resolve(property, Locale.getDefault());
    }
}
