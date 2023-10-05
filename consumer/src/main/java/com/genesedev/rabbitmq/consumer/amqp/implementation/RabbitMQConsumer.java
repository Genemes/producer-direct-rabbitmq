package com.genesedev.rabbitmq.consumer.amqp.implementation;

import com.genesedev.rabbitmq.consumer.amqp.AmqpConsumer;
import com.genesedev.rabbitmq.consumer.domain.Boleto;
import com.genesedev.rabbitmq.consumer.service.ConsumerService;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer implements AmqpConsumer<Boleto> {

    private final ConsumerService consumerService;

    public RabbitMQConsumer(ConsumerService consumer){
        this.consumerService = consumer;
    }

    @Override
    @RabbitListener(queues = "${spring.rabbitmq.request.routing-key.producer}")
    public void consumer(Boleto message) {
        try {
            consumerService.action(message);
        } catch (Exception ex) {
            throw new AmqpRejectAndDontRequeueException(ex);
        }
    }
}
