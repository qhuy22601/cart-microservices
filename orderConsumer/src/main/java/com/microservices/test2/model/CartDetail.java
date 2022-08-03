package com.microservices.test2.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Getter
@Setter
public class CartDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartDetailId")
    private Long cartDetailId;

    private Long UserId;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> products;
}
