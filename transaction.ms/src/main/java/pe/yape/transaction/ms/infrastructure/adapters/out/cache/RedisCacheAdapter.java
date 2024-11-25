package pe.yape.transaction.ms.infrastructure.adapters.out.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import pe.yape.transaction.ms.domain.ports.out.CacheRepository;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RedisCacheAdapter<T> implements CacheRepository<T> {

    private final RedisTemplate<String,T> redisTemplate;

    @Override
    public void cache(UUID key, T transaction) {
        redisTemplate.opsForValue().set(key.toString(), transaction);
    }


    @Override
    public T getCached(UUID key) {
        return redisTemplate.opsForValue().get(key.toString());
    }
}
