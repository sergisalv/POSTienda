package com.POS.POSTienda.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleResponseDTO {
    private Long saleID;
    private LocalDateTime date;
    private String username;
    private List<SaleItemResponseDTO> items;
    private Double total;
}
