package edu.epam.web.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BigDecimalValidator {

    private static final String PRICE_REGEX ="\\d{1,4}\\.\\d{2}|\\d{1,4}";

    public static boolean validate(String price){
        boolean isCorrect;
        Pattern pattern=Pattern.compile(PRICE_REGEX);
        Matcher matcher=pattern.matcher(price);
        isCorrect=matcher.matches();
        return isCorrect;
    }
}
