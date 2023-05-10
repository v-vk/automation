package com.auto.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.auto.commons.CommonMethods;

public class LoginPage {

	WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	private 
	@FindBy(name = "identifier") WebElement txtUserName;
		
//	@FindBy(name = "") List<WebElement> lelelements;

	private
	@FindBy(xpath = "//span[text()='Next']") WebElement btnNext;
	
	private
	@FindBy(name="Passwd") WebElement txtpwd;
	
	private @FindBy(css = "a[aria-label^='Google Account:']") WebElement linkSignOut;
	private @FindBy(xpath="//div[text()='Sign out']") WebElement divSignOut;
	
	public void enterUserName(String email) {
		txtUserName.sendKeys(email);
	}
	
	public void clickOnNext() {
		CommonMethods.waitforVisibilityElement(driver, btnNext);
		btnNext.click();
	}

	public void enterPassword(String password) {
		txtpwd.sendKeys(password);
	}
	
	public void ClickonSignOutLink() {
		linkSignOut.click();
	}
	
	public void clickonSignOut() {
		divSignOut.click();
	}
	
}
