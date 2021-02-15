package edu.epam.web.dao;

import edu.epam.web.entity.User;

import java.util.List;

public interface UserDao {
    void createUser(User user);

    User readUserByIdPassword(String id, String password);

    User readUserById(String id);

    List<User> readUsers();

    void updateUser(User newUser);

    void deleteUser(String id);
}
