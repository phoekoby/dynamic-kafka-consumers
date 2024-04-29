package com.example.dynamickafkaconsumers.service;

public interface BusinessLogicService<E, P> {
    P doBusinessLogic(E e);
}
