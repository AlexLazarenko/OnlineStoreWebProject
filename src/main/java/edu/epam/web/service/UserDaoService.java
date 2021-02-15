package edu.epam.web.service;

import edu.epam.web.dao.impl.UserDaoImpl;
import edu.epam.web.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserDaoService {
    private final List<User> users = new ArrayList<>();
private final UserDaoImpl userDaoImpl=new UserDaoImpl();

    public void createUser(User user) {
userDaoImpl.createUser(user);
    }

    public User readUserByIdPassword(String id, String password) {
        return userDaoImpl.readUserByIdPassword(id, password);
    }

    public User readUserById(String id) {
        return userDaoImpl.readUserById(id);
    }

    public List<User> readUsers() {
        return userDaoImpl.readUsers();
    }

    public void updateUser(User newUser) {
userDaoImpl.updateUser(newUser);
    }

    public void deleteUser(String id) {
userDaoImpl.deleteUser(id);
    }
}
