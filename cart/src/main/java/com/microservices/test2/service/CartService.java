package com.microservices.test2.service;

import com.microservices.test2.model.*;
import com.microservices.test2.repository.CartDetailRepository;
import com.microservices.test2.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private CartDetailRepository cartDetailRepo;
    @Autowired
    private WebClient.Builder webConfig;
//
//    @Autowired
//    private  ProductDetailProxy detailProxy;

    public void addItemToCartService(Cart cart){
        cartRepo.save(cart);
    }



    // user webclient to fetch product by id -> product.setquantity = amount
    // cartDetailList.add( prod) display cartdetail


    public CartDetail getItemFromCartDetail(String name, Long userId, Long cardId){
        CartDetail cartDetail = new CartDetail();
//        Optional<Product> productOpt = productRepo.findById(product.getId());
//        Product prod = productOpt.get();
//        String name = product.getName();
        Product fetchProd = webConfig.build().get()
                .uri("http://localhost:8082/api/product/find/", uriBuilder -> uriBuilder.queryParam("name",name).build())
                .retrieve()
                .bodyToMono(Product.class)
                .block();
//        Product prod  = new Product();
        log.info(String.valueOf(fetchProd));

        cartDetail.setUserId(userId);
        cartDetail.setCartDetailId(cardId);

        cartDetail.getProducts().add(fetchProd);

        return cartDetail;
    }

    public CartDetail createCartDetail(CartDetail cartDetail){
        return cartDetailRepo.save(cartDetail);
    }

    public CartDetail addItem(String name, Long userId, Long cartId, Integer amount){
        CartDetail cartDetail = new CartDetail();
//        Optional<Product> productOpt = productRepo.findById(product.getId());
//        Product prod = productOpt.get();
//        String name = product.getName();
        Product fetchProd = webConfig.build().get()
                .uri("http://localhost:8082/api/product/find/", uriBuilder -> uriBuilder.queryParam("name",name).build())
                .retrieve()
                .bodyToMono(Product.class)
                .block();
//        Product prod  = new Product();
        fetchProd.setQuantity(amount);
        cartDetail.setUserId(userId);
        cartDetail.setCartDetailId(cartId);
        List<Product> productList = new ArrayList<>();
        productList.add(fetchProd);


        cartDetail.setProducts(productList);
        return cartDetailRepo.save(cartDetail);
    }




    // create cart detail, create product with amount prop, add product into cart detail
    //test create addAmountDTO userid, productid, amount


    //use DTO

//    public CartDetail addItem(Product prod, Long userId, Long cartDetailId){
//        CartDetail cartDetail = new CartDetail();
//        cartDetail.setUserId(userId);
//        cartDetail.setCartDetailId(cartDetailId);
//
//        Product product = new Product();
//        product.setId(prod.getId());
//        product.setQuantity(prod.getQuantity());
//        product.setName(prod.getName());
//        product.setDescription(prod.getDescription());
//        List<Product> productList = new ArrayList<>();
//        productList.add(product);
//        cartDetail.setProducts(productList);
//        return cartDetail;
//    }

    public void deleteItemFromCartService(Cart cart){
        cartRepo.delete(cart);
    }

    public CartDetail deteleCartDetail(){
        CartDetail cartDetail = new CartDetail();
        return cartDetail;
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




    public CartDetail displayAllItemFromCartService(Long userId, Long cartDetailId){
        CartDetail  cartDetail = new CartDetail();
        cartDetail.setUserId(userId);
        cartDetail.setCartDetailId(cartDetailId);

//        List<String> names = cartDetail.getProducts().stream()
//                .map(Product::getName )
//                .collect(Collectors.toList());

//        List<String> id = new ArrayList<>();
//        id.add("1");
//        id.add("2");
//
//        List<Cart> cartList = new ArrayList<>();
//        List<Product> productList = new ArrayList<>();
//
//        Product[] products = webConfig.build().get()
//                .uri("http://localhost:8082/api/product/get", uriBuilder -> uriBuilder.queryParam("id" , id).build())
//                .retrieve()
//                .bodyToMono(Product[].class)
//                .block();
//        for( int i=0;i<products.length;i++){
//            productList.add(products[i]);
//        }
//        cartDetail.setProducts(productList);
        return cartDetail;
    }





}
