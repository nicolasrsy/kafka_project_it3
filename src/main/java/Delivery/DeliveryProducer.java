package Delivery;


import com.producer.Car;
import io.smallrye.reactive.messaging.kafka.Record;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import java.util.List;

import static java.lang.Thread.sleep;

@ApplicationScoped
public class DeliveryProducer {

    @Inject @Channel("delivery-out")
    Emitter<List<Car>> emitter;

    public void sendDeliveryCars(List<Car> cars) throws InterruptedException {
        sleep(2000);
        emitter.send(cars);
    }
}
