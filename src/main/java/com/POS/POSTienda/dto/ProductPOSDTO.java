package com.POS.POSTienda.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductPOSDTO {
    private Long id;
    private String code;
    private String name;
    private Double price;
}
