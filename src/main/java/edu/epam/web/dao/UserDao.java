package edu.epam.web.dao;

import edu.epam.web.entity.AccountStatus;
import edu.epam.web.entity.User;
import edu.epam.web.entity.UserRole;
import edu.epam.web.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserDao extends BaseDao {

    int createUser(User user, String password) throws DaoException;

    Optional<User> findByTelephoneNumberPassword(String telephoneNumber, String password) throws DaoException;

    Optional<User> findUserById(int idx) throws DaoException;

    Optional<User> findUserByEmail(String email) throws DaoException;

    String findPasswordById(int idx) throws DaoException;

    String findUserTelephoneNumber(String telephoneNumber) throws DaoException;

    String findUserEmail(String email) throws DaoException;

    List<User> findUsers() throws DaoException;

    int updateUser(User newUser) throws DaoException;

    int updateAccountStatus(int userId, AccountStatus status) throws DaoException;

    int updateAvatar(int userId, String avatar) throws DaoException;

    int updateUserRole(int userId, UserRole role) throws DaoException;

    int activateAccount(String email) throws DaoException;

    int changePassword(String email, String password) throws DaoException;

    int deleteUser(int idx) throws DaoException;

}
