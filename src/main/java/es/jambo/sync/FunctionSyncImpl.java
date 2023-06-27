package es.jambo.sync;

import java.util.function.Function;

final class FunctionSyncImpl<K> extends Sync<K> implements FunctionSync<K> {

    @Override
    public <T, R> R apply(final K key, final T t, final Function<T, R> f) {
        synchronized (getReference(key)) {
            try {
                return f.apply(t);
            } finally {
                removeReference(key);
            }
        }
    }
}