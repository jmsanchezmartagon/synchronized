package es.jambo.sync;

public final class SyncFactory {

    public <K> ConsumerSyncImpl<K> createConsumer() {
        return new ConsumerSyncImpl<>();
    }

    public <K> FunctionSyncImpl<K> createFunction() {
        return new FunctionSyncImpl<>();
    }
}
