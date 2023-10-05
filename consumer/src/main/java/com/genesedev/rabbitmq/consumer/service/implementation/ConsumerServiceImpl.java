package com.genesedev.rabbitmq.consumer.service.implementation;
import com.genesedev.rabbitmq.consumer.domain.Boleto;
import com.genesedev.rabbitmq.consumer.exception.ErrorTicketException;
import com.genesedev.rabbitmq.consumer.exception.ExpiredTicketException;
import com.genesedev.rabbitmq.consumer.service.ConsumerService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    LocalDate hoje = LocalDate.now();

    @Override
    public void action(Boleto message) throws ExpiredTicketException, ErrorTicketException {
        if("erro".equalsIgnoreCase(message.getDescription())) {
            throw new ErrorTicketException("Erro");
            // parking-lot
        }else if(message.getDueDate().isBefore(hoje)) {
            throw new ExpiredTicketException("Boleto Vencido");
            //data-exception
        }

        System.out.println(message.toString());
    }
}
