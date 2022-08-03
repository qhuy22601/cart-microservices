package com.microservices.test2.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.test2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.microservices.test2.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    ObjectMapper om = new ObjectMapper();

    @Value("${user.topic.name}")
    private String userTopic;

    public User getById(long id) {
        User user = userRepo.findById(id).get();

        try {
            String userStr = om.writeValueAsString(user);
            kafkaTemplate.send(userStr, userTopic );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return user;

    }

    public User save(User user) {
        return userRepo.save(user);
    }
}
