package edu.epam.web.service;

import edu.epam.web.entity.User;
import edu.epam.web.entity.UserStatus;

public class UserStatusPointService {
    public static UserStatus identifyStatus(User user){
        if (user.getStatusPoint()<1000){
            return UserStatus.SILVER;
        }else if (user.getStatusPoint()<3000){
            return UserStatus.GOLD;
        }else return UserStatus.DIAMOND;
    }
}
