package com.genesedev.rabbitmq.consumer.domain;

import java.time.LocalDate;

public class BoletoQueue {

    private String description;
    private float payValue;
    private LocalDate dueDate;

    public BoletoQueue() {}

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
