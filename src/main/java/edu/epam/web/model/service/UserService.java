package edu.epam.web.model.service;

import edu.epam.web.exception.ServiceException;
import edu.epam.web.model.entity.AccountStatus;
import edu.epam.web.model.entity.User;
import edu.epam.web.model.entity.UserRole;

import java.util.List;
import java.util.Optional;

public interface UserService {

    int createUser(User user, String password) throws ServiceException;


    Optional<User> findByTelephoneNumberPassword(String telephoneNumber, String password) throws ServiceException;


    Optional<User> findUserById(int id) throws ServiceException;


    Optional<User> findUserByEmail(String email) throws ServiceException;


    List<User> findUsers() throws ServiceException;


    int updateUser(User newUser) throws ServiceException;


    int updateAvatar(int id, String avatar) throws ServiceException;


    int deleteUser(int id) throws ServiceException;


    String findPasswordById(int id) throws ServiceException;


    String findUserTelephoneNumber(String telephoneNumber) throws ServiceException;


    String findUserEmail(String email) throws ServiceException;


    int changePassword(String email, String password) throws ServiceException;


    int activateAccount(String email) throws ServiceException;


    int updateUserRole(int id, UserRole role) throws ServiceException;


    int updateAccountStatus(int id, AccountStatus status) throws ServiceException;

}
