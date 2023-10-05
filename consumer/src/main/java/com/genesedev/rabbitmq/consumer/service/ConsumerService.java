package com.genesedev.rabbitmq.consumer.service;

import com.genesedev.rabbitmq.consumer.domain.Boleto;
import com.genesedev.rabbitmq.consumer.exception.ErrorTicketException;
import com.genesedev.rabbitmq.consumer.exception.ExpiredTicketException;

public interface ConsumerService {

    void action(Boleto message) throws ExpiredTicketException, ErrorTicketException;

}
