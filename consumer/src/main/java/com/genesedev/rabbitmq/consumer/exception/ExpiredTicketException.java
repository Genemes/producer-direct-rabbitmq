package com.genesedev.rabbitmq.consumer.exception;

public class ExpiredTicketException extends  Exception{

    public ExpiredTicketException(){
        super();
    }

    public ExpiredTicketException(String message){
        super(message);
    }
}
