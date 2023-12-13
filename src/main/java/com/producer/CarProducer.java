package com.producer;


import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import static java.lang.Thread.sleep;

@ApplicationScoped
public class CarProducer {

    @Inject @Channel("producer-out")
    Emitter<String> emitter;

    public void sendModel(Car car) throws InterruptedException {
        switch (car.getModel()){
            case "M":
                sleep(10000);
                break;
            case "I":
                sleep(2000);
                break;
            case "A":
                sleep(3000);
                break;
            case "G":
                sleep(4000);
                break;
            case "E":
                sleep(7000);
                break;
            default:
                break;
        }
        emitter.send(car.getModel());
    }
}
