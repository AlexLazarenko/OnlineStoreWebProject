package edu.epam.web.dao;

import edu.epam.web.entity.AccountStatus;
import edu.epam.web.entity.User;
import edu.epam.web.entity.UserRole;

import java.util.List;

public interface UserDao {

    int createUser(User user);

    User findByTelephoneNumberPassword(String telephoneNumber, String password);

    User findUserById(int idx);

    String findUserTelephoneNumber(String telephoneNumber);

    String findUserEmail(String email);

    List<User> findUsers();

    int updateUser(User newUser);

    int updateAvatar(int userId, byte[] avatar);

    int changeAccountStatus(int userId, AccountStatus status);

    int changePassword(int userId, String password);

    void deleteUser(int idx);
}
