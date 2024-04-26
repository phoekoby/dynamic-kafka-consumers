package com.example.dynamickafkaconsumers.function;

import com.example.dynamickafkaconsumers.entity.BaseEntity;
import com.example.dynamickafkaconsumers.service.NegativesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class DefaultKafkaConverterProcessor<K, V> implements KafkaConverterProcessor<K, V> {
    private final NegativesService negativesService;
    private final KafkaMessagesConverter<K, V> converter;

    @Override
    public void accept(K k, V v) {
        log.info("Обрабатываю входящее сообщение с ключом: {} ({}) и значением: {} ({})", k, k.getClass(), v, v.getClass());
        Boolean checkRes = converter.check(k, v);
        if (checkRes) {
            BaseEntity entity = converter.convert(k, v);
            negativesService.process(entity);
        }
    }
}
