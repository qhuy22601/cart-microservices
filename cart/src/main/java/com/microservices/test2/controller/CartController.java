package com.microservices.test2.controller;

import com.microservices.test2.model.*;
import com.microservices.test2.service.CartService;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PostMapping("/additem/")
    public ResponseEntity<CartDetail> addItem(@RequestParam("name") String name, @RequestParam("uId") Long userId, @RequestParam("cId") Long cartId, @RequestParam("amount") Integer amount) {
        return new ResponseEntity<>(cartService.addItem(name, userId, cartId, amount), HttpStatus.OK);
    }
    @GetMapping("/getitem/")
    public ResponseEntity<CartDetail> getItemFromCart(@RequestParam("name") String name, @RequestParam("uId") Long userId, @RequestParam("cId") Long cartId, @RequestParam("amount") Integer amount) {
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

    @GetMapping("/seecart")
    public ResponseEntity<List<CartDetail>> seeCart(){
        return new ResponseEntity<>(cartService.seeCart(), HttpStatus.OK);
    }

    @GetMapping("/findbyId/{id}")
    public ResponseEntity<Optional<Cart>> findCart(@PathVariable("id") Long id){
        return new ResponseEntity<>(cartService.getCart(id),HttpStatus.OK);
    }

    @GetMapping("/cartdetail")
    public ResponseEntity<Optional<CartDetail>> findDetailById(@RequestParam("id") Long id){
        return new ResponseEntity<>(cartService.findDetailById(id),HttpStatus.OK);
    }

//    @PutMapping("/order/{id}")
//    public ResponseEntity<Product> order(@PathVariable("id") Long id, @RequestParam Integer quantity){
//        return new ResponseEntity<>(cartService.testOrder(id, quantity), HttpStatus.OK);
//    }

//    cart user product
    //post user comment
}
