package com.microservices.test2.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class DouIdObjectDTO {
    private Long UserId;

    private Long CartId;
}
