package com.microservices.test2.model;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Voucher {
    private Long id;

    private String name;

    private Double value;

    private Date stated_date;

    private Date ended_date;

    private Integer quantity;

    private Boolean del_flg;
}
