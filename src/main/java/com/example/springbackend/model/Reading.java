package com.example.springbackend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
public class Reading {

    @Id
    @Column(nullable = false)
    private LocalDateTime timestamp = LocalDateTime.now();

    @Column(nullable = false)
    private String temperature;

    public Reading() {}


    public Reading(LocalDateTime timestamp, String temperature) {
        this.timestamp = timestamp;
        this.temperature = temperature;
    }

    //toegevoegd voor testen
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Reading(String temperature) {
        this.temperature = temperature;
        this.timestamp = LocalDateTime.now();
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}