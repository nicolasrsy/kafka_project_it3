package Delivery;

import com.producer.Car;
import io.smallrye.reactive.messaging.kafka.Record;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

import java.util.List;


@ApplicationScoped
public class DeliveryConsumer {

    static List<Car> deliveryCars;
    @Inject
    DeliveryProducer producer;
    private final Logger logger = Logger.getLogger(DeliveryConsumer.class);

    @Incoming("producer-in")
    public void receive(String model) throws InterruptedException {
        logger.infof("Model %s received - delivery ", model);
        deliveryCars.add(new Car(model));
        if (deliveryCars.size() == 10){
            producer.sendDeliveryCars(deliveryCars);
            deliveryCars.clear();
        }
    }


}
