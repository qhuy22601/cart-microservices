package com.microservices.test2.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    private Long id;
    private String phoneNumber;
}
