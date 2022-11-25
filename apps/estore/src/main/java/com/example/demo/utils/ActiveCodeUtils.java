package com.example.demo.utils;

import java.util.UUID;

public class ActiveCodeUtils {

    public static String getActiveCode() {

        return UUID.randomUUID().toString();
    }
}
