package practice.docker.core.presentation;


import static practice.docker.core.util.HttpServletUtil.getCustomHttpStatusCode;
import static practice.docker.core.util.HttpServletUtil.getUrlAndQueryString;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import lombok.Builder;
import lombok.Getter;
import practice.docker.core.util.TimeUtil;

@Getter
public class SuccessResponse<T> {

    @Schema(example = "200")
    private int statusCode;

    @Schema(example = "2023-09-30T00:03:24Z")
    private String timeStamp;

    @Schema(example = "/{urlPath}")
    private String path;

    @Schema(example = "true", defaultValue = "ok")
    private boolean ok;

    @Schema
    private T result;

    @Builder
    public SuccessResponse(T result) {
        this.timeStamp = TimeUtil.toString(ZonedDateTime.now(ZoneOffset.UTC));
        this.path = getUrlAndQueryString();
        this.statusCode = getCustomHttpStatusCode();
        this.ok = true;
        this.result = result;
    }
}
