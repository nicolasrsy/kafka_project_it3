package com.example;

import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.reactive.messaging.*;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

import java.util.stream.Stream;

import static java.lang.Thread.sleep;

@ApplicationScoped
public class MyReactiveMessagingApplication {

    private static int carCount = 0;

    @Inject
    @Channel("Selling")
    Emitter<String> sellingEmitter;

    @Inject
    @Channel("Producer")
    Emitter<String> producerEmitter;


    /**
     * Sends message to the "words-out" channel, can be used from a JAX-RS resource or any bean of your application.
     * Messages are sent to the broker.
     **/
    void onStart(@Observes StartupEvent ev) {
        Stream.of("M", "I", "A", "G", "E").forEach(string -> sellingEmitter.send(string));
    }

    /**
     * Consume the message from the "words-in" channel, uppercase it and send it to the uppercase channel.
     * Messages come from the broker.
     **/
    @Incoming("Producer")
    @Outgoing("Delivery")
    public Message<String> produceCar(Message<String> model) throws InterruptedException {
        // Simulate production time based on model
        if (model.equals("M")) {
            sleep(10000);
        } else if (model.equals("I")) {
            sleep(2000);
        } else if (model.equals("A")) {
            sleep(3000);
        } else if (model.equals("G")) {
            sleep(4000);
        } else if (model.equals("E")) {
            sleep(7000);
        }

        System.out.println("Produced car: " + model);
        // Logic to count produced cars and trigger delivery
        // Production logic as above
        carCount++;
        if (carCount >= 10) {
            producerEmitter.send("Delivery ready");
            carCount = 0;
        }
        return null;
    }

    /**
     * Consume the uppercase channel (in-memory) and print the messages.
     **/
    @Incoming("Delivery")

    public void sink(String word) {
        System.out.println(">> " + word);
    }
}
