package com.POS.POSTienda.repository;

import com.POS.POSTienda.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByCode(String code);

    List<Product> findActiveTrue();

    Optional<Product> findByIdAndActiveTrue(Long id);


}
