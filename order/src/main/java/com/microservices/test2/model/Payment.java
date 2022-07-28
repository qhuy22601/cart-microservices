package com.microservices.test2.model;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Payment {
    private Long id;

    private String method;

    private Boolean del_flg;
}
