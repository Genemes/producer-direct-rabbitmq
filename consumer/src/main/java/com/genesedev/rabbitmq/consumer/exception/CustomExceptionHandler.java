package com.genesedev.rabbitmq.consumer.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ExpiredTicketException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<String> handleExpiredTicketException(ExpiredTicketException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Boleto Vencido.");
    }

    @ExceptionHandler(ErrorTicketException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<String> handleErrorTicketException(ErrorTicketException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Boleto com Erro.");
    }

    @ExceptionHandler(AmqpRejectAndDontRequeueException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<String> handleAmqpRejectAndDontRequeueException(AmqpRejectAndDontRequeueException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro interno no protocolo AMQP.");
    }

    @ExceptionHandler(JsonProcessingException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<String> handleJsonProcessingException(JsonProcessingException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro ao ´processar Json.");
    }

}
