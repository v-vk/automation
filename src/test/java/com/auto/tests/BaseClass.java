package com.auto.tests;

import java.time.Duration;

import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import com.auto.pages.LoginPage;
import com.auto.utilities.Readconfig;
import com.auto.logs.Log;

public class BaseClass {
	Readconfig config = new Readconfig();
	public static WebDriver driver;
	public String userName = config.getUserName();
	public String Password = config.getPassword();

	@Parameters({ "browser" })
	@BeforeMethod
	public void setUp(String browser) {
//		String path = System.getProperty("user.dir");
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
//		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
			driver.navigate().to(config.getApplicationUrl());
			Log.info("application launched");

		}
	}

	public WebDriver getDriver() {
		return driver;
	}

	@AfterMethod
	public void teardown() {
		Log.info("Test completed");
		Log.info("==============================================================");
		driver.quit();
	}

}
