package com.microservices.test2.repository;

import com.microservices.test2.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByName(String name);

    Product findByName(String name);

//    Optional<List<Product>> findProductsById (List<Long> id);

    List<Product> getAllById(Long id);

    @Query("SELECT p FROM  Product p WHERE p.name LIKE %:name%")
    List<Product> searchByNameLike(@Param("name") String name);

}
