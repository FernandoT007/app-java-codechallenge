package pe.yape.transaction.ms.domain.ports.out;

import java.util.UUID;

public interface CacheRepository<T> {

    void cache(UUID key, T transaction);
    T getCached(UUID key);
}
