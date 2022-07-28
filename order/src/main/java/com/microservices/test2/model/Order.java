package com.microservices.test2.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Getter
@Setter
@Table(name = "ordered")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Date orderDate;

    private String address;

    private Double totalBill;

    private Long voucherId;

    private String paymentMethod;

    private String shippingMethod;







}
