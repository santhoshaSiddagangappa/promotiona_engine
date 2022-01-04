package com.example.test;

import com.example.test.model.Product;
import com.example.test.model.SKU;
import com.example.test.service.PriceCalculatorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class PromotionEngineApplicationTests {

    @Autowired
    PriceCalculatorService priceCalculatorService;

    @Test
    void contextLoads() {
    }

    @Test
    public void calculateCartPriceTest1() {
        List<Product> cartItems = new ArrayList<>();
        SKU skuA = new SKU("A", new BigDecimal(50.0));
        Product productA = new Product(skuA, 1);
        SKU skuB = new SKU("B", new BigDecimal(30.0));
        Product productB = new Product(skuB, 1);
        SKU skuC = new SKU("C", new BigDecimal(20.0));
        Product productC = new Product(skuC, 1);
        cartItems.add(productA);
        cartItems.add(productB);
        cartItems.add(productC);
        BigDecimal finalPrice = priceCalculatorService.calculatePrice(cartItems);
        Assertions.assertEquals(new BigDecimal(100.0), finalPrice);
    }

    @Test
    public void calculateCartPriceTest2() {
        List<Product> cartItems = new ArrayList<>();
        SKU skuA = new SKU("A", new BigDecimal(50.0));
        Product productA = new Product(skuA, 5);
        SKU skuB = new SKU("B", new BigDecimal(30.0));
        Product productB = new Product(skuB, 5);
        SKU skuC = new SKU("C", new BigDecimal(20.0));
        Product productC = new Product(skuC, 1);
        cartItems.add(productA);
        cartItems.add(productB);
        cartItems.add(productC);
        BigDecimal finalPrice = priceCalculatorService.calculatePrice(cartItems);
        Assertions.assertEquals(new BigDecimal(370.0), finalPrice);
    }

    @Test
    public void calculateCartPriceTest3() {
        List<Product> cartItems = new ArrayList<>();
        SKU skuA = new SKU("A", new BigDecimal(50.0));
        Product productA = new Product(skuA, 3);
        SKU skuB = new SKU("B", new BigDecimal(30.0));
        Product productB = new Product(skuB, 5);
        SKU skuC = new SKU("C", new BigDecimal(20.0));
        Product productC = new Product(skuC, 1);
        SKU skuD = new SKU("D", new BigDecimal(15.0));
        Product productD = new Product(skuD, 1);
        cartItems.add(productA);
        cartItems.add(productB);
        cartItems.add(productC);
        cartItems.add(productD);
        BigDecimal finalPrice = priceCalculatorService.calculatePrice(cartItems);
        Assertions.assertEquals(new BigDecimal(280.0), finalPrice);
    }

}
