package com.POS.POSTienda.service;

import com.POS.POSTienda.dto.CreateSaleDTO;
import com.POS.POSTienda.dto.SaleResponseDTO;

public interface SaleService {

    SaleResponseDTO createSale(CreateSaleDTO dto);
}
