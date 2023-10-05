package com.genesedev.rabbitmq.producer.service.implementation;

import com.genesedev.rabbitmq.producer.amqp.AmqpProducer;
import com.genesedev.rabbitmq.producer.domain.Boleto;
import com.genesedev.rabbitmq.producer.service.AmqpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQServiceImpl implements AmqpService {

    private final AmqpProducer<Boleto> amqp;

    public RabbitMQServiceImpl(AmqpProducer<Boleto> amqp) {
        this.amqp = amqp;
    }

    @Override
    public void sendToConsumer(Boleto message) {
        amqp.producer(message);
    }
}
