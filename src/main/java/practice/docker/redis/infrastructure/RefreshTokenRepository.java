package practice.docker.redis.infrastructure;

import org.springframework.data.repository.CrudRepository;
import practice.docker.redis.domain.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {

    Optional<RefreshToken> findByAuthId(String authId);
}
