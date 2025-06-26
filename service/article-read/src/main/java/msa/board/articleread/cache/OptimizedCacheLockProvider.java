package msa.board.articleread.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class OptimizedCacheLockProvider {

    private final StringRedisTemplate redisTemplate;

    private static final String KEY_PREFIX = "optimized-cache-lock::";
    private static final Duration LOCK_TTL = Duration.ofSeconds(3);

    public boolean lock(String key) {
        return redisTemplate.opsForValue().setIfAbsent(
                generateKey(key),
                "locked",
                LOCK_TTL
        );
    }

    public void unlock(String key) {
        redisTemplate.delete(generateKey(key));
    }

    private String generateKey(String key) {
        return KEY_PREFIX + key;
    }

}
