package com.BeanPostProcessor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



public class ContextForBeanPostProcessor {

    public void startContext(){

        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(TestClass.class, TimeBeanPostProcessor.class);

         // applicationContext.getBean(TestClass.class);

    }
}
