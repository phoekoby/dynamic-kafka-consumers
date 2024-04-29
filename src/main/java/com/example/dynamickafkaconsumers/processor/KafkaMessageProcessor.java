package com.example.dynamickafkaconsumers.processor;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface KafkaMessageProcessor<K,V> {

    void process(ConsumerRecord<K, V> record);
}
