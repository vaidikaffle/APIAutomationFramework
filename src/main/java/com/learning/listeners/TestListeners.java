package com.learning.listeners;
import com.learning.annotation.FrameWorkAnnotation;
import com.learning.enums.LogType;
import com.learning.reports.ExtentLogger;
import com.learning.reports.ExtentReport;
import com.learning.reports.FrameWorkLogger;
import com.learning.utils.JiraUtils;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListeners implements ITestListener, ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        ExtentReport.initReports();
    }

    @Override
    public void onFinish(ISuite suite) {
        ExtentReport.tearDownReport();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentReport.createTest(result.getName());
        ExtentReport.addAuthor(result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameWorkAnnotation.class).author());
        ExtentReport.addCategory(result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameWorkAnnotation.class).category());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        FrameWorkLogger.log(LogType.PASS, result.getName() + " is passed successfully");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        FrameWorkLogger.log(LogType.FAIL, result.getName() + " is Failed");
        ExtentLogger.fail(result.getThrowable().toString());

        String issueInJira = JiraUtils.createIssueInJira(result.getThrowable().toString());
        ExtentLogger.fail("Issue created in Jira" + "http://localhost:8080/browse/" + issueInJira);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        FrameWorkLogger.log(LogType.SKIP, result.getName() + " is skipped");
    }

}
