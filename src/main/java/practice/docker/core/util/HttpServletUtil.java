package practice.docker.core.util;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpServletUtil {

    /**
     * 현재 HTTP 요청 객체를 가져오고, 요청이 존재하면 해당 요청의 HTTP 메서드를 반환
     */
    public static String getHttpMethod() {
        return Optional
            .ofNullable(getHttpServletRequest())
            .map(HttpServletRequest::getMethod)
            .orElse(null);
    }

    /**
     * 현재 HTTP 메서드가 POST인 경우 201을, GET인 경우 200을 반환
     */
    public static int getCustomHttpStatusCode() {
        HttpServletRequest request = getHttpServletRequest();
        if (request != null) {
            String httpMethod = getHttpMethod();
            if ("POST".equalsIgnoreCase(httpMethod)) {
                return HttpServletResponse.SC_CREATED; // HTTP 상태 코드 201 (Created) for POST method
            } else if ("GET".equalsIgnoreCase(httpMethod)) {
                return HttpServletResponse.SC_OK; // HTTP 상태 코드 200 (OK) for GET method
            }
        }
        return HttpServletResponse.SC_OK; // 기본적으로 OK 상태 코드를 반환합니다.
    }

    /**
     * 현재 HTTP 요청 객체를 가져오고, 요청이 존재하면 요청 URI와 쿼리 문자열을 합친 문자열을 반환
     */
    public static String getUrlAndQueryString() {
        return Optional
            .ofNullable(getHttpServletRequest())
            .map(request -> request.getRequestURI() + (request.getQueryString() != null ? "?" + request.getQueryString() : ""))
            .orElse(null);
    }


    /**
     * 현재 요청을 처리하는 스레드의 RequestContextHolder에서 ServletRequestAttributes를 가져와서 현재의 HttpServletRequest 객체를 반환
     */
    private static HttpServletRequest getHttpServletRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        } catch (IllegalStateException ignored) {
            return null;
        }
    }
}
