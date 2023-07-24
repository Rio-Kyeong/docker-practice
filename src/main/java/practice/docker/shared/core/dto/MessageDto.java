package practice.docker.shared.core.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class MessageDto {

    private String title;

    private String content;
}
