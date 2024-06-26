package practice.docker.core.util;

import java.net.URI;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
     * 현재 HTTP 요청 객체를 가져오고, 요청이 존재하면 요청 URI와 쿼리 문자열을 합친 문자열을 반환
     */
    public static String getUrlAndQueryString() {
        return Optional
            .ofNullable(getHttpServletRequest())
            .map(request -> request.getRequestURI() + (request.getQueryString() != null ? "?" + request.getQueryString() : ""))
            .orElse(null);
    }

    /**
     * 현재 요청의 URI를 기반으로 postId를 받아와서 URI를 생성
     */
    public static URI createUriWithPostIdFromCurrentRequest(UUID newPostId) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{postId}")
            .buildAndExpand(newPostId.toString())
            .toUri();
    }

    /**
     * HTTP 요청의 헤더를 포함하는 Map<String, String>을 반환
     */
    public static Map<String, String> requestToHeaderMap() {
        HttpServletRequest request = getHttpServletRequest();
        if (request == null) {
            return new HashMap<>();
        }
        Map<String, String> headerMap = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames == null) {
            return new HashMap<>();
        }
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            headerMap.put(headerName, headerValue);
        }
        return headerMap;
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
