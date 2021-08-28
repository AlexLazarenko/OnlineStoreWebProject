package edu.epam.web.dao.impl;

import edu.epam.web.connection.ConnectionPool;
import edu.epam.web.dao.UserDao;
import edu.epam.web.entity.*;
import edu.epam.web.exception.DaoException;
import edu.epam.web.factory.UserFactory;
import edu.epam.web.utility.EncryptPasswordUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static java.lang.String.valueOf;

public class UserDaoImpl implements UserDao { //todo

    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

    private final List<User> users = new ArrayList<>();

    private final ConnectionPool pool = ConnectionPool.getInstance();

    private static final String CREATE_USER="INSERT INTO users (telephone_number, password, surname,name,birthday, user_gender," +
            " email,status_point,user_role,avatar,user_status,account_status) VALUES (?,?,?,?,?,CAST(? AS user_gender),?,?," +
            "CAST(? AS user_role),?,CAST(? AS user_status),CAST(? AS account_status));";

    private static final String FIND_BY_TELEPHONE_NUMBER_PASSWORD="SELECT * FROM users where telephone_number=?;";

    private static final String FIND_USER_BY_ID="SELECT id,telephone_number, surname,name,birthday,user_gender, email," +
            "status_point,user_role,avatar,user_status,account_status FROM users where id=?";

    private static final String FIND_USER_BY_EMAIL="SELECT id,telephone_number, surname,name,birthday,user_gender, email," +
            "status_point,user_role,avatar,user_status,account_status FROM users where email=?";

    private static final String FIND_PASSWORD_BY_ID="SELECT password FROM users where id=?";

    private static final String FIND_USER_TELEPHONE_NUMBER = "SELECT telephone_number FROM users where telephone_number=?";

    private static final  String FIND_USER_EMAIL = "SELECT email FROM users where email=?";

    private static final String FIND_USERS = "SELECT id, telephone_number, surname,name,birthday, " +
            "user_gender, email,status_point,user_role,avatar,user_status,account_status FROM users;";

    private static final String UPDATE_USER = "UPDATE users SET telephone_number=?,surname=?,name=?," +
            "birthday=?,user_gender=CAST(? AS user_gender),email=?,status_point=?,user_role=CAST(? AS user_role)," +
            "avatar=?,user_status=CAST(? AS user_status),account_status=CAST(? AS account_status) WHERE id=?";

    private static final String UPDATE_ACCOUNT_STATUS = "UPDATE users SET account_status=CAST(? AS account_status) WHERE id=?";

    private static final String UPDATE_AVATAR = "UPDATE users SET avatar=? WHERE id=?";

    private static final String UPDATE_USER_ROLE = "UPDATE users SET user_role=CAST(? AS user_role) WHERE id=?";

    private static final String ACTIVATE_ACCOUNT = "UPDATE users SET account_status=CAST(? AS account_status) WHERE email=?";

    private static final String UPDATE_PASSWORD = "UPDATE users SET password=? WHERE email=?";

    private static final String DELETE_USER = "DELETE FROM users where id=?";










    @Override
    public int createUser(User user, String password) throws DaoException {
        int affectedRows = 0;
        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(CREATE_USER, PreparedStatement.RETURN_GENERATED_KEYS);) {
            {
                ps.setString(1, user.getTelephoneNumber());
                ps.setString(2, password);
                ps.setString(3, user.getSurname());
                ps.setString(4, user.getName());
                ps.setDate(5, new java.sql.Date(user.getBirthday().getTime()));
                ps.setString(6, user.getGender().name());
                ps.setString(7, user.getEmail());
                ps.setBigDecimal(8, user.getStatusPoint());
                ps.setString(9, user.getRole().name());
                ps.setString(10, user.getAvatar());
                ps.setString(11, user.getUserStatus().name());
                ps.setString(12, user.getAccountStatus().name());
            }
            affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                logger.error("Creation failed. Try again!");
            } else {
                logger.info("User created");
            }
        } catch (SQLException e) {
            logger.error("Creation failed. Something wrong with database", e);
            throw new DaoException(e);
        }
        return affectedRows;
    }

    @Override
    public Optional<User> findByTelephoneNumberPassword(String telephoneNumber, String password) throws DaoException {
        Optional<User> optionalUser = Optional.empty();
        String storedPassword = null;
        User user=null;
        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_BY_TELEPHONE_NUMBER_PASSWORD);) {
            ps.setString(1, telephoneNumber);
            ResultSet rs = ps.executeQuery();
            rs.next();
            user = convertResultSet(rs);
            storedPassword = rs.getString("password");
            rs.close();
        } catch (SQLException e) {
            logger.error("Failed. Something wrong with database", e);
            throw new DaoException(e);
        }
        if (user != null && EncryptPasswordUtil.checkPassword(storedPassword,password)) {
            optionalUser=Optional.of(user);
        }
        return optionalUser;
    }

    @Override
    public Optional<User> findUserById(int idx) throws DaoException {
        Optional<User> optionalUser = Optional.empty();
        User user = null;
        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_USER_BY_ID);) {
            ps.setInt(1, idx);
            ResultSet rs = ps.executeQuery();
            rs.next();
            user = convertResultSet(rs);
            optionalUser=Optional.of(user);
            rs.close();
        } catch (SQLException e) {
            logger.error("Failed. Something wrong with database", e);
            throw new DaoException(e);
        }
        return optionalUser;
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws DaoException {
        Optional<User> optionalUser = Optional.empty();
        User user = null;
        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_USER_BY_EMAIL);) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            rs.next();
            user = convertResultSet(rs);
            optionalUser=Optional.of(user);
            rs.close();
        } catch (SQLException e) {
            logger.error("Failed. Something wrong with database", e);
            throw new DaoException(e);
        }
        return optionalUser;
    }

    @Override
    public String findPasswordById(int idx) throws DaoException {
        String storedPassword = null;
        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_PASSWORD_BY_ID);
        ) {
            ps.setInt(1, idx);
            ResultSet rs = ps.executeQuery();
            rs.next();
            storedPassword = rs.getString("password");
            rs.close();
        } catch (SQLException e) {
            logger.error("Failed. Something wrong with database", e);
            throw new DaoException(e);
        }
        return storedPassword;
    }

    @Override
    public String findUserTelephoneNumber(String telephoneNumber) throws DaoException {
        String storedTelephoneNumber = null;
        try (
                Connection connection = pool.getConnection();
                PreparedStatement ps = connection.prepareStatement(FIND_USER_TELEPHONE_NUMBER);
        ) {
            ps.setString(1, telephoneNumber);
            ResultSet rs = ps.executeQuery();
            rs.next();
            storedTelephoneNumber = rs.getString("telephone_number");
            rs.close();
        } catch (SQLException e) {
            logger.error("Failed. Something wrong with database", e);
            throw new DaoException(e);
        }
        return storedTelephoneNumber;
    }

    @Override
    public String findUserEmail(String email) throws DaoException {
        String storedEmail = null;
        try (
                Connection connection = pool.getConnection();
                PreparedStatement ps = connection.prepareStatement(FIND_USER_EMAIL);
        ) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            rs.next();
            storedEmail = rs.getString("email");
            rs.close();
        } catch (SQLException e) {
            logger.error("Failed. Something wrong with database", e);
            throw new DaoException(e);
        }
        return storedEmail;
    }

    @Override
    public List<User> findUsers() throws DaoException {
                try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_USERS);
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = convertResultSet(rs);
                users.add(user);
            }
            rs.close();
        } catch (SQLException e) {
            logger.error("Failed. Something wrong with database", e);
            throw new DaoException(e);
        }
        return users;
    }

    @Override
    public int updateUser(User newUser) throws DaoException {
        int affectedRows = 0;
        int id = newUser.getId();
        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_USER);
        ) {
            {
                ps.setString(1, newUser.getTelephoneNumber());
                ps.setString(2, newUser.getSurname());
                ps.setString(3, newUser.getName());
                ps.setDate(4, (java.sql.Date) newUser.getBirthday());
                ps.setString(5, valueOf(newUser.getGender()));
                ps.setString(6, newUser.getEmail());
                ps.setBigDecimal(7, newUser.getStatusPoint());
                ps.setString(8, valueOf(newUser.getRole()));
                ps.setString(9, newUser.getAvatar());
                ps.setString(10, valueOf(newUser.getUserStatus()));
                ps.setString(11, valueOf(newUser.getAccountStatus()));
                ps.setInt(12, id);
            }
            affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                logger.error("Update failed. Try again!");
            } else {
                logger.info("User updated");
            }
        } catch (SQLException e) {
            logger.error("Update failed. Something wrong with database", e);
            throw new DaoException(e);
        }
        return affectedRows;
    }

    @Override
    public int updateAccountStatus(int userId, AccountStatus status) throws DaoException {
        int affectedRows = 0;

        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_ACCOUNT_STATUS);
        ) {
            {
                ps.setString(1, valueOf(status));
                ps.setInt(2, userId);
            }
            affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                logger.error("Account status update failed. Try again!");
            } else {
                logger.info("Account status updated");
            }
        } catch (SQLException e) {
            logger.error("Account status update failed. Something wrong with database", e);
            throw new DaoException(e);
        }
        return affectedRows;
    }

    @Override
    public int updateAvatar(int userId, String avatar) throws DaoException {
        int affectedRows = 0;
        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_AVATAR);
        ) {
            {
                ps.setString(1, avatar);
                ps.setInt(2, userId);
            }
            affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                logger.error("Avatar update failed. Try again!");
            } else {
                logger.info("Avatar updated");
            }
        } catch (SQLException e) {
            logger.error("Avatar update failed. Something wrong with database", e);
            throw new DaoException(e);
        }
        return affectedRows;
    }

    @Override
    public int updateUserRole(int userId, UserRole role) throws DaoException {
        int affectedRows = 0;
        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_USER_ROLE);
        ) {
            {
                ps.setString(1, valueOf(role));
                ps.setInt(2, userId);
            }
            affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                logger.error("Role update failed. Try again!");
            } else {
                logger.info("Role updated");
            }
        } catch (SQLException e) {
            logger.error("Role update failed. Something wrong with database", e);
            throw new DaoException(e);
        }
        return affectedRows;
    }

    @Override
    public int activateAccount(String email) throws DaoException {
        int affectedRows = 0;
        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(ACTIVATE_ACCOUNT);
        ) {
            {
                ps.setString(1, "ACTIVE");
                ps.setString(2, email);
            }
            affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                logger.error("Account status update failed. Try again!");
            } else {
                logger.info("Account status updated");
            }
        } catch (SQLException e) {
            logger.error("Account status update failed. Something wrong with database", e);
            throw new DaoException(e);
        }
        return affectedRows;
    }

    @Override
    public int changePassword(String email, String password) throws DaoException {
        int affectedRows = 0;
        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_PASSWORD);
        ) {
            {
                ps.setString(1, password);
                ps.setString(2, email);
            }
            affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                logger.error("Password update failed. Try again!");
            } else {
                logger.info("Password updated");
            }
        } catch (SQLException e) {
            logger.error("Password update failed. Something wrong with database", e);
            throw new DaoException(e);
        }
        return affectedRows;
    }

    @Override
    public int deleteUser(int idx) throws DaoException {
        int affectedRows = 0;
        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE_USER);
        ) {
            ps.setInt(1, idx);
            affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                logger.info("User with this id not exist");
            } else {
                logger.info("User deleted");
            }
        } catch (SQLException e) {
            logger.error("Failed. Something wrong with database", e);
            throw new DaoException(e);
        }
        return affectedRows;
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
        BigDecimal statusPoint = resultSet.getBigDecimal("status_point");
        UserRole role = UserRole.valueOf(resultSet.getString("user_role"));
        String avatar = resultSet.getString("avatar");
        UserStatus userStatus = UserStatus.valueOf(resultSet.getString("user_status"));
        AccountStatus accountStatus = AccountStatus.valueOf(resultSet.getString("account_status"));
        user = new User(id, telephone_number, surname, name, birthday,
                gender, email, statusPoint, role, avatar, userStatus, accountStatus);
        return user;
    }
}

