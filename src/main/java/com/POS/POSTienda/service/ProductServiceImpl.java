package com.POS.POSTienda.service;

import com.POS.POSTienda.dto.CreateProductDTO;
import com.POS.POSTienda.dto.ProductAdminDTO;
import com.POS.POSTienda.dto.ProductPOSDTO;
import com.POS.POSTienda.dto.UpdateProductDTO;
import com.POS.POSTienda.model.Product;
import com.POS.POSTienda.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Override
    public ProductAdminDTO createProduct(CreateProductDTO dto) {
        Product product = new Product();
        product.setCode(dto.getCode());
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        return mapToAdminDTO(productRepository.save(product));
    }

    @Override
    public ProductAdminDTO updateProduct(Long id, UpdateProductDTO dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setCode(dto.getCode());
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());

        return mapToAdminDTO(productRepository.save(product));
    }

    @Override
    public List<ProductPOSDTO> getAllForPOS() {

        return productRepository.findActiveTrue()
                .stream()
                .map(this::mapToPOSDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deactivateProduct(Long id) {
        changeProductStatus(id, false);
    }

    @Override
    public void activateProduct(Long id) {
        changeProductStatus(id, true);
    }

    private void changeProductStatus(Long id, boolean status){

        Product product = productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Product not found"));

        product.setActive(status);

        productRepository.save(product);

    }

    private ProductPOSDTO mapToPOSDTO(Product product) {
        ProductPOSDTO dto = new ProductPOSDTO();
        dto.setId(product.getId());
        dto.setCode(product.getCode());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        return dto;
    }

    private ProductAdminDTO mapToAdminDTO(Product product){
        ProductAdminDTO dto = new ProductAdminDTO();
        dto.setId(product.getId());
        dto.setCode(product.getCode());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());
        return dto;

    }
}
