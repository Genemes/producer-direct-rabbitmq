package com.genesedev.rabbitmq.producer.service;

import com.genesedev.rabbitmq.producer.domain.Boleto;

public interface AmqpService {
    void sendToConsumer(Boleto message);
}
