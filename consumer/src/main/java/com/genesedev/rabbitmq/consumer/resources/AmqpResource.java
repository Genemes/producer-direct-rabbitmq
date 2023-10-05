package com.genesedev.rabbitmq.consumer.resources;

import com.genesedev.rabbitmq.consumer.service.RePublishService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AmqpResource {

    private final RePublishService service;

    public AmqpResource(RePublishService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/repost")
    public void sendToQueue() {
        service.repost();
    }

}
