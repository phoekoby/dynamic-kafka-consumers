package com.example.dynamickafkaconsumers.pojo;

import com.example.dynamickafkaconsumers.processor.KafkaMessageProcessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;


public record KafkaConsumerPojo<K, V>(
        KafkaConsumerConfig<K, V> config,
        KafkaMessageProcessor<K, V> processor
) {

    @KafkaListener(
            id = "#{__listener.config.id}",
            topics = "#{__listener.config.topics}",
            containerFactory = "#{__listener.config.containerFactory}",
            groupId = "#{__listener.config.groupId}",
            concurrency = "#{__listener.config.concurrency}",
            topicPattern = "#{__listener.config.topicPattern}",
            containerGroup = "#{__listener.config.containerGroup}",
            errorHandler = "#{__listener.config.errorHandler}",
            clientIdPrefix = "#{__listener.config.clientIdPrefix}",
            autoStartup = "#{__listener.config.autoStartup}",
            properties = "#{__listener.config.properties}",
            contentTypeConverter = "#{__listener.config.contentTypeConverter}",
            batch = "#{__listener.config.batch}",
            filter = "#{__listener.config.filter}",
            info = "#{__listener.config.info}"
    )
    public void listen(ConsumerRecord<K, V> record) {
        processor.process(record.key(), record.value());
    }
}
