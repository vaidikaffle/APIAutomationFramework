package com.learning.reports;

import com.aventstack.extentreports.ExtentTest;

/**
 * The ExtentManager class is a utility class for managing the ExtentTest instances using ThreadLocal.
 * It provides methods to get, set, and remove the ExtentTest instance associated with the current thread.
 */
public final class ExtentManager {


    //Private constructor to prevent instantiation of the ExtentManager class.
    private ExtentManager() {
    }


    /**
     * ThreadLocal variable to store the ExtentTest instance associated with the current thread.
     */
    private static final ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();


    /**
     * @return The ExtentTest instance associated with the current thread.
     */
    public static ExtentTest getExtentTest() {
        return extentTestThreadLocal.get();
    }


    /**
     * Sets the ExtentTest instance for the current thread.
     *
     * @param extentTest The ExtentTest instance to be set for the current thread.
     */
    public static void setExtentTest(ExtentTest extentTest) {
        extentTestThreadLocal.set(extentTest);
    }


    /**
     * Removes the ExtentTest instance associated with the current thread.
     */
    public static void unLoad() {
        extentTestThreadLocal.remove();
    }

}
