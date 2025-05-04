package com.learning.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import com.learning.constants.FrameworkConstants;
import com.learning.enums.PropertiesType;
import com.learning.utils.PropertyUtils;


import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * The ExtentReport class provides methods for initializing, tearing down, and creating ExtentReports.
 */
public class ExtentReport {
    private static ExtentReports extentReports;

    /**
     * Initializes the ExtentReports instance if it is null.
     * Creates ExtentSparkReporter for the main report and failed test report.
     * Sets the theme, report name, and document title for both reports.
     * Attaches the reporters to the extentReports instance.
     * Sets system information for the reports.
     */
    public static void initReports() {
        if (Objects.isNull(ExtentManager.getExtentTest())){
            extentReports = new ExtentReports();
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(FrameworkConstants.getEXTENTREPORTPATH())
                    .viewConfigurer().viewOrder().as(new ViewName[] {ViewName.DASHBOARD, ViewName.TEST, ViewName.AUTHOR, ViewName.CATEGORY}).apply();
            sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setReportName("Automation Result");
            sparkReporter.config().setDocumentTitle("Rest API Automation");

            String failedTestPath = System.getProperty("user.dir")+"//reports//failed-index.html";
            ExtentSparkReporter failedSpark = new ExtentSparkReporter(failedTestPath).filter().statusFilter().as(new Status[]{Status.FAIL}).apply();
            failedSpark.config().setTheme(Theme.DARK);
            failedSpark.config().setReportName("Automation Result");
            failedSpark.config().setDocumentTitle("Rest API Automation");

            extentReports.attachReporter(sparkReporter, failedSpark);
            extentReports.setSystemInfo("Automation Tester", PropertyUtils.getPropertyValue("testCaseAuthor"));
        }
    }

    /**
     * Flushes the extentReports instance and unloads the ExtentManager.
     * Opens the main report in the default browser.
     */
    public static void tearDownReport() {
        if (Objects.nonNull(ExtentManager.getExtentTest())){
            extentReports.flush();
            ExtentManager.unLoad();
//            try {
//                Desktop.getDesktop().browse(new File(System.getProperty("user.dir")+"//extent-test-output//index.html").toURI());
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
        }
    }

    /**
     * Creates a test with the given test case name.
     * Assigns author and category to the test based on the property values.
     *
     * @param testCaseName The name of the test case.
     * @return
     */
    public static void createTest(String testCaseName) {
        ExtentManager.setExtentTest(extentReports.createTest(testCaseName));
    }


    /**
     * Adds authors to the current Extent Report test.
     * @param authors An array of authors to be added.
     */
    public static void addAuthor(String[] authors){
        for(String temp:authors){
            ExtentManager.getExtentTest().assignAuthor(temp);
        }
    }

    /**
     * Adds categories to the current Extent Report test.
     * @param categories An array of categories to be added.
     */
    public static void addCategory(String[] categories){
        for(String temp:categories){
            ExtentManager.getExtentTest().assignCategory(temp);
        }
    }
}