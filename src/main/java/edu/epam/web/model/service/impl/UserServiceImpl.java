package edu.epam.web.model.service.impl;

import edu.epam.web.model.dao.UserDao;
import edu.epam.web.model.dao.impl.UserDaoImpl;
import edu.epam.web.model.entity.AccountStatus;
import edu.epam.web.model.entity.User;
import edu.epam.web.model.entity.UserRole;
import edu.epam.web.exception.DaoException;
import edu.epam.web.exception.ServiceException;
import edu.epam.web.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private final UserDao userDao = new UserDaoImpl();

    public int createUser(User user, String password) throws ServiceException {
        int affectedRows = 0;
        try {
            affectedRows = userDao.createUser(user, password);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return affectedRows;
    }

    public Optional<User> findByTelephoneNumberPassword(String telephoneNumber, String password) throws ServiceException {
        try {
            Optional<User> user = userDao.findByTelephoneNumberPassword(telephoneNumber, password);
            return user;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    public Optional<User> findUserById(int id) throws ServiceException {
        try {
            Optional<User> user = userDao.findUserById(id);
            return user;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    public Optional<User> findUserByEmail(String email) throws ServiceException {
        try{
            Optional<User> user=userDao.findUserByEmail(email);
            return user;
        }catch (DaoException e){
            logger.error(e);
            throw new ServiceException(e);
        }
    }


        public List<User> findUsers() throws ServiceException {
        List<User> users;
        try {
            users = userDao.findUsers();
            return users;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }

    }

    public int updateUser(User newUser) throws ServiceException {
        int affectedRows = 0;
        try {
            affectedRows = userDao.updateUser(newUser);
            return affectedRows;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }

    }

    public int updateAvatar(int id, String avatar) throws ServiceException {
        int affectedRows = 0;
        try {
            affectedRows = userDao.updateAvatar(id, avatar);
            return affectedRows;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    public int deleteUser(int id) throws ServiceException {
        int affectedRows = 0;
        try {
            affectedRows = userDao.deleteUser(id);
            return affectedRows;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    public String findPasswordById(int id) throws ServiceException {
        String password = "";
        try {
            password = userDao.findPasswordById(id);
            return password;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    public String findUserTelephoneNumber(String telephoneNumber) throws ServiceException {
        String storedTelephoneNumber = "";
        try {
            storedTelephoneNumber = userDao.findUserTelephoneNumber(telephoneNumber);
            return storedTelephoneNumber;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    public String findUserEmail(String email) throws ServiceException {
        String storedEmail = "";
        try {
            storedEmail = userDao.findUserEmail(email);
            return storedEmail;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    public int changePassword(String email, String password) throws ServiceException {
        int affectedRows = 0;
        try {
            affectedRows = userDao.changePassword(email, password);
            return affectedRows;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    public int activateAccount(String email) throws ServiceException {
        int affectedRows = 0;
        try {
            affectedRows = userDao.activateAccount(email);
            return affectedRows;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    public int updateUserRole(int id, UserRole role) throws ServiceException {
        int affectedRows = 0;
        try {
            affectedRows = userDao.updateUserRole(id, role);
            return affectedRows;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    public int updateAccountStatus(int id, AccountStatus status) throws ServiceException {
        int affectedRows = 0;
        try {
            affectedRows = userDao.updateAccountStatus(id, status);
            return affectedRows;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }
}
