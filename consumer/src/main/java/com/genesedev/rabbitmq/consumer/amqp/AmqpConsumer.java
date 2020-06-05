package com.genesedev.rabbitmq.consumer.amqp;

public interface AmqpConsumer<T> {
    void consumer(T t);
}
