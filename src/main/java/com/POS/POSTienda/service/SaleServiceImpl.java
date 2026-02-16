package com.POS.POSTienda.service;

import com.POS.POSTienda.dto.CreateSaleDTO;
import com.POS.POSTienda.dto.CreateSaleItemDTO;
import com.POS.POSTienda.dto.SaleItemResponseDTO;
import com.POS.POSTienda.dto.SaleResponseDTO;
import com.POS.POSTienda.model.Product;
import com.POS.POSTienda.model.Sale;
import com.POS.POSTienda.model.SaleItem;
import com.POS.POSTienda.model.User;
import com.POS.POSTienda.repository.ProductRepository;
import com.POS.POSTienda.repository.SaleRepository;
import com.POS.POSTienda.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService{

    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;


    @Override
    @Transactional
    public SaleResponseDTO createSale(CreateSaleDTO dto) {

        User user = userRepository.findById(dto.getUserID())
                .orElseThrow(()-> new RuntimeException("User not found"));

        Sale sale = new Sale();
        sale.setDate(LocalDateTime.now());
        sale.setUser(user);
        sale.setItems(new ArrayList<>());

        Double total = 0.0;

        for (CreateSaleItemDTO itemDTO : dto.getItems()) {

            Product product = productRepository.findByIdAndActiveTrue(itemDTO.getProductId())
                    .orElseThrow(()-> new RuntimeException("Product not dounf or inactive"));

            if (product.getStock() < itemDTO.getAmount()){
                throw new RuntimeException("Insufficient stock for product: " + product.getName());
            }

            product.setStock(product.getStock() - itemDTO.getAmount());

            SaleItem saleItem = new SaleItem();
            saleItem.setSale(sale);
            saleItem.setProduct(product);
            saleItem.setAmount(itemDTO.getAmount());
            saleItem.setPrice(product.getPrice());

            sale.getItems().add(saleItem);

            total += product.getPrice() * itemDTO.getAmount();

        }

        sale.setTotal(total);

        Sale savedSale = saleRepository.save(sale);

        return mapToResponseDTO(savedSale);
    }

    private SaleResponseDTO mapToResponseDTO(Sale sale){

        SaleResponseDTO dto = new SaleResponseDTO();
        dto.setSaleID(sale.getId());
        dto.setDate(sale.getDate());
        dto.setUsername(sale.getUser().getName());
        dto.setTotal(sale.getTotal());

        List<SaleItemResponseDTO> items = new ArrayList<>();

        for (SaleItem item : sale.getItems()) {

            SaleItemResponseDTO itemDTO = new SaleItemResponseDTO();
            itemDTO.setProductName(item.getProduct().getName());
            itemDTO.setAmount(item.getAmount());
            itemDTO.setPrice(item.getPrice());
            itemDTO.setSubtotal(item.getPrice() * item.getAmount());

            items.add(itemDTO);
        }

        dto.setItems(items);

        return dto;
    }
}
