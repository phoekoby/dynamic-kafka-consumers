package com.example.dynamickafkaconsumers.annotations;


import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value= ElementType.TYPE)
@Retention(value= RetentionPolicy.RUNTIME)
public @interface KafkaConverter {
    String id();
    String[] topics();
    String groupId();
    int concurrency() default 1;
    String containerFactory() default "";
    String topicPattern() default "";
    String containerGroup() default "";
    String errorHandler() default "";
    String clientIdPrefix() default "";
    String autoStartup() default "true";
    String[] properties() default {};
    String contentTypeConverter() default "";
    String batch() default "";
    String filter() default "";
    String info() default "";
    String containerPostProcessor() default "";
    boolean isCustomJsonObject() default true;

    Class<? extends Deserializer> keyDeserializer() default StringDeserializer.class;
    Class<? extends Deserializer> valueDeserializer() default JsonDeserializer.class;


    Class keyClass();
    Class valueClass();

    String kafkaConverterProcessorBeanName() default "";

}
