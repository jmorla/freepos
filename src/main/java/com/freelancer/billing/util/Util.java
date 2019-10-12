package com.freelancer.billing.util;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Util {

    public static String encodePassword(String encode) {
        return new BCryptPasswordEncoder().encode(encode);
    }

    public static void main(String[] args) {
        System.out.println(Util.encodePassword("admin"));
    }
}
