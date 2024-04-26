package com.example.dynamickafkaconsumers.exception;

public class KafkaConverterException extends RuntimeException{
    public KafkaConverterException(String message) {
        super(message);
    }
}
