package com.example.dynamickafkaconsumers.processor;

import com.example.dynamickafkaconsumers.entity.BaseEntity;
import com.example.dynamickafkaconsumers.service.NegativesService;

public abstract class BaseEntityDefaultKafkaMessageProcessor<K, V> extends DefaultKafkaMessageProcessor<K, V, BaseEntity> {
    protected abstract NegativesService getService();

    @Override
    protected void doBusinessLogic(BaseEntity entity) {
        getService().process(entity);
    }
}
