package com.ahmetaltun.securedoc.utils;

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
