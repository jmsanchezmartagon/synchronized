package org.xsync;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;

public class FSync<E> {

    private Lock lock = new ReentrantLock();
    private Map<E, AtomicInteger> reference = new HashMap<>();

    public <R> R run(Function<E, R> f, E data) {
        synchronized (this) {
            return f.apply(data);
        }
    }

    private AtomicInteger getReference(E data) {
        lock.lock();
        try {
            return reference.computeIfAbsent(data, (p) -> new AtomicInteger(1));
        } finally {
            lock.unlock();
        }
    }

    private void freeReference(E data) {
        lock.lock();
        try {
            AtomicInteger i = reference.get(data);
            if (i.decrementAndGet() <= 0) {
                reference.remove(data);
            }

        } finally {
            lock.unlock();
        }
    }
}
