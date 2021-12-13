package com.BeanPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class TimeBeanPostProcessor implements BeanPostProcessor {
    @Override
    @Nullable
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    @Nullable
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

long start = System.currentTimeMillis();
        Method method = null;

        try {
          method =  bean.getClass().getMethod("testedMethod");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }



        if(method.isAnnotationPresent(Timed.class)){
            try {
                method.invoke(bean);

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();



       if(method.isAnnotationPresent(Timed.class)) {
           System.out.println("time:   " + (end - start));
       }


        return end - start;
    }
}
