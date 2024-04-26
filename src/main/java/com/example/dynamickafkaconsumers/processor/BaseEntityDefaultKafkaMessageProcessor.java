package com.example.dynamickafkaconsumers.processor;

import com.example.dynamickafkaconsumers.entity.BaseEntity;

public abstract class BaseEntityDefaultKafkaMessageProcessor<K, V> extends DefaultKafkaMessageProcessor<K, V, BaseEntity> {
}
