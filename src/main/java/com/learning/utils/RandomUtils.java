package com.learning.utils;

import java.util.Locale;

public final class RandomUtils {

    // Business layer --> All the business level changes

    private RandomUtils(){}

    public static int getId(){
        return FakerUtils.getNumber(100, 1000);
    }

    public static String getFirstName(){
        return FakerUtils.getFirstName().toLowerCase();
    }

    public static String getLastName(){
        return FakerUtils.getLastName().toLowerCase();
    }
}
