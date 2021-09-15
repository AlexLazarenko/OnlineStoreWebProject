package edu.epam.web.model.entity;

import java.math.BigDecimal;

public enum UserStatus {
    SILVER(BigDecimal.valueOf(5)),
    GOLD(BigDecimal.valueOf(7)),
    DIAMOND(BigDecimal.valueOf(10));

    private final BigDecimal discount;

    UserStatus(BigDecimal discount){
        this.discount=discount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }
}
