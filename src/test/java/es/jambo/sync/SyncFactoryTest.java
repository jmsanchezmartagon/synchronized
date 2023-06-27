package es.jambo.sync;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class SyncFactoryTest {

    @Test
    void createConsumer() {
        final var consumer = SyncFactory.createConsumer();
        Assertions.assertThat(consumer).isNotNull().isInstanceOf(ConsumerSyncImpl.class);
        final var data = UUID.randomUUID().toString();
        consumer.accept("key", data, (value) -> {
            Assertions.assertThat(value).isEqualTo(data);
        });
    }

    @Test
    void createFunction() {
        final var function = SyncFactory.createFunction();
        Assertions.assertThat(function).isNotNull().isInstanceOf(FunctionSync.class);
        int count = 0;
        final var value = function.apply("key", count, (data) -> {
            return data + 1;
        });
        Assertions.assertThat(value).isEqualTo(1);
    }
}