package es.jambo.sync;

import java.util.function.Consumer;

public class ConsumerSync<K> extends Sync<K> {
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
