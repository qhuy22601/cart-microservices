package com.microservices.test2.model;

import lombok.*;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class User {
    private Long id;
    private String phoneNumber;
    private String password;

    private Double balance;
}
