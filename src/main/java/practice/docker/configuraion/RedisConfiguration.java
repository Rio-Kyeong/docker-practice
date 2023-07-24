package practice.docker.configuraion;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import practice.docker.configuraion.properties.RedisConnectionProperties;

/**
 * Spring Boot 2.0 이하인 경우 Config 설정이 필요하다.
 */
@RequiredArgsConstructor
@Configuration
public class RedisConfiguration {

    private final RedisConnectionProperties redisConnectionProperties;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(redisConnectionProperties.getHost(), redisConnectionProperties.getPort());
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setDefaultSerializer(new StringRedisSerializer()); // 시리얼라이저 설정

        return redisTemplate;
    }
}
