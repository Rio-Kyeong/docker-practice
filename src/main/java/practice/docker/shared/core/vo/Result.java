package practice.docker.shared.core.vo;

import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Result<T> {

    private T content;

    protected Result() {
        this.content = null;
    }

    public static <T> Result<T> of(T content) {
        return new Result<>(content);
    }
}
