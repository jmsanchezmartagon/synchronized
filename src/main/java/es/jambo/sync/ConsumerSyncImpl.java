package es.jambo.sync;

import java.util.function.Consumer;

final class ConsumerSyncImpl<K> extends Sync<K> implements ConsumerSync<K> {
    @Override
    public <D> void accept(final K key, final D data, final Consumer<D> consumer) {
        synchronized (getReference(key)) {
            try {
                consumer.accept(data);
            } finally {
                removeReference(key);
            }
        }
    }
}
