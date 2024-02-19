package practice.docker.core.presentation;

import static practice.docker.core.util.HttpServletUtil.getUrlAndQueryString;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import lombok.Builder;
import lombok.Getter;
import practice.docker.core.util.TimeUtil;

@Getter
public class ErrorResponse {

    @Schema(example = "500")
    private int statusCode;

    @Schema(example = "2023-09-30T00:03:24Z")
    private String timeStamp;

    @Schema(example = "/{urlPath}")
    private String path;

    @Schema(example = "false", defaultValue = "ok")
    private boolean ok;

    @Schema
    private Error error;

    @Getter
    @Builder
    public static class Error {

        @Schema(example = "존재하지 않는 값입니다.")
        private String message;

        @Schema(type = "array", example = "[\"org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java)\"]")
        private String[] stack;
    }

    @Builder(builderClassName = "Init", builderMethodName = "init")
    public ErrorResponse(int statusCode, Exception exception) {
        this.timeStamp = TimeUtil.toString(ZonedDateTime.now(ZoneOffset.UTC));
        this.path = getUrlAndQueryString();
        this.statusCode = statusCode;
        this.ok = false;
        this.error = Error.builder()
            .message(exception.getMessage())
            .stack(convertStackTraceToStringArray(exception, 10))
            .build();
    }

    private String[] convertStackTraceToStringArray(Throwable throwable, int depth) {
        StackTraceElement[] stackTrace = throwable.getStackTrace();
        int length = Math.min(stackTrace.length, depth);

        String[] result = new String[length];
        for (int i = 0; i < length; i++) {
            result[i] = formatStackTraceElement(stackTrace[i]);
        }

        return result;
    }

    private String formatStackTraceElement(StackTraceElement stackTraceElement) {
        String declaringClass = stackTraceElement.getClassName();
        String methodName = stackTraceElement.getMethodName();
        String fileName = stackTraceElement.getFileName();

        return declaringClass + "." + methodName + "(" + fileName + ")";
    }
}
