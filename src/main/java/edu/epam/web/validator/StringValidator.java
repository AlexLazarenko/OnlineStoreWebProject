package edu.epam.web.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface StringValidator {

    default boolean isNotEmptyOrNull(String string) {
        return string != null && !string.isEmpty();
    }

    default boolean isStringMatches(String string, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    default boolean isStringLengthCorrect(String string, int min, int max) {
        return string.length() >= min && string.length() <= max;
    }
}
