package com.Integration;

import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
class Order implements OrderInterface{

    private OrderState orderState;

    @Override
    public OrderState sendMessage() {
        return  OrderState.CANCELED;
    }
}

enum OrderState {
    CANCELED,
    WAITING_FOR_PAYMENT,
    PAYMENT_COMPLETED
}

