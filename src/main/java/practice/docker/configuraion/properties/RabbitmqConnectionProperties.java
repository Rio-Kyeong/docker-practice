package practice.docker.configuraion.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("spring.rabbitmq")
@Getter
@Setter
public class RabbitmqConnectionProperties {

    private String host;

    private int port;

    private String userName;

    private String password;
}
