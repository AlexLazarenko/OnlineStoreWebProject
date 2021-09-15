package edu.epam.web.validator;

import java.util.Date;

public class UserValidator implements StringValidator {

    private static final String EMAIL_REGEX ="^[-0-9a-zA-Z.+_]+@[-0-9a-zA-Z.+_]+\\.[a-zA-Z]{2,7}";

    private static final String TELEPHONE_REGEX ="\\d{12}";

    private static final int MIN_NAME_LENGTH =2;

    private static final int MAX_NAME_LENGTH =25;


    public boolean isUser(String telephoneNumber, String surname, String name, Date birthday, String email) {
        return isTelephoneNumber(telephoneNumber) && isSurname(surname) && isName(name) &&
                isBirthday(birthday) && isEmail(email);
    }

    private boolean isTelephoneNumber(String telephoneNumber) {
        return isNotEmptyOrNull(telephoneNumber)&&isStringMatches(telephoneNumber,TELEPHONE_REGEX);
    }

    private boolean isSurname(String surname) {
        return isNotEmptyOrNull(surname)&&isStringLengthCorrect(surname,MIN_NAME_LENGTH,MAX_NAME_LENGTH);
    }

    private boolean isName(String name) {
        return isNotEmptyOrNull(name)&&isStringLengthCorrect(name,MIN_NAME_LENGTH,MAX_NAME_LENGTH);
    }

    private boolean isBirthday(Date birthday) {
        return isNotEmptyOrNull(String.valueOf(birthday));
    }

    private boolean isEmail(String email) {
        return isNotEmptyOrNull(email)&&isStringMatches(email,EMAIL_REGEX);
    }

}
