package es.jambo.sync;

public final class SyncFactory {

    public <K> ConsumerSync<K> createConsumer() {
        return new ConsumerSync<>();
    }

    public <K> FunctionSync<K> createFunction() {
        return new FunctionSync<>();
    }
}
