package com.example.test.service;

import com.example.test.common.PromotionsCache;
import com.example.test.model.CombinedItemFixedPricePromotion;
import com.example.test.model.NitemForFixedPricePromotion;
import com.example.test.model.Product;
import com.example.test.model.Promotion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class PriceCalculatorServiceImpl implements PriceCalculatorService {

    @Autowired
    private PromotionsCache promotionsCache;

    @Override
    public BigDecimal calculatePrice(List<Product> cartItems) {
        BigDecimal finalPrice = new BigDecimal(0.0);
        finalPrice = applyPromotions(cartItems);
        return finalPrice;
    }

    private BigDecimal applyPromotions(List<Product> cartItems) {
        BigDecimal finalPrice = new BigDecimal(0.0);
        Map<String, Promotion> promotionsMap = promotionsCache.getPromotionsMap();
        List<String> allItems = new ArrayList<>();
        for (Product item : cartItems) {
            allItems.add(item.getSku().getIdentifier());
        }
        for (Product item : cartItems) {
            String identifier = item.getSku().getIdentifier();
            if (promotionsMap.containsKey(identifier)) {
                if (promotionsMap.get(identifier) instanceof CombinedItemFixedPricePromotion) {
                    CombinedItemFixedPricePromotion combinedItemFixedPricePromotion = (CombinedItemFixedPricePromotion) promotionsMap.get(identifier);
                    List<String> sku = combinedItemFixedPricePromotion.getSku();
                    String sku1 = sku.get(0);
                    String sku2 = sku.get(1);
                    Integer skuCQuantity = item.getQuantity();
                    if (sku1.equalsIgnoreCase(identifier)) {
                        if (allItems.contains(sku2)) {
                            Integer skuDQuantity = 0;
                            for (Product pro : cartItems) {
                                if (pro.getSku().getIdentifier().equalsIgnoreCase(sku2)) {
                                    skuDQuantity = pro.getQuantity();
                                    if (skuCQuantity == skuDQuantity) {
                                        finalPrice = finalPrice.add(combinedItemFixedPricePromotion.getFixedPrice().multiply(BigDecimal.valueOf(skuCQuantity)));
                                    } else if (skuCQuantity > skuDQuantity) {
                                        finalPrice = finalPrice.add(combinedItemFixedPricePromotion.getFixedPrice().multiply(BigDecimal.valueOf(skuDQuantity)));
                                        finalPrice = finalPrice.add(item.getSku().getPricePerUnit().multiply(BigDecimal.valueOf(skuCQuantity - skuDQuantity)));
                                    } else {
                                        finalPrice = finalPrice.add(combinedItemFixedPricePromotion.getFixedPrice().multiply(BigDecimal.valueOf(skuCQuantity)));
                                        finalPrice = finalPrice.add(pro.getSku().getPricePerUnit().multiply(BigDecimal.valueOf(skuDQuantity - skuCQuantity)));
                                    }
                                }
                            }
                            cartItems.remove(3);
                        } else {
                            finalPrice = finalPrice.add(item.getSku().getPricePerUnit().multiply(BigDecimal.valueOf(skuCQuantity)));
                        }
                    }
                } else if (promotionsMap.get(identifier) instanceof NitemForFixedPricePromotion) {
                    NitemForFixedPricePromotion nitemForFixedPricePromotion = (NitemForFixedPricePromotion) promotionsMap.get(identifier);
                    Integer minItems = nitemForFixedPricePromotion.getNumberOfItems();
                    Integer quantity = item.getQuantity();
                    while (quantity >= minItems) {
                        finalPrice = finalPrice.add(nitemForFixedPricePromotion.getFixedPrice());
                        quantity -= minItems;
                    }
                    finalPrice = finalPrice.add(item.getSku().getPricePerUnit().multiply(BigDecimal.valueOf(quantity)));
                }
            } else {
                finalPrice = finalPrice.add(item.getSku().getPricePerUnit().multiply(BigDecimal.valueOf(item.getQuantity())));
            }
        }
        return finalPrice;
    }
}
