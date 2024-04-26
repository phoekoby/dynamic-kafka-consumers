package com.example.dynamickafkaconsumers.service;

import com.example.dynamickafkaconsumers.aop.AopAnnotation;
import com.example.dynamickafkaconsumers.entity.BaseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class NegativesService {


    @AopAnnotation
    @Transactional
    public void process(BaseEntity entity){
        log.info("NegativesService process---");
        System.out.println(entity);
    }
}
