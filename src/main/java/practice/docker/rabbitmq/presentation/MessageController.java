package practice.docker.rabbitmq.presentation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import practice.docker.rabbitmq.application.MessageService;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MessageController {

    private final MessageService messageService;

    /**
     * Queue로 메시지를 발행
     *
     * @param messageDto 발행할 메시지의 DTO 객체
     * @return ResponseEntity 객체로 응답을 반환
     */
    @PostMapping("/send/message")
    public ResponseEntity<Object> sendMessage(@RequestBody MessageDto messageDto) {
        messageService.sendMessage(messageDto);
        return ResponseEntity.ok("Message sent to RabbitMQ!");
    }

    @ToString
    @Getter
    @Setter
    public static class MessageDto {

        private String title;

        private String content;
    }
}
