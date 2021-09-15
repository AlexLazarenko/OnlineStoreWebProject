package edu.epam.web.model.factory;

import edu.epam.web.model.entity.*;
import edu.epam.web.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.Date;

public class UserFactory {
    private static final Logger logger = LogManager.getLogger(UserFactory.class);
    private final UserValidator validator = new UserValidator();
    //todo optional
    public User createNewUser(int id, String telephoneNumber, String surname,
                           String name, Date birthday, UserGender gender, String email,
                            String avatar)   {
        User user = null;
        if (validator.isUser( telephoneNumber, surname, name, birthday, email)) {
            user = new User(id, telephoneNumber, surname, name, birthday, gender, email,
                    BigDecimal.valueOf(0), UserRole.CLIENT, avatar, UserStatus.SILVER,  AccountStatus.NEW);
        }
        logger.info("User created " + user);
        return user;
    }
    public User createUser(int id, String telephoneNumber, String surname,
                              String name, Date birthday, UserGender gender, String email,
                              String avatar,BigDecimal statusPoint, UserRole userRole,UserStatus userStatus,
                           AccountStatus accountStatus)  {
        User user = null;
        if (validator.isUser( telephoneNumber, surname, name, birthday, email)) {
            user = new User(id, telephoneNumber, surname, name, birthday, gender, email,
                    statusPoint, userRole, avatar, userStatus, accountStatus);
        }
        logger.info("User created " + user);
        return user;
    }
}
