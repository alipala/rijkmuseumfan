package org.testproject.helper;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class TestReporter {
    private static ExtentReports extent;
    private static ExtentHtmlReporter htmlReporter;
    public static ExtentTest test;

    public static void setUpReport() {
        htmlReporter = new ExtentHtmlReporter("target/extent-reports.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    public static void tearDownReport() {
        extent.flush();
    }

    public static ExtentTest createTest(String testName) {
        test = extent.createTest(testName);
        return test;
    }

    public static ExtentTest getTest() {
        return test;
    }
}
