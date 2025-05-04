package com.learning.reports;

import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.restassured.http.Header;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;

/**
 * The ExtentLogger class provides static methods for logging different types of messages using ExtentManager.
 * It is a utility class and cannot be instantiated.
 */
public final class ExtentLogger {


    //Private constructor to prevent instantiation of the ExtentLogger class.
    private ExtentLogger() {

    }

    /**
     * Logs a pass message using ExtentManager.
     */
    public static void pass(String passMessage) {
        ExtentManager.getExtentTest().pass(passMessage);
    }

    /**
     * Logs a fail message using ExtentManager.
     */
    public static void fail(String failMessage) {
        ExtentManager.getExtentTest().fail(failMessage);
    }

    /**
     * Logs a skip message using ExtentManager.
     */
    public static void skip(String skipMessage) {
        ExtentManager.getExtentTest().skip(skipMessage);
    }

    /**
     * Logs an info message using ExtentManager.
     */
    public static void info(String infoMessage) {
        ExtentManager.getExtentTest().info(infoMessage);
    }

    public static void logResponse(String message){
        ExtentManager.getExtentTest().pass(MarkupHelper.createCodeBlock(message, CodeLanguage.JSON));
    }


    public static void logRequest(RequestSpecification requestSpecification){
        QueryableRequestSpecification query = SpecificationQuerier.query(requestSpecification);
        info("Request Details Below");
        ExtentManager.getExtentTest().info(MarkupHelper.createCodeBlock(query.getBody(), CodeLanguage.JSON));
        for (Header header: query.getHeaders()){
            info(header.getName() + ": "+ header.getValue());
        }
    }

}
