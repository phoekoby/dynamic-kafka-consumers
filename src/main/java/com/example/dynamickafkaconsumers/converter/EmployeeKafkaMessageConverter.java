package com.example.dynamickafkaconsumers.converter;

import com.example.dynamickafkaconsumers.annotations.KafkaConverter;
import com.example.dynamickafkaconsumers.entity.BaseEntity;
import com.example.dynamickafkaconsumers.processor.BaseEntityDefaultKafkaMessageProcessor;
import com.example.dynamickafkaconsumers.service.NegativesService;
import com.example.dynamickafkaconsumers.test.Employee;
import com.example.dynamickafkaconsumers.test.Human;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@KafkaConverter(
        id = "MyId",
        topics = "employee",
        groupId = "kafka",
        keyClass = String.class,
        valueClass = Human.class
)
@RequiredArgsConstructor
public class EmployeeKafkaMessageConverter extends BaseEntityDefaultKafkaMessageProcessor<String, Human> {
    private final NegativesService negativesService;

    @Override
    public Boolean check(String key, Human value) {
        System.out.println("EmployeeKafkaMessageConverter check: " + key + " " + value);
        return true;
    }

    @Override
    public BaseEntity convertInbound(String key, Human value) {
        System.out.println("EmployeeKafkaMessageConverter convert: " + key + " " + value);
        return new BaseEntity();
    }


    @Override
    protected NegativesService getService() {
        return negativesService;
    }
}
