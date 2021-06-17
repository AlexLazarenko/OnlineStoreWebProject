package edu.epam.web.dao.impl;

import edu.epam.web.connection.ConnectionPool;
import edu.epam.web.dao.UserDao;
import edu.epam.web.entity.*;
import edu.epam.web.factory.UserFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static java.lang.String.valueOf;

public class UserDaoImpl implements UserDao { //todo
    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);
    private final List<User> users = new ArrayList<>();
    private final ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public int createUser(User user, String password) {
        String sqlStatement = "INSERT INTO users (telephone_number, password, surname,name,birthday, user_gender, email,status_point,user_role,avatar,user_status,account_status) " +
                "VALUES (?,?,?,?,?,CAST(? AS user_gender),?,?,CAST(? AS user_role),?,CAST(? AS user_status),CAST(? AS account_status));";
        Connection connection = pool.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(sqlStatement, PreparedStatement.RETURN_GENERATED_KEYS);
            {
                ps.setString(1, user.getTelephoneNumber());
                ps.setString(2, password);
                ps.setString(3, user.getSurname());
                ps.setString(4, user.getName());
                ps.setDate(5, new java.sql.Date(user.getBirthday().getTime()));
                ps.setString(6, user.getGender().name());
                ps.setString(7, user.getEmail());
                ps.setInt(8, user.getStatusPoint());
                ps.setString(9, user.getRole().name());
                ps.setBytes(10, user.getAvatar());
                ps.setString(11, user.getUserStatus().name());
                ps.setString(12, user.getAccountStatus().name());
            }
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                logger.error("Creation failed. Try again!");
            } else {
                logger.info("User created");
            }
            ps.close();
            return affectedRows;
        } catch (SQLException e) {
            logger.error("Creation failed. Something wrong with database", e);
        } finally {
            pool.releaseConnection(connection);
        }
        return 0;
    }

    @Override
    public User findByTelephoneNumberPassword(String telephoneNumber, String password) {
        User user = null;
        String storedPassword = null;
        Connection connection = pool.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM users where telephone_number=?;");//todo
            ps.setString(1, telephoneNumber);
            ResultSet rs = ps.executeQuery();
            rs.next();
            user = convertResultSet(rs);
            storedPassword = rs.getString("password");
            rs.close();
            ps.close();
        } catch (SQLException e) {
            logger.error("Failed. Something wrong with database", e);
        } finally {
            pool.releaseConnection(connection);
        }
        if (user != null && Objects.equals(storedPassword, password)) {
            return user;
        } else {
            return null;
        }
    }

    @Override
    public User findUserById(int idx) {
        User user = null;
        Connection connection = pool.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT id,telephone_number, surname,name,birthday, " +
                    "user_gender, email,status_point,user_role,avatar,user_status,account_status FROM users where id=?");
            ps.setInt(1, idx);
            ResultSet rs = ps.executeQuery();
            rs.next();
            user = convertResultSet(rs);
            rs.close();
            ps.close();
        } catch (SQLException e) {
            logger.error("Failed. Something wrong with database", e);
        } finally {
            pool.releaseConnection(connection);
        }
        return user;
    }

    @Override
    public String findPasswordById(int idx) {
        String storedPassword = null;
        Connection connection = pool.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT password FROM users where id=?");
            ps.setInt(1, idx);
            ResultSet rs = ps.executeQuery();
            rs.next();
            storedPassword = rs.getString("password");
            rs.close();
            ps.close();
        } catch (SQLException e) {
            logger.error("Failed. Something wrong with database", e);
        } finally {
            pool.releaseConnection(connection);
        }
        return storedPassword;
    }

    @Override
    public String findUserTelephoneNumber(String telephoneNumber) {
        String storedTelephoneNumber = null;
        Connection connection = pool.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT telephone_number FROM users where telephone_number=?");
            ps.setString(1, telephoneNumber);
            ResultSet rs = ps.executeQuery();
            rs.next();
            storedTelephoneNumber = rs.getString("telephone_number");
            rs.close();
            ps.close();
        } catch (SQLException e) {
            logger.error("Failed. Something wrong with database", e);
        } finally {
            pool.releaseConnection(connection);
        }
        return storedTelephoneNumber;
    }

    @Override
    public String findUserEmail(String email) {
        String storedEmail = null;
        Connection connection = pool.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT email FROM users where email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            rs.next();
            storedEmail = rs.getString("email");
            rs.close();
            ps.close();
        } catch (SQLException e) {
            logger.error("Failed. Something wrong with database", e);
        } finally {
            pool.releaseConnection(connection);
        }
        return storedEmail;
    }

    @Override
    public List<User> findUsers() {
        Connection connection = pool.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT id, telephone_number, surname,name,birthday, " +
                    "user_gender, email,status_point,user_role,avatar,user_status,account_status FROM users;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = convertResultSet(rs);
                users.add(user);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            logger.error("Failed. Something wrong with database", e);
        } finally {
            pool.releaseConnection(connection);
        }
        return users;
    }

    @Override
    public int updateUser(User newUser) {
        int id = newUser.getId();
        String sqlStatement = "UPDATE users SET telephone_number=?,surname=?,name=?," +
                "birthday=?,user_gender=CAST(? AS user_gender),email=?,status_point=?,user_role=CAST(? AS user_role)," +
                "avatar=?,user_status=CAST(? AS user_status),account_status=CAST(? AS account_status) WHERE id=?";
        Connection connection = pool.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(sqlStatement);
            {
                ps.setString(1, newUser.getTelephoneNumber());
                ps.setString(2, newUser.getSurname());
                ps.setString(3, newUser.getName());
                ps.setDate(4, (java.sql.Date) newUser.getBirthday());
                ps.setString(5, valueOf(newUser.getGender()));
                ps.setString(6, newUser.getEmail());
                ps.setInt(7, newUser.getStatusPoint());
                ps.setString(8, valueOf(newUser.getRole()));
                ps.setBytes(9, newUser.getAvatar());
                ps.setString(10, valueOf(newUser.getUserStatus()));
                ps.setString(11, valueOf(newUser.getAccountStatus()));
                ps.setInt(12, id);
            }
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                logger.error("Update failed. Try again!");
            } else {
                logger.info("User updated");
            }
            ps.close();
            return affectedRows;
        } catch (SQLException e) {
            logger.error("Update failed. Something wrong with database", e);
        } finally {
            pool.releaseConnection(connection);
        }
        return 0;
    }

    @Override
    public int updateAccountStatus(int userId, AccountStatus status) {
        String sqlStatement = "UPDATE users SET account_status=CAST(? AS account_status) WHERE id=?";
        Connection connection = pool.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(sqlStatement);
            {
                ps.setString(1, valueOf(status));
                ps.setInt(2, userId);
            }
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                logger.error("Account status update failed. Try again!");
            } else {
                logger.info("Account status updated");
            }
            ps.close();
            return affectedRows;
        } catch (SQLException e) {
            logger.error("Account status update failed. Something wrong with database", e);
        } finally {
            pool.releaseConnection(connection);
        }
        return 0;
    }

    @Override
    public int updateAvatar(int userId, byte[] avatar) {
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
                logger.error("Avatar update failed. Try again!");
            } else {
                logger.info("Avatar updated");
            }
            ps.close();
            return affectedRows;
        } catch (SQLException e) {
            logger.error("Avatar update failed. Something wrong with database", e);
        } finally {
            pool.releaseConnection(connection);
        }
        return 0;
    }

    @Override
    public int updateUserRole(int userId, UserRole role) {
        String sqlStatement = "UPDATE users SET user_role=CAST(? AS user_role) WHERE id=?";
        Connection connection = pool.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(sqlStatement);
            {
                ps.setString(1, valueOf(role));
                ps.setInt(2, userId);
            }
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                logger.error("Role update failed. Try again!");
            } else {
                logger.info("Role updated");
            }
            ps.close();
            return affectedRows;
        } catch (SQLException e) {
            logger.error("Role update failed. Something wrong with database", e);
        } finally {
            pool.releaseConnection(connection);
        }
        return 0;
    }

    @Override
    public int activateAccount(String email) {
        String sqlStatement = "UPDATE users SET account_status=CAST(? AS account_status) WHERE email=?";
        Connection connection = pool.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(sqlStatement);
            {
                ps.setString(1, "ACTIVE");
                ps.setString(2, email);
            }
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                logger.error("Account status update failed. Try again!");
            } else {
                logger.info("Account status updated");
            }
            ps.close();
            return affectedRows;
        } catch (SQLException e) {
            logger.error("Account status update failed. Something wrong with database", e);
        } finally {
            pool.releaseConnection(connection);
        }
        return 0;
    }

    @Override
    public int changePassword(String email, String password) {
        String sqlStatement = "UPDATE users SET password=? WHERE email=?";
        Connection connection = pool.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(sqlStatement);
            {
                ps.setString(1, password);
                ps.setString(2, email);
            }
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                logger.error("Password update failed. Try again!");
            } else {
                logger.info("Password updated");
            }
            ps.close();
            return affectedRows;
        } catch (SQLException e) {
            logger.error("Password update failed. Something wrong with database", e);
        } finally {
            pool.releaseConnection(connection);
        }
        return 0;
    }

    @Override
    public void deleteUser(int idx) {
        Connection connection = pool.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM users where id=?");
            ps.setInt(1, idx);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                logger.info("User with this id not exist");
            } else {
                logger.info("User deleted");
            }
            ps.close();
        } catch (SQLException e) {
            logger.error("Failed. Something wrong with database", e);
        } finally {
            pool.releaseConnection(connection);
        }
    }

    private User convertResultSet(ResultSet resultSet) throws SQLException {
        User user = null;
        int id = resultSet.getInt("id");
        String telephone_number = resultSet.getString("telephone_number");
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
        user = new User(id, telephone_number, surname, name, birthday,
                gender, email, statusPoint, role, avatar, userStatus, accountStatus);
        return user;
    }
}

