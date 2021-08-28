package edu.epam.web.service;

import edu.epam.web.dao.impl.UserDaoImpl;
import edu.epam.web.entity.AccountStatus;
import edu.epam.web.entity.User;
import edu.epam.web.entity.UserRole;
import edu.epam.web.exception.DaoException;
import edu.epam.web.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoService {
    private static final Logger logger = LogManager.getLogger(UserDaoService.class);
    private final UserDaoImpl userDaoImpl = new UserDaoImpl();

    public int createUser(User user, String password) throws ServiceException {
        int affectedRows = 0;
        try {
            affectedRows = userDaoImpl.createUser(user, password);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return affectedRows;
    }

    public Optional<User> findByTelephoneNumberPassword(String telephoneNumber, String password) throws ServiceException {
        try {
            Optional<User> user = userDaoImpl.findByTelephoneNumberPassword(telephoneNumber, password);
            return user;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    public Optional<User> findUserById(int id) throws ServiceException {
        try {
            Optional<User> user = userDaoImpl.findUserById(id);
            return user;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    public Optional<User> findUserByEmail(String email) throws ServiceException {
        try{
            Optional<User> user=userDaoImpl.findUserByEmail(email);
            return user;
        }catch (DaoException e){
            logger.error(e);
            throw new ServiceException(e);
        }
    }


        public List<User> findUsers() throws ServiceException {
        List<User> users;
        try {
            users = userDaoImpl.findUsers();
            return users;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }

    }

    public int updateUser(User newUser) throws ServiceException {
        int affectedRows = 0;
        try {
            affectedRows = userDaoImpl.updateUser(newUser);
            return affectedRows;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }

    }

    public int updateAvatar(int id, String avatar) throws ServiceException {
        int affectedRows = 0;
        try {
            affectedRows = userDaoImpl.updateAvatar(id, avatar);
            return affectedRows;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    public int deleteUser(int id) throws ServiceException {
        int affectedRows = 0;
        try {
            affectedRows = userDaoImpl.deleteUser(id);
            return affectedRows;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    public String findPasswordById(int id) throws ServiceException {
        String password = "";
        try {
            password = userDaoImpl.findPasswordById(id);
            return password;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    public String findUserTelephoneNumber(String telephoneNumber) throws ServiceException {
        String storedTelephoneNumber = "";
        try {
            storedTelephoneNumber = userDaoImpl.findUserTelephoneNumber(telephoneNumber);
            return storedTelephoneNumber;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    public String findUserEmail(String email) throws ServiceException {
        String storedEmail = "";
        try {
            storedEmail = userDaoImpl.findUserEmail(email);
            return storedEmail;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    public int changePassword(String email, String password) throws ServiceException {
        int affectedRows = 0;
        try {
            affectedRows = userDaoImpl.changePassword(email, password);
            return affectedRows;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    public int activateAccount(String email) throws ServiceException {
        int affectedRows = 0;
        try {
            affectedRows = userDaoImpl.activateAccount(email);
            return affectedRows;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    public int updateUserRole(int id, UserRole role) throws ServiceException {
        int affectedRows = 0;
        try {
            affectedRows = userDaoImpl.updateUserRole(id, role);
            return affectedRows;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    public int updateAccountStatus(int id, AccountStatus status) throws ServiceException {
        int affectedRows = 0;
        try {
            affectedRows = userDaoImpl.updateAccountStatus(id, status);
            return affectedRows;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }
}
