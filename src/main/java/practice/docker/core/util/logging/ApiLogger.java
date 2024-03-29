package practice.docker.core.util.logging;

import static practice.docker.core.util.HttpServletUtil.getHttpMethod;
import static practice.docker.core.util.HttpServletUtil.getUrlAndQueryString;
import static practice.docker.core.util.HttpServletUtil.requestToHeaderMap;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import practice.docker.core.util.logging.document.RequestDocument;
import practice.docker.core.util.logging.document.RequestDocument.RequestDocumentBuilder;
import practice.docker.core.util.logging.document.ResponseDocument;

@Component
@RequiredArgsConstructor
public class ApiLogger {

    private final MongoTemplate mongoTemplate;

    public void beforeLog(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();

        RequestDocumentBuilder requestLogBuilder = RequestDocument.builder()
            .url(getUrlAndQueryString())
            .method(getHttpMethod())
            .header(requestToHeaderMap());

        for (Object arg : args) {
            if (arg != null && ClassUtils.getUserClass(arg).getSimpleName().contains("Request")) {
                requestLogBuilder.body(arg);
            }
        }

        mongoTemplate.insert(requestLogBuilder.build());
    }

    public void afterLog(ResponseEntity response) {
        ResponseDocument successResponseLog = ResponseDocument.builder()
            .statusCode(response.getStatusCodeValue())
            .body(response.getBody())
            .header(response.getHeaders().toSingleValueMap())
            .isError(false)
            .build();

        mongoTemplate.insert(successResponseLog);
    }

    public void failLog(ResponseEntity response) {
        ResponseDocument errorResponseLog = ResponseDocument.builder()
            .statusCode(response.getStatusCodeValue())
            .body(response.getBody())
            .header(response.getHeaders().toSingleValueMap())
            .isError(true)
            .build();

        mongoTemplate.insert(errorResponseLog);
    }
}
