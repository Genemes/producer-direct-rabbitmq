package com.genesedev.rabbitmq.producer.amqp;

public interface AmqpProducer<T> {
    void producer(T t);
}
