package com.microservices.test2.model;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Setter
@Getter
public class Cart {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long cartId;

    private Long userId;

    private String cartDetailId;

}
