package com.genesedev.rabbitmq.consumer.exception;

public class ErrorTicketException extends  Exception{

    public ErrorTicketException(){
        super();
    }

    public ErrorTicketException(String message){
        super(message);
    }
}
