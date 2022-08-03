package com.microservices.test2.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_id;

    private Long user_id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="cartDetail", referencedColumnName = "cartDetailId")
    private CartDetail cartDetail;

    private Boolean del_flg;
}
