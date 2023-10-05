package com.genesedev.rabbitmq.producer.amqp.implementation;

import com.genesedev.rabbitmq.producer.amqp.AmqpProducer;
import com.genesedev.rabbitmq.producer.domain.Boleto;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProducerRabbitMQ implements AmqpProducer<Boleto> {

    private final RabbitTemplate rabbitTemplate;

    public ProducerRabbitMQ(RabbitTemplate template) {
        this.rabbitTemplate = template;
    }

    @Value("${spring.rabbitmq.request.routing-key.producer}")
    private String queue;

    @Value("${spring.rabbitmq.request.exchange.producer}")
    private String exchange;

    @Override
    public void producer(Boleto message) {
        try {
            rabbitTemplate.convertAndSend(exchange, queue, message);
        } catch (Exception ex) {
            throw new AmqpRejectAndDontRequeueException(ex);
        }
    }

}
