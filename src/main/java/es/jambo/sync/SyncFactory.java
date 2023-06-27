package es.jambo.sync;

public final class SyncFactory {

    public static <K> ConsumerSyncImpl<K> createConsumer() {
        return new ConsumerSyncImpl<>();
    }

    public static <K> FunctionSyncImpl<K> createFunction() {
        return new FunctionSyncImpl<>();
    }
}
