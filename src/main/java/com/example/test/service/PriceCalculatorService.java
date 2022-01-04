package com.example.test.service;

import com.example.test.model.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface PriceCalculatorService {
    BigDecimal calculatePrice(List<Product> cartItems);
}
