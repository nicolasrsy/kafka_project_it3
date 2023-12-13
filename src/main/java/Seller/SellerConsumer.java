package Seller;

import com.producer.Car;
import io.smallrye.reactive.messaging.kafka.Record;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

import java.util.List;


@ApplicationScoped
public class SellerConsumer {

    static List<Car> localCars;
    @Inject
    SellerProducer producer;
    private final Logger logger = Logger.getLogger(SellerConsumer.class);

    @Incoming("delivery-in")
    public void receive(List<Car> cars){
        for (Car car:
             cars) {
            localCars.add(car);
        }
        logger.infof("Models received - seller");
    }

    public void removeCar(String model){
        Car car = new Car(model);
        if(localCars.contains(car)){
            localCars.remove(car);
        }
        producer.sendNotifSelling(car, 1);
    }


}
