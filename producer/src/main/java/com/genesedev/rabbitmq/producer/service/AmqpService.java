package com.genesedev.rabbitmq.producer.service;

import com.genesedev.rabbitmq.producer.domain.BoletoQueue;

public interface AmqpService {
    void sendToConsumer(BoletoQueue message);
}
