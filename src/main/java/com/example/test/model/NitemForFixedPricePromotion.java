package com.example.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class NitemForFixedPricePromotion extends  Promotion {
    private String sku;
    private Integer numberOfItems;
    private BigDecimal fixedPrice;
}
