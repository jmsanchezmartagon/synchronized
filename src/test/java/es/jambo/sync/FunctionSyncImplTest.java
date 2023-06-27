package es.jambo.sync;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;

class FunctionSyncImplTest {


    private static class MutableCounter {
        private int value = 0;
    }

    @Test
    void should_printInOrder() {
        final var NUM_INC = 400;
        final var NUM_TASK = 2000;
        final var sync = new FunctionSyncImpl<>();
        final var counter = new MutableCounter();

        var service = Executors.newFixedThreadPool(200);
        for (int i = 0; i < NUM_TASK; i++) {
            service.execute(() -> {
                        sync.apply("key", counter, (data) -> {
                            for (int c = 0; c < NUM_INC; c++)
                                data.value++;
                            return data.value;
                        });
                    }
            );
        }

        service.shutdown();
        while (!service.isTerminated()) ;

        Assertions.assertThat(counter.value).isEqualTo(NUM_TASK * NUM_INC);
    }


    @Test
    void should_giveArgumentData_when_applyFunction() {
        final var function = new FunctionSyncImpl<>();
        int count = 0;
        final var value = function.apply("key", count, (data) -> {
            return data + 1;
        });
        Assertions.assertThat(value).isEqualTo(1);
    }
}