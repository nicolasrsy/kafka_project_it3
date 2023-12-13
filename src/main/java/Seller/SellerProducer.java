package Seller;


import io.smallrye.reactive.messaging.kafka.Record;
import com.producer.Car;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import static java.lang.Thread.sleep;

@ApplicationScoped
public class SellerProducer {

    @Inject @Channel("selling-out")
    Emitter<Record<String, Integer>> emitter;

    public void sendNotifSelling(Car car, Integer nb) {
        emitter.send(Record.of(car.getModel(), nb));
    }
}
