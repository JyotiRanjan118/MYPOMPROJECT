package com.qa.hubspot.util;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.hubspot.base.BasePage;

public class ElementUtil extends BasePage{
		
	WebDriver driver;
	WebDriverWait wait;
	JavaScriptUtilGit jsUtil;
	Properties prop;
	
	public ElementUtil(WebDriver driver) {
		prop=super.prop;
		this.driver=driver;
		wait=new WebDriverWait(driver, AppConstants.DEFAULT_TIME_OUT);
		jsUtil=new JavaScriptUtilGit(driver);
	}
	
	public boolean waitForTitlePresent(String title) {
		wait.until(ExpectedConditions.titleContains(title));
		return true;
	}
	
	public boolean waitForElementPresent(By locator) {
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return true;
	}
	
	public boolean waitForElementVisible(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return true;
	}
	
	public String doGetPageTitle() {
		try {
			return driver.getTitle();
		} catch (Exception e) {
			System.out.println("some exception got occured while getting the title");
		}
		return null;
		
	}
	
	public WebElement getElement(By locator) {
		WebElement element=null;
		try {
			element=driver.findElement(locator);
			if(highlightelement) {
				jsUtil.flash(element);
			}
		} catch (Exception e) {
			System.out.println("some exception got occured while creating the web element");
		}
		return element;
	}
	
	public void doClick(By locator) {
		try {
			getElement(locator).click();
		} catch (Exception e) {
			System.out.println("some exception got occured while clicking on the web element");
		}
	}
	
	public void doSendKeys(By locator,String value) {
		try {
			WebElement ele=getElement(locator);
			ele.clear();
			ele.sendKeys(value);
		} catch (Exception e) {
			System.out.println("some exception got occured while entering value in a field");
		}
	}
	
	public boolean doIsDisplayed(By locator) {
		try {
			return getElement(locator).isDisplayed();
		} catch (Exception e) {
			System.out.println("some exception got occured");
		}
		return false;
	}
	
	public String doGetText(By locator) {
		try {
			return getElement(locator).getText();
		} catch (Exception e) {
			System.out.println("some exception got occured while getting the text from a webelement");
		}
		return null;
	}
	
	
}
