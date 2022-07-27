package com.microservices.test2.service;

import com.microservices.test2.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Component
@FeignClient(name = "PRODUCT_SERVICE", url = "localhost:8082")
public interface ProductDetailProxy {

    @GetMapping("/api/product/get/{id}")
    public Product  getProductById(@PathVariable("id") Long id);
}
