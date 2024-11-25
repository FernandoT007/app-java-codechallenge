package pe.yape.transaction.ms.domain.ports.out;

public interface EventPublisher<T> {

    void publishEvent(T event);
}
