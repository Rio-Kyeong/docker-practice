package practice.docker.configuraion.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("sample-rabbitmq")
@Getter
@Setter
public class RabbitmqProperties {

    private String queue;

    private String exchange;

    private String routingKey;
}
