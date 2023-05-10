package com.auto.tests;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Login {

	public static WebDriver driver;
	public WebDriverWait wait;
	
	ExtentSparkReporter htmlreporter = new ExtentSparkReporter("Reports/Report"+uniqueNameGenerator()+".html");
	ExtentReports rep = new ExtentReports();
	
	public String uniqueNameGenerator() {
		return new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	}
	
	@BeforeTest
	public void setUp() {
		String path = System.getProperty("user.dir");
		System.out.println(path);
		System.setProperty("webdriver.chrome.driver", path+"/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		
		
	}
	
	@Test(invocationCount = 1/* ,threadPoolSize = 3 */)
	public void GoogleSearch() throws IOException, InterruptedException {
		driver.get("https://www.google.com");
		htmlreporter.config().setTheme(Theme.STANDARD);
		htmlreporter.config().setDocumentTitle("Test-Report");
		rep.attachReporter(htmlreporter);
		ExtentTest test = rep.createTest("GoogleSearch").assignAuthor("QA").assignCategory("functional test case").assignDevice("Windows");
		driver.findElement(By.name("q")).sendKeys("selenium");
		test.log(Status.PASS, "google launched");
		test.info("googlelaunched");
		test.addScreenCaptureFromPath(captureScreenshot(driver));
		Thread.sleep(5000);
		driver.findElement(By.xpath("//ul[@role='listbox']/li[1]")).click();
		driver.findElement(By.name("q")).click();
		wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[text()='Selenium']"))));
		try {
			Assert.assertTrue(driver.getTitle().contains("google"), "Search for selenium is successful");
			test.log(Status.PASS, "Search for selenium is successful");
		}catch (AssertionError e) {
			test.log(Status.FAIL, "Search for selenium is successful");	
			test.addScreenCaptureFromPath(captureScreenshot(driver));
			test.log(Status.INFO, e.getLocalizedMessage());
		}
	}
	
	@AfterTest
	public void tearDown() {
		
		rep.flush();
		driver.close();
		driver.quit();
	}
	
	public static String captureScreenshot(WebDriver driver) throws IOException {
		
		/*
		 * TakesScreenshot ts = (TakesScreenshot) driver;
		 * ts.getScreenshotAs(OutputType.FILE);
		 */
		File srcfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/Screenshots/image"+System.currentTimeMillis()+".png";
		File filepath= new File(path);
		String destpathlocation = filepath.getAbsolutePath();
		FileUtils.copyFile(srcfile, filepath);
		return destpathlocation;
		
	}
	
	
}
