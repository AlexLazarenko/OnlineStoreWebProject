package edu.epam.web.service;

import edu.epam.web.entity.User;
import edu.epam.web.entity.UserStatus;

import java.math.BigDecimal;

public class UserStatusPointService {
    public static UserStatus identifyStatus(BigDecimal statusPoint ){
        if (statusPoint.compareTo(BigDecimal.valueOf(1000))<0){
            return UserStatus.SILVER;
        }else if (statusPoint.compareTo(BigDecimal.valueOf(3000))<0){
            return UserStatus.GOLD;
        }else return UserStatus.DIAMOND;
    }
}
