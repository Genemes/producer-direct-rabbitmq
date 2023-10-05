package com.genesedev.rabbitmq.consumer.domain;

import java.time.LocalDate;

public class Boleto {

    private String description;
    private float payValue;
    private LocalDate dueDate;

    public Boleto() {}

    public Boleto(String description, float payValue, LocalDate dueDate){
        this.description = description;
        this.payValue = payValue;
        this.dueDate = dueDate;
    }

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
        return "Description: " + description + " | " +
                " payValue=" + payValue + " | " +
                " dueDate=" + dueDate;
    }
}
