package com.genesedev.rabbitmq.producer.service.implementation;

import com.genesedev.rabbitmq.producer.amqp.AmqpProducer;
import com.genesedev.rabbitmq.producer.domain.BoletoQueue;
import com.genesedev.rabbitmq.producer.service.AmqpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQServiceImpl implements AmqpService {

    @Autowired
    private AmqpProducer<BoletoQueue> amqp;

    @Override
    public void sendToConsumer(BoletoQueue message) {
        amqp.producer(message);
    }
}
