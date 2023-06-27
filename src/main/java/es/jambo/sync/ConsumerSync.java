package es.jambo.sync;

import java.util.function.Consumer;

public interface ConsumerSync<K> {

    /**
     * @param key      to sync, all threads of the same key will be blocked
     * @param data     data that will be consumed
     * @param consumer operation
     * @param <D>      Data Type of the operation consumer
     */
    <D> void accept(K key, D data, Consumer<D> consumer);
}
