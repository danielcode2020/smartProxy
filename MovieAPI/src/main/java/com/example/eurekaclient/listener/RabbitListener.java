package com.example.eurekaclient.listener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.stereotype.Component;

@Component
@EnableRabbit
public class RabbitListener {
    private final Logger log = LoggerFactory.getLogger(RabbitListener.class);


    @org.springframework.amqp.rabbit.annotation.RabbitListener(queues = "${moviesapi.receiving-queue}")
    public void receiveMessage(Object object) {
        log.info("Recive  : {}", object.toString());
    }
}
