package edu.epam.web.service;

import edu.epam.web.entity.Dish;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Map;

public class PriceCountService {
    public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    static public BigDecimal count(Map<Dish,Integer> dishMap){
        BigDecimal sum=new BigDecimal(0);
        for (Map.Entry<Dish, Integer> entry : dishMap.entrySet()) {
                BigDecimal price = entry.getKey().getPrice();
                int quantity = entry.getValue();
                BigDecimal dishSum=price.multiply(BigDecimal.valueOf(quantity));
                       sum= sum.add(dishSum);
        }
        return sum;
    }
    static public BigDecimal countDiscountPrice(BigDecimal totalPrice,BigDecimal discount){
        BigDecimal discountPrice=totalPrice.multiply(ONE_HUNDRED.subtract(discount)).divide(ONE_HUNDRED);
        discountPrice=discountPrice.setScale(2, RoundingMode.HALF_UP);
        return discountPrice;
    }
}
