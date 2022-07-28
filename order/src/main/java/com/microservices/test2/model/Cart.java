package com.microservices.test2.model;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cart {
    private Long id;

    private List<Product> products;
}
