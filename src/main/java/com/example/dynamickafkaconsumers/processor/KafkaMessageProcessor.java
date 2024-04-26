package com.example.dynamickafkaconsumers.processor;

public interface KafkaMessageProcessor<K,V> {

    void process(K key, V value);
}
