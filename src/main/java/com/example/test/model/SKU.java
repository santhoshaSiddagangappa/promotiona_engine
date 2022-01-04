package com.example.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class SKU {
    private String identifier;
    private BigDecimal pricePerUnit;
    private String description;

    public SKU(String identifier) {
        this.identifier = identifier;
    }

    public SKU(String identifier, BigDecimal pricePerUnit) {
        this.identifier = identifier;
        this.pricePerUnit = pricePerUnit;
    }
}
