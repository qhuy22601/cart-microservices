package com.microservices.test2.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ItemDTO {
    private IdObjectDTO id;

    private Product product;

}
