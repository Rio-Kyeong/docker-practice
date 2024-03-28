package practice.docker.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import practice.docker.core.presentation.ErrorResponse;

@RestControllerAdvice
public class SystemExceptionHandler {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorResponse> handleDomainException(DomainException e) {
        ErrorResponse response = ErrorResponse.init()
            .exception(e)
            .build();

        return ResponseEntity.status(e.getHttpStatus())
            .body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentValidException(MethodArgumentNotValidException e) {
        ErrorResponse response = ErrorResponse.init()
            .exception(e)
            .build();

        return ResponseEntity.status(determineHttpStatus(e))
            .body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        ErrorResponse response = ErrorResponse.init()
            .exception(e)
            .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(response);
    }

    private HttpStatus determineHttpStatus(MethodArgumentNotValidException ex) {
        ResponseStatus responseStatus = ex.getClass().getAnnotation(ResponseStatus.class);

        return responseStatus == null ? HttpStatus.BAD_REQUEST : responseStatus.value();
    }
}
