package com.example.dynamickafkaconsumers.configuration;

import com.example.dynamickafkaconsumers.pojo.KafkaConsumerConfig;
import com.example.dynamickafkaconsumers.pojo.KafkaConsumerPojo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


@Configuration
@RequiredArgsConstructor
public class KafkaConverterConfiguration {

//    @Bean
//    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//    public <K, V> KafkaConsumerPojo<K, V> pojo(
//            KafkaConsumerConfig<K, V> kafkaConsumerConfig,
//            Class<K> kClass,
//            Class<V> vClass
//    ) {
//
//        return new KafkaConsumerPojo<>(
//                kafkaConsumerConfig,
//                kClass,
//                vClass
//        );
//    }

}
