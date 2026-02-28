package com.monika.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@EnableRabbit
@Configuration
public class RabbitConfig {

    public static final String ACCOUNT_EXCHANGE = "account.exchange";
    public static final String ACCOUNT_QUEUE = "account.created.queue";
    public static final String ROUTING_KEY = "account.created";

    @Bean
    Queue accountQueue() {
        return new Queue(ACCOUNT_QUEUE, true);
    }

    @Bean
    DirectExchange accountExchange() {
        return new DirectExchange(ACCOUNT_EXCHANGE);
    }

    @Bean
    Binding binding(Queue accountQueue, DirectExchange accountExchange) {
        return BindingBuilder
                .bind(accountQueue)
                .to(accountExchange)
                .with(ROUTING_KEY);
    }
}
