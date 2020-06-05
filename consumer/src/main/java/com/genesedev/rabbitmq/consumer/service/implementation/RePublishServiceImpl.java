package com.genesedev.rabbitmq.consumer.service.implementation;

import com.genesedev.rabbitmq.consumer.amqp.AmqpRePublish;
import com.genesedev.rabbitmq.consumer.service.RePublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RePublishServiceImpl implements RePublishService {

    @Autowired
    private AmqpRePublish rePublish;

    @Override
    public void repost() {
        rePublish.rePublish();
    }
}
