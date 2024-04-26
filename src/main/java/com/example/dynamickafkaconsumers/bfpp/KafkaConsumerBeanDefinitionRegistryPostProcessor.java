package com.example.dynamickafkaconsumers.bfpp;

import com.example.dynamickafkaconsumers.annotations.KafkaConverter;
import com.example.dynamickafkaconsumers.pojo.KafkaConsumerConfig;
import com.example.dynamickafkaconsumers.pojo.KafkaConsumerPojo;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Component
public class KafkaConsumerBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @SneakyThrows
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        for (String beanDefinitionName : registry.getBeanDefinitionNames()) {
            BeanDefinition beanDefinition = registry.getBeanDefinition(beanDefinitionName);
            if (!Objects.isNull(beanDefinition.getBeanClassName())) {
                Class<?> beanClass = Class.forName(beanDefinition.getBeanClassName());

                if (beanClass.isAnnotationPresent(KafkaConverter.class)) {
                    KafkaConverter annotation = beanClass.getAnnotation(KafkaConverter.class);
                    registerConsumerBeanDefinition(registry, annotation, beanDefinitionName);
                }
            }

        }

    }

    private void registerConsumerBeanDefinition(
            BeanDefinitionRegistry registry,
            KafkaConverter annotation,
            String processorBeanName

    ) {
        log.info("==================== Регистрируем Consumer Bean Definition ====================");
        String consumerBeanName = annotation.id() + "KafkaConsumer";

        AbstractBeanDefinition consumer = BeanDefinitionBuilder
                .genericBeanDefinition(KafkaConsumerPojo.class)
                .addConstructorArgValue(createKafkaConsumerConverterConfigFromAnnotation(
                        annotation,
                        annotation.keyClass(),
                        annotation.valueClass()
                ))
                .addConstructorArgReference(processorBeanName)
                .getBeanDefinition();

        registry.registerBeanDefinition(consumerBeanName, consumer);
    }


    private <K, V> KafkaConsumerConfig<K, V> createKafkaConsumerConverterConfigFromAnnotation(
            KafkaConverter annotation,
            Class<K> kClass,
            Class<V> vClass
    ) {
        log.info("==================== Конфигурация Kafka Consumer ====================");
        List<String> consumerProperties = Arrays.stream(annotation.properties()).collect(Collectors.toList());
        consumerProperties.add(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG + "=" + annotation.keyDeserializer().getName());
        consumerProperties.add(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG + "=" + annotation.valueDeserializer().getName());

        if (annotation.isCustomJsonObject() && (annotation.valueDeserializer() == JsonDeserializer.class)) {
            consumerProperties.add(JsonDeserializer.USE_TYPE_INFO_HEADERS + "=" + false);
            consumerProperties.add(JsonDeserializer.VALUE_DEFAULT_TYPE + "=" + vClass.getName());
        }

        if (annotation.isCustomJsonObject() && (annotation.keyDeserializer() == JsonDeserializer.class)) {
            consumerProperties.add(JsonDeserializer.USE_TYPE_INFO_HEADERS + "=" + false);
            consumerProperties.add(JsonDeserializer.VALUE_DEFAULT_TYPE + "=" + vClass.getName());
        }

        String[] properties = consumerProperties.toArray(String[]::new);

        return new KafkaConsumerConfig<>(
                annotation.id(),
                annotation.containerFactory(),
                annotation.topics(),
                annotation.groupId(),
                annotation.concurrency(),
                annotation.topicPattern(),
                annotation.containerGroup(),
                annotation.errorHandler(),
                annotation.clientIdPrefix(),
                annotation.autoStartup(),
                properties,
                annotation.contentTypeConverter(),
                annotation.batch(),
                annotation.filter(),
                annotation.info(),
                annotation.containerPostProcessor()
        );
    }
}
