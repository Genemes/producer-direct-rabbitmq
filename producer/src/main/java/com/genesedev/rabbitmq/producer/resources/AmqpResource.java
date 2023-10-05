package com.genesedev.rabbitmq.producer.resources;

import com.genesedev.rabbitmq.producer.domain.Boleto;
import com.genesedev.rabbitmq.producer.service.AmqpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AmqpResource {

    private final AmqpService service;

    public AmqpResource(AmqpService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/direct")
    public void sendToConsumer(@RequestBody Boleto message) {
        service.sendToConsumer(message);
    }

}
