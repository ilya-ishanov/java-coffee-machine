package kz.solvatech.testtask.handler;

import kz.solvatech.testtask.exception.WorkingHoursException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WorkingHoursException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException exception) {
        String message = exception.getMessage();
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
