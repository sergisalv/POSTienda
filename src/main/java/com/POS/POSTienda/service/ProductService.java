package com.POS.POSTienda.service;

import com.POS.POSTienda.dto.CreateProductDTO;
import com.POS.POSTienda.dto.ProductAdminDTO;
import com.POS.POSTienda.dto.ProductPOSDTO;
import com.POS.POSTienda.dto.UpdateProductDTO;

import java.util.List;

public interface ProductService {

    ProductAdminDTO createProduct (CreateProductDTO dto);

    ProductAdminDTO updateProduct (Long id, UpdateProductDTO dto);

    List<ProductPOSDTO> getAllForPOS();

    void deactivateProduct(Long id);

    void activateProduct(Long id);
}
