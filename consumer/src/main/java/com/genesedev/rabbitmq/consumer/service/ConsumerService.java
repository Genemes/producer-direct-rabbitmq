package com.genesedev.rabbitmq.consumer.service;

import com.genesedev.rabbitmq.consumer.domain.BoletoQueue;

public interface ConsumerService {

    void action(BoletoQueue message);

}
