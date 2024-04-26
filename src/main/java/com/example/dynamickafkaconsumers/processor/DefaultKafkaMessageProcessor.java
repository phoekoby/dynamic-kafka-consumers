package com.example.dynamickafkaconsumers.processor;

public abstract class DefaultKafkaMessageProcessor<K, V, E> implements KafkaMessageProcessor<K, V> {
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

    protected abstract void doBusinessLogic(E e);
}
