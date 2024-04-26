package com.example.dynamickafkaconsumers.configuration;

import com.example.dynamickafkaconsumers.converter.EmployeeKafkaMessageConverter;
import com.example.dynamickafkaconsumers.entity.BaseEntity;
import com.example.dynamickafkaconsumers.function.KafkaConverterProcessor;
import com.example.dynamickafkaconsumers.service.NegativesService;
import com.example.dynamickafkaconsumers.test.Employee;
import com.example.dynamickafkaconsumers.test.Human;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.function.BiConsumer;

@Configuration
@RequiredArgsConstructor
public class ExampleConfiguration {

    @Bean
    public KafkaConverterProcessor<String, Employee> toHumanBiConsumer(
            EmployeeKafkaMessageConverter employeeKafkaMessageConverter,
            NegativesService negativesService
    ){
        return (k, v) -> {
            Human human = new Human();
            human.setFirstName(v.getFirstName());
            human.setLastName(v.getLastName());

            Boolean check = employeeKafkaMessageConverter.check(k, human);
            if(check){
                BaseEntity convert = employeeKafkaMessageConverter.convert(k, human);
                negativesService.process(convert);
            }
        };
    }
}
