package com.example.dynamickafkaconsumers.processor;

import com.example.dynamickafkaconsumers.service.BusinessLogicService;

public abstract class DefaultKafkaMessageProcessor<K, V, E> implements KafkaMessageProcessor<K, V> {
    protected abstract BusinessLogicService<E> getService();
    protected abstract Boolean check(K key, V value);

    protected abstract E convert(K key, V value);

    @Override
    public void process(K key, V value) {
        Boolean check = check(key, value);
        if(check){
            E convert = convert(key, value);
            doBusinessLogic(convert);
        }
    }

    protected void doBusinessLogic(E e){
        getService().doBusinessLogic(e);
    }
}
