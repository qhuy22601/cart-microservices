package com.microservices.test2.controller;


import com.microservices.test2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.microservices.test2.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/get/{id}")
    private ResponseEntity<User> getById(@PathVariable("id") long id){
        return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    private ResponseEntity<User> save(@RequestBody User user){
        return new ResponseEntity<>(userService.save(user),HttpStatus.OK);
    }
}
