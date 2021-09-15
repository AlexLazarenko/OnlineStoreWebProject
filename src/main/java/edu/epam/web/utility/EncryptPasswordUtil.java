package edu.epam.web.utility;


import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;


public class EncryptPasswordUtil {
    private static final Logger logger = LogManager.getLogger(EncryptPasswordUtil.class);


    private EncryptPasswordUtil() {
    }

    public static String encrypt(String password) {
        String encryptPassword = DigestUtils.md5Hex(password);
        return encryptPassword;
    }

    public static Map<String, String> checkPasswords(String storedPassword, String password, String newPassword, String verifyPassword) {
        Map<String, String> messages = new HashMap<String, String>();
        if (!EncryptPasswordUtil.checkPassword(storedPassword, password)) {
            logger.warn("Checking password fail");
            messages.put("fail", "It is not your password!");
        }
        if (EncryptPasswordUtil.checkPassword(password, newPassword)) {
            logger.warn("Checking password fail. You input same passwords.");
            messages.put("new_password", "Checking fail. You input same passwords.");
            messages.put("password", "Checking fail. You input same passwords.");
        }
        if (!EncryptPasswordUtil.checkPassword(newPassword, verifyPassword)) {
            logger.warn("Verifying password fail. Input same passwords, please");
            messages.put("new_password", "Verifying fail. Input same passwords, please");
        }
        return messages;
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
