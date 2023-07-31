package practice.docker.redis.presentation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practice.docker.redis.domain.RefreshToken;
import practice.docker.redis.infrastructure.RefreshTokenRepository;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
@Slf4j
public class RefreshTokenController {

    private final RefreshTokenRepository refreshTokenRepository;

    @PostMapping
    public ResponseEntity<String> save(@RequestBody RefreshToken refreshToken) {
        RefreshToken saveRefreshToken = refreshTokenRepository.save(refreshToken);
        log.info("save to DB : {}", saveRefreshToken);

        return ResponseEntity.ok("save id: " + saveRefreshToken.getAuthId());
    }

    /**
     * ResponseEntity 반환 시 No Constructor 이슈가 있었음
     */
    @GetMapping("/{id}")
    @Cacheable(cacheNames = "refreshToken", key = "#id", sync = true, cacheManager = "redisCacheManager")
    public RefreshToken getToken(@PathVariable String id) {
        RefreshToken findRefreshToken = refreshTokenRepository.findByAuthId(id)
                .orElseThrow(() -> new IllegalArgumentException("데이터가 존재하지 않습니다."));
        log.info("RefreshToken fetching from DB : {}", findRefreshToken);

        return findRefreshToken;
    }
}
