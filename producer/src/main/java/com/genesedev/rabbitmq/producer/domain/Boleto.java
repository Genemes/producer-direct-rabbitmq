package com.genesedev.rabbitmq.producer.domain;

import java.io.Serializable;
import java.time.LocalDate;

public class Boleto implements Serializable {

    private String description;
    private float payValue;
    private LocalDate dueDate;

    public Boleto() {}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPayValue() {
        return payValue;
    }

    public void setPayValue(float payValue) {
        this.payValue = payValue;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "{ description: "+ description + ", " +
                "payValue:" + payValue + ", " +
                "dueDate:" + dueDate +"}";
    }
}
