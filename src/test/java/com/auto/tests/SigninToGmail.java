package com.auto.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.auto.pages.LoginPage;
import com.auto.commons.*;

public class SigninToGmail extends BaseClass {

	

	@Test
	public void TC001_SignIn() {
		LoginPage lp = new LoginPage(driver);
		lp.enterUserName(userName);
		lp.clickOnNext();
		WebElement forgotPassword = driver.findElement(By.xpath("//span[text()='Forgot password?']"));
		CommonMethods.waitforVisibilityElement(driver, forgotPassword);
		lp.enterPassword(Password);
		lp.clickOnNext();
		By composeMail = By.xpath("//div[text()='Compose']");
		CommonMethods.waitforVisibilityElement(driver, composeMail);

		/*
		 * WebElement compose = driver.findElement(By.xpath("//div[text()='Compose']"));
		 * CommonMethods.waitforVisibilityElement(driver, compose);
		 */
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Compose']")).isDisplayed(), "User Logged in");
		if (driver.findElement(By.xpath("//div[text()='Compose']")).isDisplayed()) {
			Reporter.log("User logged in");
			System.out.println("User logged in");
		} else {
			System.out.println("user not logged in");
			Reporter.log("User not logged in");
		}

		lp.ClickonSignOutLink();
		driver.switchTo().frame("account");
		lp.clickonSignOut();
		driver.switchTo().defaultContent();

	}

}
