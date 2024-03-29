package es.jambo.sync;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;
import java.util.concurrent.Executors;

class ConsumerSyncImplTest {


    private static class MutableCounter {
        private int value = 0;
    }

    @Test
    void should_printInOrder() {
        final var NUM_INC = 400;
        final var NUM_TASK = 2000;
        final var sync = new ConsumerSyncImpl<>();
        final var counter = new MutableCounter();

        var service = Executors.newFixedThreadPool(200);
        for (int i = 0; i < NUM_TASK; i++) {
            service.execute(() -> {
                        sync.accept("key", counter, (data) -> {
                            for (int c = 0; c < NUM_INC; c++)
                                data.value++;
                        });
                    }
            );
        }

        service.shutdown();
        while (!service.isTerminated()) ;

        Assertions.assertThat(counter.value).isEqualTo(NUM_TASK * NUM_INC);
    }


    @Test
    void should_giveDataArgument_when_callConsumer() {
        final var consumer = new ConsumerSyncImpl<>();
        final var data = UUID.randomUUID().toString();
        consumer.accept("key", data, (value) -> {
            Assertions.assertThat(value).isEqualTo(data);
        });
    }


}