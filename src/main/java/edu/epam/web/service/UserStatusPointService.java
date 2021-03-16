package edu.epam.web.service;

import edu.epam.web.entity.User;
import edu.epam.web.entity.UserStatus;

public class UserStatusPointService {
    public static UserStatus identifyStatus(int statusPoint ){
        if (statusPoint<1000){
            return UserStatus.SILVER;
        }else if (statusPoint<3000){
            return UserStatus.GOLD;
        }else return UserStatus.DIAMOND;
    }
}
