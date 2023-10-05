package com.genesedev.rabbitmq.consumer.service.implementation;

import com.genesedev.rabbitmq.consumer.amqp.AmqpRePublish;
import com.genesedev.rabbitmq.consumer.service.RePublishService;
import org.springframework.stereotype.Service;

@Service
public class RePublishServiceImpl implements RePublishService {

    private final AmqpRePublish rePublish;

    public RePublishServiceImpl(AmqpRePublish rePublish){
        this.rePublish = rePublish;
    }

    @Override
    public void repost() {
        rePublish.rePublish();
    }
}
