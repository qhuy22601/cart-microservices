package com.microservices.test2.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartDetail {


    public Long userid;

    public List<Product> products;
}


