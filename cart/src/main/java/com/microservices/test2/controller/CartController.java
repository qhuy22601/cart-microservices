package com.microservices.test2.controller;

import com.microservices.test2.model.Cart;
import com.microservices.test2.model.CartDetail;
import com.microservices.test2.model.Product;
import com.microservices.test2.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public void save(@RequestBody Cart cart){
            cartService.addItemToCartService(cart);
    }

    @DeleteMapping("/remove")
    public void remove(Cart cart){
        cartService.deleteItemFromCartService(cart);
    }

    @GetMapping("/get/{id}")
    public CartDetail displayAllItemInCart(@PathVariable("id") Long userId){
        return cartService.displayAllItemFromCartService(userId);
    }

    @PutMapping("/order/{id}")
    public ResponseEntity<Product> order(@PathVariable("id") Long id, @RequestParam Integer quantity){
        return new ResponseEntity<>(cartService.testOrder(id, quantity), HttpStatus.OK);
    }


}
