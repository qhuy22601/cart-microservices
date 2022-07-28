package com.microservices.test2.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
    private Long id;

    private String name;

    private Integer quantity;

    private String description;
}
