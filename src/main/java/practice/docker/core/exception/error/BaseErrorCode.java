package practice.docker.core.exception.error;

import org.springframework.http.HttpStatus;

public interface BaseErrorCode<T extends RuntimeException> {

    String name();

    String getMessage();

    HttpStatus getHttpStatus();

    T toException();
}
