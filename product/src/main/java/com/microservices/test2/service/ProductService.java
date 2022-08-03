package com.microservices.test2.service;


import com.microservices.test2.model.Product;
import com.microservices.test2.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductService {
    @Autowired
    private ProductRepository productRepo;

    public Product save(Product product){
        return productRepo.save(product);
    }

    public List<Product> getAllProduct(){
        return productRepo.findAll();
    }

//    public Optional<List<Product>> getProductById(List<Long> id){
//        return productRepo.findProductsById(id);
//    }

    public Product addQuantity(Long id, Integer quantity){
        Optional<Product> productOptional = productRepo.findById(id);
        if(productOptional.isEmpty()){
            log.info("ko cos san pham" + id);
            return null;
        }
        else{
            Product product = productOptional.get();
            product.getName();
            product.getDescription();
            product.setQuantity(quantity);
            return productRepo.save(product);
        }
    }

    public Product getProductByName(String name){
        return productRepo.findByName(name);
    }
    public List<Product> findAllById(Long id){
        return productRepo.getAllById(id);
    }



    public Product reduceQuantity(Long id, Integer quantity) {
        Optional<Product> productOpt = productRepo.findById(id);
        Product product = productOpt.get();
        if (productOpt.isEmpty()) {
            log.error("ko co prod nay:" + id);
            return null;
        } else if (product.getQuantity() < quantity) {
            log.error("prod ko du so luong:" + product.getQuantity());
            return null;
        } else {
            product.setQuantity(product.getQuantity() - quantity);
            return productRepo.save(product);
        }
    }

}
