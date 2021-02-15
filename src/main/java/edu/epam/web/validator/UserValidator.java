package edu.epam.web.validator;

import edu.epam.web.entity.UserGender;
import edu.epam.web.exception.ValidatorException;

import java.util.Date;

public class UserValidator {
    public boolean isUser(int id, String telephoneNumber, String password, String surname, String name, Date birthday, UserGender gender, String email) throws ValidatorException {
        return isID(id) &&  isTelephoneNumber(telephoneNumber) && isPassword(password) && isSurname(surname) && isName(name) &&
                isBirthday(birthday) && isGender(gender) && isEmail(email);
    }

    private boolean isID(int id) {
        return true;
    }

    private boolean isTelephoneNumber(String telephoneNumber) {
        return true;
    }

    private boolean isPassword(String password) {
        return true;
    }

    private boolean isSurname(String surname) {
        return true;
    }

    private boolean isName(String name) {
        return true;
    }

    private boolean isBirthday(Date birthday) {
        return true;
    }

    private boolean isGender(UserGender gender) {
        return true;
    }

    private boolean isEmail(String email) {
        return true;
    }
}
