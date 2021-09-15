package edu.epam.web.utility;

import edu.epam.web.model.entity.UserStatus;

import java.math.BigDecimal;

public class UserStatusPointUtil {
    public static UserStatus identifyStatus(BigDecimal statusPoint ){
        if (statusPoint.compareTo(BigDecimal.valueOf(1000))<0){
            return UserStatus.SILVER;
        }else if (statusPoint.compareTo(BigDecimal.valueOf(3000))<0){
            return UserStatus.GOLD;
        }else return UserStatus.DIAMOND;
    }
}
