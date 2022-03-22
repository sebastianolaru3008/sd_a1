package com.tabs.assignment1.utils;

public class ExceptionUtil {
    public static void printExceptionStacktrace(Exception e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
    }
}
