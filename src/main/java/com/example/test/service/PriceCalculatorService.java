package com.example.test.service;

import com.example.test.model.Product;

import java.math.BigDecimal;
import java.util.List;

interface PriceCalculatorService {
    BigDecimal calculatePrice(List<Product> cartItems);
}
