package es.jambo.sync;

final class Counter {
    private int pointer = Integer.MIN_VALUE;

    public void increment() {
        if (pointer == Integer.MAX_VALUE)
            throw new CounterOverFlowException();
        pointer++;
    }

    public boolean decrementAndCheckMin() {
        if (pointer == Integer.MIN_VALUE)
            throw new CounterOverFlowException();
        pointer--;
        return pointer == Integer.MIN_VALUE;
    }
}
