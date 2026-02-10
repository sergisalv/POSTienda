package com.POS.POSTienda.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleItemResponseDTO {
    private String productName;
    private Double amount;
    private Double price;
    private Double subtotal;
}
