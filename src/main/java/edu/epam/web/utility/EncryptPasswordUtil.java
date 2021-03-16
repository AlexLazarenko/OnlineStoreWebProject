package edu.epam.web.utility;


import org.apache.commons.codec.digest.DigestUtils;


public class EncryptPasswordUtil {

    private EncryptPasswordUtil() {
    }

    public static String encrypt(String password) {
        String encryptPassword = DigestUtils.md5Hex(password);
        return encryptPassword;
    }

    public static boolean checkPassword(String password, String verifyPassword) {
        boolean checkPassword = false;
        if (password == null || verifyPassword == null) {
            checkPassword = false;
        }
        if (password.equals(verifyPassword)) {
            checkPassword = true;
        }
        return checkPassword;
    }
}
