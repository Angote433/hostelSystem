package com.ejaisoft.Utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
    public static String hash(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    public static boolean matches(String plainPassword, String hashedPassword) {
        if (hashedPassword == null) {
            return false;
        }
        try {
            return BCrypt.checkpw(plainPassword, hashedPassword);
        } catch (IllegalArgumentException e) {
            // Hash predates the bcrypt migration (plaintext or another format) - never matches.
            return false;
        }
    }
}
