package com.andree.antar_be.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Password {
    static public String createPassword(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    static public boolean ComparePassword(String password, String hash){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(password, hash);
    }
}
