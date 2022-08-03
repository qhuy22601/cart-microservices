package com.microservices.test2.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.test2.model.CartDetail;
import com.microservices.test2.model.Order;
import com.microservices.test2.model.OrderDetail;
import com.microservices.test2.model.Product;
import com.microservices.test2.repository.OrderDetailRepository;
import com.microservices.test2.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepo;
    @Autowired
    private OrderDetailRepository orderDetailRepo;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    ObjectMapper om = new ObjectMapper();

    @Value("${order.topic.name}")
    private String topicName;

    @Autowired
    private WebClient.Builder webConfig;

//    public Product orderPlace(Long id, Integer amount){
//
//        String uri = UriComponentsBuilder.fromUriString("http://localhost:8082/api/product/order/{id}/")
//                .queryParam("quantity", amount)
//                .build().toUriString();
//        Product product= webConfig.build().put()
//                .uri(uri,id)
//                .retrieve()
//                .bodyToMono(Product.class)
//                .block();
//        return product;
//    }


    public OrderDetail orderReal(OrderDetail orderDetail){
        orderDetail = orderDetailRepo.save(orderDetail);
        try {
            String orderStr = om.writeValueAsString(orderDetail);
            kafkaTemplate.send(topicName, orderStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return orderDetail;
    }

    public OrderDetail getProdFromCart(Long cartDetailid, Long orderId){
//
//        String uri =UriComponentsBuilder.fromUriString("http://localhost:8084/api/cart/cartdetail/")
//                .queryParam("id", cartDetailid)
//                .build()
//                .toUriString();
        CartDetail cartDetail = webConfig.build().get()
                .uri("http://localhost:8084/api/cart/cartdetail", uriBuilder -> uriBuilder.queryParam("id", cartDetailid).build())
                .retrieve()
                .bodyToMono(CartDetail.class)
                .block();

        List<Product> product =cartDetail.getProducts();
        for(int i=0;i<product.size();i++){
            Product prod = product.get(i);
            Long prodId = prod.getId();
            Integer amount  = prod.getQuantity();
            String url = UriComponentsBuilder.fromUriString("http://localhost:8082/api/product/order/{id}/")
                .queryParam("quantity", amount)
                .build().toUriString();
            Product productReal= webConfig.build().put()
                .uri(url,prodId)
                .retrieve()
                .bodyToMono(Product.class)
                .block();
        }
        CartDetail detail = new CartDetail();
        OrderDetail orderDetail = new OrderDetail();
        detail.setProducts(product);
        detail.setCartDetailId(cartDetailid);
        detail.setUserId(cartDetail.getUserId());
        orderDetail.setOrder_id(orderId);
        orderDetail.setUser_id(cartDetail.getUserId());
        orderDetail.setCartDetail(detail);
        return orderDetailRepo.save(orderDetail);
    }


}


