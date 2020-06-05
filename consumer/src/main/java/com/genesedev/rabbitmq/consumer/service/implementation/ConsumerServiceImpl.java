package com.genesedev.rabbitmq.consumer.service.implementation;
import com.genesedev.rabbitmq.consumer.domain.BoletoQueue;
import com.genesedev.rabbitmq.consumer.service.ConsumerService;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    LocalDate hoje = LocalDate.now();

    @Override
    public void action(BoletoQueue message) {
        if("erro".equalsIgnoreCase(message.getDescription())) {
            throw new AmqpRejectAndDontRequeueException("erro");
            // parking-lot
        }else if(message.getDueDate().isBefore(hoje)) {
            throw new AmqpRejectAndDontRequeueException("boleto vencido");
            //data-exception
        }

        System.out.println(message.toString());
    }
}
