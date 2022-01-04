package com.example.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class SKU {
    private String identifier;
    private BigDecimal pricePerUnit;
    private String description;
}
