package es.jambo.sync;

import java.util.function.Function;

public final class FunctionSync<D> extends Sync<D> {

    public <T, R> R apply(final D data, final T t, final Function<T, R> f) {
        synchronized (getReference(data)) {
            try {
                return f.apply(t);
            } finally {
                removeReference(data);
            }
        }
    }
}