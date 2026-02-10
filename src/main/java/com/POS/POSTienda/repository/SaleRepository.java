package com.POS.POSTienda.repository;

import com.POS.POSTienda.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
