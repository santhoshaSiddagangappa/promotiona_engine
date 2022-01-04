package com.example.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class CombinedItemFixedPricePromotion extends Promotion {
    private List<String> sku;
    private BigDecimal fixedPrice;
}
