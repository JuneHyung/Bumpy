package com.bump.bumpy.util.funtion;

public class UserIdValidator {
    public static Boolean UserIdIsValid(String userId) {
        // check 5~20 length
        if (userId.length() < 5 || userId.length() > 20) {
            return false;
        }
        // only alphabet and number & not start from number
        if (!userId.matches("^[a-zA-Z][a-zA-Z0-9]*$")) {
            return false;
        }
        // not null
        if (userId == null) {
            return false;
        }
        return true;
    }
}
