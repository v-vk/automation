package com.auto.listeners;

import com.auto.tests.BaseClass;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.auto.logs.Log;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class TestListener implements ITestListener {

	ExtentSparkReporter htmlreport;
	ExtentReports report;
	ExtentTest test;

	public void configureReport() {
		htmlreport = new ExtentSparkReporter("Reports/Report" + uniqueNameGenerator() + ".html");
		report = new ExtentReports();
		report.attachReporter(htmlreport);

		// congig
		htmlreport.config().setTheme(Theme.STANDARD);
		htmlreport.config().setDocumentTitle("Test report");
		htmlreport.config().setReportName("Reports");

	}

	public String uniqueNameGenerator() {
		return new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	}

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	@Override
	public void onStart(ITestContext iTestContext) {
		configureReport();
		Log.info("I am in onStart method " + iTestContext.getName());

	}

	@Override
	public void onFinish(ITestContext iTestContext) {
	
		Log.info("I am in onFinish method " + iTestContext.getName());
		report.flush();
	}

	@Override
	public void onTestStart(ITestResult iTestResult) {
		Log.info(getTestMethodName(iTestResult) + " test is starting.");
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		Log.info(getTestMethodName(iTestResult) + " test is Passed.");
		test = report.createTest(getTestMethodName(iTestResult));
		test.log(Status.PASS, getTestMethodName(iTestResult) + " is passed");

	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		Log.info(getTestMethodName(iTestResult) + " test is failed.");
		test = report.createTest(getTestMethodName(iTestResult));
//		test.log(Status.FAIL, "Test Failed");
		// Get driver from BaseTest and assign to local webdriver variable.
		
		Object testClass = iTestResult.getInstance();
		WebDriver driver = ((BaseClass) testClass).getDriver();
		
		// Take base64Screenshot screenshot for extent reports String
		String base64Screenshot = "data:image/png;base64,"
				+ ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);
		
		
		
		test.log(Status.FAIL, getTestMethodName(iTestResult)+"Test is Failed",
				test.addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));

	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		Log.info(getTestMethodName(iTestResult) + " test is skipped.");

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		Log.info("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
	}
}
