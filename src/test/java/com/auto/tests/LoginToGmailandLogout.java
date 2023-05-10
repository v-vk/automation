package com.auto.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.auto.commons.CommonMethods;
import com.auto.pages.LoginPage;

public class LoginToGmailandLogout extends BaseClass {

	@Test(enabled = true)
	public void TC002_ReadTheSenderName() {
		LoginPage lp = new LoginPage(driver);
		lp.enterUserName(userName);
		lp.clickOnNext();
		WebElement forgotPassword = driver.findElement(By.xpath("//span[text()='Forgot password?']"));
		CommonMethods.waitforVisibilityElement(driver, forgotPassword);
		lp.enterPassword(Password);
		lp.clickOnNext();
		WebElement compose = driver.findElement(By.xpath("//div[text()='Compose']"));
		CommonMethods.waitforVisibilityElement(driver, compose);
		if (driver.getTitle().contains(userName)) {
			System.out.println("User logged in");
		} else {
			System.out.println("user not logged in");
		}
		
		//read the mails
		
		int row = driver.findElements(By.xpath("//table[@role='grid']/tbody/tr")).size();
		int column = driver.findElements(By.xpath("//table[@role='grid']/tbody/tr[1]/td")).size();
		System.out.println("rows :"+row );
		System.out.println("columns :"+column );
		
		for(int i = 1;i<=row;i++) {
			String xpath = "//table[@role='grid']/tbody/tr["+i+"]/td[4]";
			String xpath1 = "//table[@role='grid']/tbody/tr["+i+"]/td[2]";
			String emailName = driver.findElement(By.xpath(xpath)).getText();
			System.out.println(emailName);
			driver.findElement(By.xpath(xpath1)).click();
			driver.findElement(By.xpath(xpath1)).click();
		}
		
		
		lp.ClickonSignOutLink();
		driver.switchTo().frame("account");
		lp.clickonSignOut();
		driver.switchTo().defaultContent();


	}
	
}
