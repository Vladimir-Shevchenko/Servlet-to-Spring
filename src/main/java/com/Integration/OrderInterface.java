package com.Integration;


import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway(name = "helloGateway", defaultRequestChannel = "channel")
public interface OrderInterface {

    OrderState sendMessage();
}
