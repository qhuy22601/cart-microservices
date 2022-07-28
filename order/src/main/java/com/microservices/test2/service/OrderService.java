package com.microservices.test2.service;

import com.microservices.test2.model.Product;
import com.microservices.test2.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private WebClient.Builder webConfig;

    public Product orderPlace(Long id, Integer amount){

        String uri = UriComponentsBuilder.fromUriString("http://localhost:8082/api/product/order/{id}/")
                .queryParam("quantity", amount)
                .build().toUriString();
        Product product= webConfig.build().put()
                .uri(uri,id)
                .retrieve()
                .bodyToMono(Product.class)
                .block();
        return product;
    }}
