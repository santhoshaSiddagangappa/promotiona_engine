package com.example.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Product {
    private SKU sku;
    private Integer quantity;
    private BigDecimal totalPrice;
}
