package pe.yape.anti.fraud.ms.domain.ports.out;

public interface EventPublisher<T> {
    void publishEvent(T event);
}
