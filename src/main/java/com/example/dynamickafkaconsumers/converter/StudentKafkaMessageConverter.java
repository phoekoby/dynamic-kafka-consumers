package com.example.dynamickafkaconsumers.converter;

import com.example.dynamickafkaconsumers.annotations.KafkaConverter;
import com.example.dynamickafkaconsumers.entity.BaseEntity;
import com.example.dynamickafkaconsumers.processor.BaseEntityDefaultKafkaMessageProcessor;
import com.example.dynamickafkaconsumers.service.NegativesService;
import com.example.dynamickafkaconsumers.test.Human;
import com.example.dynamickafkaconsumers.test.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@KafkaConverter(
        id = "DefaultKafkaMessageConverter",
        topics = "student",
        groupId = "kafka-converter",
        keyClass = String.class,
        valueClass = Student.class
)
public class StudentKafkaMessageConverter extends BaseEntityDefaultKafkaMessageProcessor<String, Student> {
    private final NegativesService negativesService;

    @Override
    public Boolean check(String key, Student value) {
        System.out.println("CHECK: " + key + " " + value);
        return true;
    }

    @Override
    public BaseEntity convertInbound(String key, Student value) {
        System.out.println("CONVERT: " + key + " " + value);
        return new BaseEntity();
    }

    @Override
    protected NegativesService getService() {
        return negativesService;
    }
}
