package frds.mgnt.controller;

import static frds.mgnt.controller.ResponseUtil.failed;
import static java.util.stream.Collectors.joining;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import frds.mgnt.service.FriendRuntimeException;

@RestControllerAdvice
public class FriendControllerAdvice {

    private static final Logger log = LoggerFactory.getLogger(FriendControllerAdvice.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(FriendRuntimeException.class)
    public ResponseUtil handleFriendException(FriendRuntimeException ex) {
        log.error("Friend operation has failed.", ex);
        return failed(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseUtil handleValidationErrors(MethodArgumentNotValidException ex) {
        log.error("Validation failed!", ex);
        return failed(ex.getBindingResult().getAllErrors().stream().map(error -> {
            return ((error instanceof FieldError) ? ((FieldError) error).getField() : "") + " " + error.getDefaultMessage();
        }).collect(joining(". ")));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseUtil handleGeneralException(Exception ex) {
        log.error("System throws an exception.", ex);
        return failed(ex.getMessage());
    }
}
