package practice.docker.core.presentation;


import static practice.docker.core.util.HttpServletUtil.getUrlAndQueryString;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import lombok.Builder;
import lombok.Getter;
import practice.docker.core.util.TimeUtil;

@Getter
public class SuccessResponse<T> {

    @Schema(example = "true", defaultValue = "ok")
    private boolean ok;

    @Schema(example = "/{urlPath}")
    private String path;

    @Schema(example = "2023-09-30T00:03:24Z")
    private String timeStamp;

    @Schema
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;

    @Builder
    public SuccessResponse(T result) {
        this.ok = true;
        this.path = getUrlAndQueryString();
        this.timeStamp = TimeUtil.toString(ZonedDateTime.now(ZoneOffset.UTC));
        this.result = result;
    }
}
