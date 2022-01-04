package com.example.test.common;

import com.example.test.model.CombinedItemFixedPricePromotion;
import com.example.test.model.NitemForFixedPricePromotion;
import com.example.test.model.Promotion;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
@Transactional
public class PromotionsCache implements InitializingBean {

    private Map<String, Promotion> promotionsMap = new HashMap<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        initializeCache();
    }

    /*Initialize the Map with Promotion Rules
     * */
    public void initializeCache() {
        promotionsMap.put( "A", new NitemForFixedPricePromotion("A", 3, new BigDecimal(130)));
        promotionsMap.put( "B", new NitemForFixedPricePromotion("B", 2, new BigDecimal(45)));
        promotionsMap.put( "C", new CombinedItemFixedPricePromotion( Arrays.asList("C","D"),new BigDecimal(30)));
    }

    public Map<String, Promotion> getPromotionsMap() {
        return promotionsMap;
    }

    public void setPromotionsMap(Map<String, Promotion> promotionsMap) {
        this.promotionsMap = promotionsMap;
    }
}
