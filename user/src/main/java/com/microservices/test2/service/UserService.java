package com.microservices.test2.service;


import com.microservices.test2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.microservices.test2.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    public User getById(long id){
        return userRepo.findById(id).get();
    }

    public User save(User user){
        return userRepo.save(user);
    }
}
