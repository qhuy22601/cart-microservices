package com.microservices.test2.repo;

import com.microservices.test2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo  extends JpaRepository<User, Long> {
}
