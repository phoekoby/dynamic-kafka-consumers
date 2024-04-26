package com.example.dynamickafkaconsumers.function;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface KafkaConverterProcessor<K,V> extends BiConsumer<K, V> {
}
