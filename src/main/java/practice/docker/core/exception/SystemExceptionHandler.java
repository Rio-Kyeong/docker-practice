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
        HttpStatus currentHttpStatus = e.getHttpStatus();
        ErrorResponse response = ErrorResponse.init()
            .statusCode(currentHttpStatus.value())
            .exception(e)
            .build();

        return ResponseEntity.status(currentHttpStatus)
            .body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentValidException(MethodArgumentNotValidException e) {
        HttpStatus currentHttpStatus = determineHttpStatus(e);
        ErrorResponse response = ErrorResponse.init()
            .statusCode(currentHttpStatus.value())
            .exception(e)
            .build();

        return ResponseEntity.status(currentHttpStatus)
            .body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        HttpStatus currentHttpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse response = ErrorResponse.init()
            .statusCode(currentHttpStatus.value())
            .exception(e)
            .build();

        return ResponseEntity.status(currentHttpStatus)
            .body(response);
    }

    private HttpStatus determineHttpStatus(MethodArgumentNotValidException ex) {
        ResponseStatus responseStatus = ex.getClass().getAnnotation(ResponseStatus.class);

        return responseStatus == null ? HttpStatus.BAD_REQUEST : responseStatus.value();
    }
}
