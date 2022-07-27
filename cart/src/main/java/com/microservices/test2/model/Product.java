package com.microservices.test2.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Product {

    private Long id;

    private String name;

    private String description;

    private Integer quantity;

}
