package com.microservices.test2.service;

import com.microservices.test2.model.Cart;
import com.microservices.test2.model.CartDetail;
import com.microservices.test2.model.Product;
import com.microservices.test2.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private WebClient.Builder webConfig;
//
//    @Autowired
//    private  ProductDetailProxy detailProxy;

    public void addItemToCartService(Cart cart){
        cartRepo.save(cart);
    }

    public void deleteItemFromCartService(Cart cart){
        cartRepo.delete(cart);
    }

//    public CartDetail displayAllItemFromCartService(Long userid){
//        CartDetail cartDetail = new CartDetail();
//        cartDetail.setUserid(userid);
//
//        ArrayList<Cart> cartArrayList = new ArrayList<>();
//        ArrayList<Product> productArrayList = new ArrayList<>();
//
//        for(int i =0; i<cartArrayList.size();i++){
//            Product product = detailProxy.getProductById(cartArrayList.get(i).getId());
//            productArrayList.add(product);
//        }
//        cartDetail.setProducts(productArrayList);
//        return cartDetail;
//    }

    public CartDetail displayAllItemFromCartService(Long userId){
        CartDetail  cartDetail = new CartDetail();
        cartDetail.setUserid(userId);

//        List<String> names = cartDetail.getProducts().stream()
//                .map(Product::getName )
//                .collect(Collectors.toList());

        List<String> id = new ArrayList<>();
        id.add("1");
        id.add("2");

        List<Cart> cartList = new ArrayList<>();
        List<Product> productList = new ArrayList<>();

        Product[] products = webConfig.build().get()
                .uri("http://localhost:8082/api/product/get", uriBuilder -> uriBuilder.queryParam("id" , id).build())
                .retrieve()
                .bodyToMono(Product[].class)
                .block();
        for( int i=0;i<products.length;i++){
            productList.add(products[i]);
        }
        cartDetail.setProducts(productList);
        return cartDetail;
    }

    public Product testOrder(Long id, Integer quantity){

        String uri = UriComponentsBuilder.fromUriString("http://localhost:8082/api/product/order/{id}/")
                .queryParam("quantity", quantity)
                .build().toUriString();
        Product product= webConfig.build().put()
                .uri(uri,id)
                .retrieve()
                .bodyToMono(Product.class)
                .block();
        return product;
    }
}
