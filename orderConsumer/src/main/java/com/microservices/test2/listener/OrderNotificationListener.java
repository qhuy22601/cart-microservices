package com.microservices.test2.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.test2.model.Order;
import com.microservices.test2.model.User;
import com.microservices.test2.repo.OrderRepo;
import com.microservices.test2.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderNotificationListener {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private UserRepo userRepo;

    ObjectMapper om = new ObjectMapper();


    @KafkaListener(topics = {"orderTopic", "userTopic"}, groupId = "groupId")
    public void orderProccessing(String orderStr, String userStr) {
        System.out.println("order test:" + orderStr);
        try {
            Order order = om.readValue(orderStr, Order.class);
            User user = userRepo.findById(order.getUserId()).get();
            if(user.getBalance()< order.getTotalBill()){
                log.info("ko du tien thanh toan");
            }else {
                user.setBalance(user.getBalance()-order.getTotalBill());
                log.info("da thanh toan so du la: "+ (user.getBalance()-order.getTotalBill()));
                userRepo.save(user);
                System.out.println("user test:" +userStr);
            }
            orderRepo.save(order);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
