package edu.epam.web.service;

import edu.epam.web.dao.impl.UserDaoImpl;
import edu.epam.web.entity.AccountStatus;
import edu.epam.web.entity.User;
import edu.epam.web.entity.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class UserDaoService {
    private static final Logger logger = LogManager.getLogger(UserDaoService.class);
    private final List<User> users = new ArrayList<>();
    private final UserDaoImpl userDaoImpl = new UserDaoImpl();

    public int createUser(User user, String password) {
        return userDaoImpl.createUser(user, password);
    }

    public User findByTelephoneNumberPassword(String telephoneNumber, String password) {
        return userDaoImpl.findByTelephoneNumberPassword(telephoneNumber, password);
    }

    public User findUserById(int id) {
        return userDaoImpl.findUserById(id);
    }

    public List<User> findUsers() {
        return userDaoImpl.findUsers();
    }

    public int updateUser(User newUser) {
        return userDaoImpl.updateUser(newUser);
    }

    public int updateAvatar(int id, byte[] avatar) {
        return
                userDaoImpl.updateAvatar(id, avatar);
    }

    public void deleteUser(int id) {
        userDaoImpl.deleteUser(id);
    }

    public String findPasswordById(int id) {
        return userDaoImpl.findPasswordById(id);
    }

    public String findUserTelephoneNumber(String telephoneNumber) {
        return userDaoImpl.findUserTelephoneNumber(telephoneNumber);
    }

    public String findUserEmail(String email) {
        return userDaoImpl.findUserEmail(email);
    }

    public int changePassword(String email, String password) {
        return userDaoImpl.changePassword(email, password);
    }

    public int activateAccount(String email) {
        return userDaoImpl.activateAccount(email);
    }

    public int updateUserRole(int id, UserRole role) {
        return
                userDaoImpl.updateUserRole(id, role);
    }

    public int updateAccountStatus(int id, AccountStatus status) {
        return
                userDaoImpl.updateAccountStatus(id, status);
    }
}
