package com.genesedev.rabbitmq.consumer.amqp.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.genesedev.rabbitmq.consumer.amqp.AmqpRePublish;
import com.genesedev.rabbitmq.consumer.domain.Boleto;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;

@Component
public class RePublishRabbitMQ implements AmqpRePublish {

    private final RabbitTemplate rabbitTemplate;

    private final  ObjectMapper objectMapper;

    public RePublishRabbitMQ(RabbitTemplate template, ObjectMapper mapper){
        this.rabbitTemplate = template;
        this.objectMapper = mapper;
    }

    @Value("${spring.rabbitmq.request.exchange.producer}")
    private String exchange;

    @Value("${spring.rabbitmq.request.routing-key.producer}")
    private String queue;

    @Value("${spring.rabbitmq.request.dead-letter.producer}")
    private String deadLetter;

    @Value("${spring.rabbitmq.request.parking-lot.producer}")
    private String parkingLot;

    @Value("${spring.rabbitmq.request.data-exception.producer}")
    private String dataException;

    private static final String X_RETRIES_HEADER = "x-retries";
    LocalDate hoje = LocalDate.now();

    @Override
    @Scheduled(cron = "${spring.rabbitmq.listener.time-retry}")
    //@Scheduled(fixedDelay = 4000)
    public void rePublish() {
        List<Message> messages = getQueueMessages();

        messages.forEach(message -> {
            Map<String, Object> headers = message.getMessageProperties().getHeaders();
            Integer retriesHeader = (Integer) headers.get(X_RETRIES_HEADER);

            if(retriesHeader == null) {
                retriesHeader = 0;
            }

            if (retriesHeader < 3) {
                headers.put(X_RETRIES_HEADER, retriesHeader + 1);
                rabbitTemplate.send(exchange, queue, message);
            } else {
                //lógica para enviar para a fila correta
                String failedMessage = new String(message.getBody(), StandardCharsets.UTF_8);
                Boleto myMessage = null;
                try {
                    myMessage = convertBoletoQueue(failedMessage);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                String selectQueue = getQueue(myMessage);
                rabbitTemplate.send(selectQueue, message);
            }
        });
    }

    private List<Message> getQueueMessages() {
        List<Message> messages = new ArrayList<>();
        Boolean isNull;
        Message message;

        do {
            message = rabbitTemplate.receive(deadLetter);
            isNull = message != null;

            if(Boolean.TRUE.equals(isNull)) {
                messages.add(message);
            }
        } while (Boolean.TRUE.equals(isNull));

        return messages;
    }

    private Boleto convertBoletoQueue(String json) throws JsonProcessingException {
        return  objectMapper.readValue(json, Boleto.class);
    }

    private String getQueue(Boleto myMessage){
        if("erro".equalsIgnoreCase(myMessage.getDescription())) {
            return parkingLot;
        }else if(myMessage.getDueDate().isBefore(hoje)) {
            return dataException;
        } else {
            return queue;
        }
    }

}
