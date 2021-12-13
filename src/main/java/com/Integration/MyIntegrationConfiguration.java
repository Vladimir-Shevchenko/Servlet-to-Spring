package com.Integration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.messaging.support.MessageBuilder;

import java.util.concurrent.atomic.AtomicInteger;

@Configuration
@EnableIntegration
public class MyIntegrationConfiguration {

    @Bean
    public AtomicInteger integerSource(){
        return new AtomicInteger();
    }

    @Bean
    public IntegrationFlow myFlow(){
       return   IntegrationFlows
                .fromSupplier(integerSource()::getAndIncrement, c -> c.poller(Pollers.fixedRate(10)))
                .channel("inputChannel")
                .filter((Integer p)->p>1)
                .transform(Object::toString)
                .channel(MessageChannels.queue())
                .get();

    }

    public void getAndIncrement(){}

}
