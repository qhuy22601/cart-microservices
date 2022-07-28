package com.microservices.test2.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CartDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long cartDetailId;

    private Long userId;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> products ;
}


