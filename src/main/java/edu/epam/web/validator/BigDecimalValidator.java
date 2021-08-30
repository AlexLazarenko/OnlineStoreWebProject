package edu.epam.web.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BigDecimalValidator {
    public static boolean validate(String price){
        boolean isCorrect=true;
        Pattern pattern=Pattern.compile("");
        Matcher matcher=pattern.matcher(price);
        isCorrect=matcher.matches();
        return isCorrect;
    }
}
