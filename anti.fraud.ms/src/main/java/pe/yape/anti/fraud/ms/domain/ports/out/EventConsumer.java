package pe.yape.anti.fraud.ms.domain.ports.out;

public interface EventConsumer<T> {
    void handleEvent(T event);
}
