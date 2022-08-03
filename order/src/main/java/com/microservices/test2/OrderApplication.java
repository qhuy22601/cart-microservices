package com.microservices.test2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;

import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableKafka
@EnableConfigurationProperties
public class OrderApplication {

    @Bean
    public WebClient.Builder webClient(){
        return WebClient.builder();
    }

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
