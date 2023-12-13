package com.producer;

import io.smallrye.reactive.messaging.kafka.Record;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;


@ApplicationScoped
public class CarConsumer {

    @Inject
    CarProducer producer;
    private final Logger logger = Logger.getLogger(CarConsumer.class);

    @Incoming("selling-in")
    public void receive(Record<String, Integer> record) throws InterruptedException {
        logger.infof("Model %s  x %d sold", record.key(), record.value());
        producer.sendModel(new Car(record.key()));
    }


}
