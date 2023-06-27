package es.jambo.sync;

import java.util.function.Function;

public interface FunctionSync<K> {
    /**
     * @param key to sync, all threads of the same key will be blocked
     * @param t   t is the data to pass to the function
     * @param f   is the function to execute in the block sync
     * @param <T> Data type
     * @param <R> Return type
     * @return value that will be returned by the function
     */
    <T, R> R apply(final K key, final T t, final Function<T, R> f);
}
