package com.microservices.test2.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDetail {
    private Long order_id;

    private Long User_id;

    private Integer quantity;

    private Boolean del_flg;
}
