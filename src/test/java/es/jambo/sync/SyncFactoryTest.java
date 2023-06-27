package es.jambo.sync;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class SyncFactoryTest {

    @Test
    void createConsumer() {
        final var consumer = SyncFactory.createConsumer();
        Assertions.assertThat(consumer).isNotNull().isInstanceOf(ConsumerSyncImpl.class);
    }

    @Test
    void createFunction() {
        final var function = SyncFactory.createFunction();
        Assertions.assertThat(function).isNotNull().isInstanceOf(FunctionSync.class);
    }
}