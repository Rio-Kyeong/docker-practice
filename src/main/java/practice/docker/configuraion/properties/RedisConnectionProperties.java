package practice.docker.configuraion.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("spring.redis")
@Getter
@Setter
public class RedisConnectionProperties {

    private String host;

    private int port;
}
