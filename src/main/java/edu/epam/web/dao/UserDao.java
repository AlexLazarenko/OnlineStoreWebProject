package edu.epam.web.dao;

import edu.epam.web.entity.AccountStatus;
import edu.epam.web.entity.User;
import edu.epam.web.entity.UserRole;

import java.util.List;

public interface UserDao {

    int createUser(User user,String password);

    User findByTelephoneNumberPassword(String telephoneNumber, String password);

    User findUserById(int idx);

    String findPasswordById(int idx);

    String findUserTelephoneNumber(String telephoneNumber);

    String findUserEmail(String email);

    List<User> findUsers();

    int updateUser(User newUser);

    int updateAccountStatus(int userId,AccountStatus status);

    int updateAvatar(int userId, byte[] avatar);

    int updateUserRole(int userId,UserRole role);

    int activateAccount(String email);

    int changePassword(String email, String password);

    void deleteUser(int idx);

}
