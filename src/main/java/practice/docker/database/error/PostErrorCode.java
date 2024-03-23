package practice.docker.database.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import practice.docker.core.exception.DomainException;
import practice.docker.core.exception.error.BaseErrorCode;

@Getter
@RequiredArgsConstructor
public enum PostErrorCode implements BaseErrorCode<DomainException> {

    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "게시글 정보를 찾을 수 없습니다."),
    NOT_MATCH_USER_POST(HttpStatus.BAD_REQUEST, "입력된 회원과 찜 목록의 회원이 일치하지 않습니다.");

    private final HttpStatus httpStatus;

    private final String message;

    @Override
    public DomainException toException() {
        return new DomainException(httpStatus, message);
    }
}
