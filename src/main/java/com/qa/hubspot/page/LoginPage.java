package com.qa.hubspot.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.Credentials;
import com.qa.hubspot.util.ElementUtil;
import com.qa.hubspot.util.JavaScriptUtilGit;

public class LoginPage extends BasePage {
	WebDriver driver;
	ElementUtil elementUtil;
	JavaScriptUtilGit jsUtil;
	
	//1. locators - By
	By emailID=By.id("username");
	By password=By.id("password");
	By loginButton=By.id("loginBtn");
	By signUpLink=By.linkText("Sign up");
	By loginerrorText=By.xpath("//div[@class='private-alert__inner']");
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		elementUtil=new ElementUtil(driver);
		//jsUtil=new JavaScriptUtilGit(driver);
	}
	
	public String getPageTitle() {
		elementUtil.waitForTitlePresent(AppConstants.LOGIN_PAGE_TITLE);
		return elementUtil.doGetPageTitle();
	}
	
	public String getPageTitleUsingJS() {
		return jsUtil.getTitleByJS();
	}
	
	public boolean checkSignUpLink() {
		elementUtil.waitForElementPresent(signUpLink);
		return elementUtil.doIsDisplayed(signUpLink);
	}
	
	public HomePage doLogin(Credentials userCred) {
		//driver.findElement(emailID).sendKeys(username);
		//driver.findElement(password).sendKeys(pwd);
		//driver.findElement(loginButton).click();
		elementUtil.waitForElementPresent(emailID);
		elementUtil.doSendKeys(emailID, userCred.getAppUsername());
		elementUtil.doSendKeys(password, userCred.getAppPassword());
		elementUtil.doClick(loginButton);
		
		return new HomePage(driver);
	}
	
	public boolean checkLoginErrorMsg() {
		return elementUtil.doIsDisplayed(loginerrorText);
	}

}
