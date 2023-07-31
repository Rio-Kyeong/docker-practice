package practice.docker.message.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import practice.docker.configuraion.properties.RabbitmqProperties;
import practice.docker.message.presentation.MessageController.MessageDto;

@Slf4j
@RequiredArgsConstructor
@Service
public class MessageService {

    private final RabbitmqProperties rabbitmqProperties;

    private final RabbitTemplate rabbitTemplate;

    /**
     * Queue로 메시지를 발행할 때는 RabbitTemplate 클래스에 convertAndSend 메서드를 사용
     *
     * @param messageDto 발행할 메시지의 DTO 객체
     */
    public void sendMessage(MessageDto messageDto) {
        log.info("message sent: {}", messageDto.toString());
        rabbitTemplate.convertAndSend(rabbitmqProperties.getExchange(), rabbitmqProperties.getRoutingKey(), messageDto);
    }

    /**
     * Queue에서 메시지를 구독할 때는 하는 @RabbitListener 어노테이션을 사용
     *
     * @param messageDto 구독한 메시지를 담고 있는 MessageDto 객체
     */
    @RabbitListener(queues = "${sample-rabbitmq.queue}")
    public void receiveMessage(MessageDto messageDto) {
        log.info("Received message: {}", messageDto.toString());
    }
}
