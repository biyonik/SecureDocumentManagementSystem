package com.ahmetaltun.securedoc.utils;

/**
 * @author Ahmet Altun
 * @version 1.0
 * @email ahmet.altun60@gmail.com
 * @since 26/11/2024
 */

public class EmailUtils {
    public static String getEmailMessage(String name, String host, String token) {
        return "Hello %s,\n\nYour account has been created. Please click on the link below to verify account. \n\n%s\n\nThe Support Team."
                .formatted(name, getVerificationUrl(host, token));
    }

    private static String getVerificationUrl(String host, String token) {
        return host+"/verify/account?token="+token;
    }

    public static String getResetPasswordMessage(String name, String host, String token) {
        return "Hello %s,\n\nYour password reset request reached to us. Please click on the link below to reset your password. \n\n%s\n\nThe Support Team."
                .formatted(name, getResetPasswordUrl(host, token));
    }

    private static String getResetPasswordUrl(String host, String token) {
        return host+"/verify/password?token="+token;
    }

}
