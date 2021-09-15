package edu.epam.web.utility;

import java.util.UUID;

public class RandomStringGenerator {
    public static String generate(){
        final String uuid = UUID.randomUUID().toString().replace("-", "").substring(0,8);
        return uuid;
    }
}
