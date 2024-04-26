package com.example.dynamickafkaconsumers.pojo;

import java.util.function.BiConsumer;

public record KafkaConsumerConfig<K, V>(
        String id,
        String containerFactory,
        String[] topics,
        String groupId,
        Integer concurrency,

        String topicPattern,
        String containerGroup,
        String errorHandler,
        String clientIdPrefix,
        String autoStartup,
        String[] properties,
        String contentTypeConverter,
        String batch,
        String filter,
        String info,
        String containerPostProcessor
) {

}
