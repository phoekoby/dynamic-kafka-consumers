package com.example.dynamickafkaconsumers.processor;

import com.example.dynamickafkaconsumers.service.BusinessLogicService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DefaultKafkaMessageProcessor<K, V, E, P> implements KafkaMessageProcessor<K, V> {
    private static final Logger log = LoggerFactory.getLogger(DefaultKafkaMessageProcessor.class);

    protected abstract BusinessLogicService<E, P> getService();
    protected abstract Boolean check(K key, V value);

    protected abstract E convertInbound(K key, V value);
    protected void postProcess(P p) {
        log.info("Skipping post process...");
    }

    @Override
    public void process(ConsumerRecord<K, V> record) {
        K key = record.key();
        V value = record.value();

        Boolean check = check(key, value);
        if(check){
            E convert = convertInbound(key, value);
            P result = doBusinessLogic(convert);
            postProcess(result);
        }
    }

    protected P doBusinessLogic(E e){
        return getService().doBusinessLogic(e);
    }
}
