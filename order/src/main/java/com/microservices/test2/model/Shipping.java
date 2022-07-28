package com.microservices.test2.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Shipping {

    private Long id;

    private String method;

    private Boolean del_flg;


}
