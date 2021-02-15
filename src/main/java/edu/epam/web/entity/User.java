package edu.epam.web.entity;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class User  {//todo
    private int id;
    private String telephoneNumber;
    private String password;
    private String surname;
    private String name;
    private Date birthday;
    private UserGender gender;
    private String email;
    private int statusPoint;
    private UserRole role;
    private byte[] avatar;
    private UserStatus userStatus;
    private AccountStatus accountStatus;

    public User(int id, String telephoneNumber, String password, String surname,
                String name, Date birthday, UserGender gender, String email, int statusPoint,
                UserRole role, byte[] avatar, UserStatus userStatus, AccountStatus accountStatus) {
        this.id = id;
        this.telephoneNumber = telephoneNumber;
        this.password = password;
        this.surname = surname;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.email = email;
        this.statusPoint = statusPoint;
        this.role = role;
        this.avatar = avatar;
        this.userStatus = userStatus;
        this.accountStatus = accountStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public UserGender getGender() {
        return gender;
    }

    public void setGender(UserGender gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatusPoint() {
        return statusPoint;
    }

    public void setStatusPoint(int statusPoint) {
        this.statusPoint = statusPoint;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                statusPoint == user.statusPoint &&
                telephoneNumber.equals(user.telephoneNumber) &&
                password.equals(user.password) &&
                surname.equals(user.surname) &&
                name.equals(user.name) &&
                birthday.equals(user.birthday) &&
                gender == user.gender &&
                email.equals(user.email) &&
                role == user.role &&
                Arrays.equals(avatar, user.avatar) &&
                userStatus == user.userStatus &&
                accountStatus == user.accountStatus;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, telephoneNumber, password, surname, name, birthday, gender, email, statusPoint, role, userStatus, accountStatus);
        result = 31 * result + Arrays.hashCode(avatar);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", statusPoint=" + statusPoint +
                ", role=" + role +
                ", avatar=" + Arrays.toString(avatar) +
                ", userStatus=" + userStatus +
                ", accountStatus=" + accountStatus +
                '}';
    }
}
