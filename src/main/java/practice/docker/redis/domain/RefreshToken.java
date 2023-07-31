package practice.docker.redis.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@Builder
@ToString
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "refresh_token")
public class RefreshToken {

    @Id
    private String authId;

    @Indexed
    private String token;

    private String role;

    @TimeToLive // 만료 시간, @RedisHash 속성으로도 설정 가능
    private Long timeToLive;

    public RefreshToken update(String token, Long timeToLive) {
        this.token = token;
        this.timeToLive = timeToLive;
        return this;
    }
}
