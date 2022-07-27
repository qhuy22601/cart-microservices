package com.microservices.test2.controller;

import com.microservices.test2.model.Product;
import com.microservices.test2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/save")
    public Product save(@RequestBody Product product){
        return productService.save(product);
    }
    @GetMapping("/getall")
    public List<Product> getAll(){
        return productService.getAllProduct();
    }

    @GetMapping("/get")
    public List<Product> get(@RequestParam Long id){
        return productService.findAllById(id);
    }
    @GetMapping("/find/{name}")
    public List<Product> find(@PathVariable("name") String name){
        return productService.getProductByName(name);
    }

    @PutMapping("/order/{id}/")
    public ResponseEntity<Product> order(@PathVariable("id") Long id, @RequestParam Integer quantity){
        return new ResponseEntity<>(productService.reduceQuantity( id, quantity), HttpStatus.OK);
    }
}
