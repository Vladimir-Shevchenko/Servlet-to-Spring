package com.BeanPostProcessor;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
public class TestClass {


    @Timed
    @SneakyThrows
    public void testedMethod() {
//        try {
           Thread.sleep(1500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}