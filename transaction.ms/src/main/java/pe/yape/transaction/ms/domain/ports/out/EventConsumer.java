package pe.yape.transaction.ms.domain.ports.out;

public interface EventConsumer<T> {

    void handleEvent(T event);

}
