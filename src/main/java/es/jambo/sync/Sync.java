package es.jambo.sync;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

abstract class Sync<K> {

    private final Map<K, Counter> references = new HashMap<>();
    private final Lock lock = new ReentrantLock();

    protected final Counter getReference(K key) {
        lock.lock();
        try {
            final var hit = references.computeIfAbsent(key, p -> new Counter());
            hit.increment();
            return hit;
        } finally {
            lock.unlock();
        }
    }

    protected final void removeReference(K key) {
        lock.lock();
        try {
            final var hit = references.get(key);
            if (hit.decrementAndCheckMin()) {
                references.remove(key);
            }
        } finally {
            lock.unlock();
        }

    }
}