package com.microservices.test2.repo;

import com.microservices.test2.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {

}
