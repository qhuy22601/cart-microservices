package com.microservices.test2.controller;

import com.microservices.test2.model.Product;
import com.microservices.test2.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;


    @PutMapping("/ordered/{id}")
    public ResponseEntity<Product> order(@PathVariable("id") Long id, @RequestParam Integer amount){
        return new ResponseEntity<>(orderService.orderPlace(id, amount), HttpStatus.OK);
    }
}
