package edu.epam.web.dao.impl;

import edu.epam.web.connection.ConnectionPool;
import edu.epam.web.dao.UserDao;
import edu.epam.web.entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.String.valueOf;

public class UserDaoImpl implements UserDao {

    private final List<User> users = new ArrayList<>();
    private final ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public void createUser(User user) {
        String sqlStatement = "INSERT INTO users (id,telephone_number, password, surname,name,birthday," +
                " user_gender, email,status_point,user_role,avatar,account_status) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";
        Connection connection = pool.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(sqlStatement, PreparedStatement.RETURN_GENERATED_KEYS);
            {
                ps.setInt(1, user.getId());
                ps.setString(2, user.getTelephoneNumber());
                ps.setString(3, user.getPassword());//todo
                ps.setString(4, user.getSurname());
                ps.setString(5, user.getName());
                ps.setDate(6, (java.sql.Date) user.getBirthday());
                ps.setString(7, valueOf(user.getGender()));
                ps.setString(8, user.getEmail());
                ps.setInt(9, user.getStatusPoint());
                ps.setString(10, valueOf(user.getRole()));
                ps.setBytes(11, user.getAvatar());
                ps.setString(12, valueOf(user.getUserStatus()));
                ps.setString(13, valueOf(user.getAccountStatus()));
            }
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("Creation failed. Try again!");
            } else {
                System.out.println("User created");
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.releaseConnection(connection);
        }
    }

    @Override
    public User readUserByIdPassword(String id, String password) {
        User user = null;
        Connection connection = pool.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM users where id=?;");//todo
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            user = convertResultSet(rs);
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.releaseConnection(connection);
        }
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }

    @Override
    public User readUserById(String idx) {
        User user = null;
        Connection connection = pool.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM users where id=?");//todo
            ps.setString(1, idx);
            ResultSet rs = ps.executeQuery();
            user = convertResultSet(rs);
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.releaseConnection(connection);
        }
        return user;
    }

    @Override
    public List<User> readUsers() {
        Connection connection = pool.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM users;");//todo
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = convertResultSet(rs);
                users.add(user);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.releaseConnection(connection);
        }
        return users;
    }

    @Override
    public void updateUser(User newUser) {
        int id = newUser.getId();
        String sqlStatement = "UPDATE users SET telephone_number=?,password=?,surname=?,name=?," +
                "birthday=?,user_gender=?,email=?,status_point=?,user_role=?,avatar=?,user_status=?," +
                "account_status=? WHERE id=?";
        Connection connection = pool.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(sqlStatement);
            {
                ps.setString(1, newUser.getTelephoneNumber());
                ps.setString(2, newUser.getPassword());//todo
                ps.setString(3, newUser.getSurname());
                ps.setString(4, newUser.getName());
                ps.setDate(5, (java.sql.Date) newUser.getBirthday());
                ps.setString(6, valueOf(newUser.getGender()));
                ps.setString(7, newUser.getEmail());
                ps.setInt(8, newUser.getStatusPoint());
                ps.setString(9, valueOf(newUser.getRole()));
                ps.setBytes(10, newUser.getAvatar());
                ps.setString(11, valueOf(newUser.getUserStatus()));
                ps.setString(12, valueOf(newUser.getAccountStatus()));
                ps.setInt(13, id);
            }
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("Update failed. Try again!");
            } else {
                System.out.println("User updated");
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.releaseConnection(connection);
        }
    }
    public void updateAvatar(int userId, byte [] avatar) {
        String sqlStatement = "UPDATE users SET avatar=? WHERE id=?";
        Connection connection = pool.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(sqlStatement);
            {
                ps.setBytes(1, avatar);
                ps.setInt(2, userId);
            }
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("Update failed. Try again!");
            } else {
                System.out.println("Avatar updated");
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.releaseConnection(connection);
        }
    }

    @Override
    public void deleteUser(String idx) {
        Connection connection = pool.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM users where id=?");
            ps.setString(1, idx);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("User with this id not exist");
            } else {
                System.out.println("User deleted");
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.releaseConnection(connection);
        }
    }

    private User convertResultSet(ResultSet resultSet) throws SQLException {
        User user = null;
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String telephone_number = resultSet.getString("telephone_number");
            String password = resultSet.getString("password");
            String surname = resultSet.getString("surname");
            String name = resultSet.getString("name");
            Date birthday = resultSet.getDate("birthday");
            UserGender gender = UserGender.valueOf(resultSet.getString("user_gender"));
            String email = resultSet.getString("email");
            int statusPoint = resultSet.getInt("status_point");
            UserRole role = UserRole.valueOf(resultSet.getString("user_role"));
            byte[] avatar = resultSet.getBytes("avatar");
            UserStatus userStatus = UserStatus.valueOf(resultSet.getString("user_status"));
            AccountStatus accountStatus = AccountStatus.valueOf(resultSet.getString("account_status"));
            user = new User(id, telephone_number, password, surname, name, birthday,
                    gender, email, statusPoint, role, avatar, userStatus, accountStatus);
        }
        return user;
    }
}

