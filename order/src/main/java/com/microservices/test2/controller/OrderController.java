package com.microservices.test2.controller;

import com.microservices.test2.model.CartDetail;
import com.microservices.test2.model.OrderDetail;
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


//    @PutMapping("/ordered/{id}")
//    public ResponseEntity<Product> order(@PathVariable("id") Long id, @RequestParam Integer amount){
//        return new ResponseEntity<>(orderService.orderPlace(id, amount), HttpStatus.OK);
//    }

    @PostMapping("/seecart/{cartId}/{orderId}")
    public ResponseEntity<OrderDetail> seeCart( @PathVariable("cartId") Long cartId, @PathVariable("orderId") Long orderid){
        return new ResponseEntity<>(orderService.getProdFromCart(cartId,orderid), HttpStatus.OK);
    }
}
