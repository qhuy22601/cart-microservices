package com.microservices.test2.controller;

import com.microservices.test2.model.*;
import com.microservices.test2.service.CartService;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@Slf4j
public class CartController {

    @Autowired
    private CartService cartService;


    @PostMapping("/add")
    public void save(@RequestBody Cart cart) {
        cartService.addItemToCartService(cart);
    }

    @DeleteMapping("/remove")
    public void remove(Cart cart) {
        cartService.deleteItemFromCartService(cart);
    }

    @GetMapping("/get/{userId}/{cartDetailId}")
    public CartDetail displayAllItemInCart(@PathVariable("userId") Long userId, @PathVariable("cartDetailId") Long cartDetailId) {
        return cartService.displayAllItemFromCartService(userId, cartDetailId);
    }

//    @PostMapping("/additem/{userId}/{cartDetailId}")
//    public ResponseEntity<CartDetail> addAmount(@RequestBody Product product, @PathVariable("userId") Long uId, @PathVariable("cartDetailId") Long cId){
//        log.info("da them product vao " + uId +product+cId);
//
//        return new ResponseEntity<>(cartService.addItem(product, uId, cId), HttpStatus.OK);
//    }

    @PostMapping("/additem/{name}/{uId}/{cId}/{amount}")
    public ResponseEntity<CartDetail> addItem(@PathVariable("name") String name, @PathVariable("uId") Long userId, @PathVariable("cId") Long cartId, @PathVariable("amount") Integer amount) {
        return new ResponseEntity<>(cartService.addItem(name, userId, cartId, amount), HttpStatus.OK);
    }
    @GetMapping("/getfrom/{name}/{userId}/{cartId}")
    public ResponseEntity<CartDetail> getFrom(@PathVariable("name") String name, @PathVariable("userId") Long userId, @PathVariable("cartId") Long cartId){
        return new ResponseEntity<>(cartService.getItemFromCartDetail(name, userId,cartId),HttpStatus.OK);
    }

    @DeleteMapping("/delcartdetail")
    public CartDetail delCardDetail() {
        return cartService.deteleCartDetail();
    }

    @PostMapping("/createdetail")
        public ResponseEntity<CartDetail> createCartDetail(@RequestBody CartDetail cartDetail){
            return new ResponseEntity<>(cartService.createCartDetail(cartDetail),HttpStatus.OK);
        }



//    @PutMapping("/order/{id}")
//    public ResponseEntity<Product> order(@PathVariable("id") Long id, @RequestParam Integer quantity){
//        return new ResponseEntity<>(cartService.testOrder(id, quantity), HttpStatus.OK);
//    }

//    cart user product
    //post user comment
}
