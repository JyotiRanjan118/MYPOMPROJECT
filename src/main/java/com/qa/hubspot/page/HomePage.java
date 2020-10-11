package com.qa.hubspot.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.ElementUtil;

public class HomePage extends BasePage{
		
	WebDriver driver;
	ElementUtil elementUtil;
	
	By header=By.cssSelector("h1.private-header__heading");
	By accountName=By.xpath("//span[@class='account-name ']");
	
	/*By parentContacts=By.xpath("//li[@role='none' and @class='expandable']/a[@data-tracking='click hover']");
	By childContacts=By.linkText("Contacts");*/
	
	By parentContacts = By.id("nav-primary-contacts-branch");
	By childContacts = By.id("nav-secondary-contacts");
	
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		elementUtil=new ElementUtil(driver);
	}
	
	public String getHomePageTitle() {
		elementUtil.waitForTitlePresent(AppConstants.HOME_PAGE_TITLE);
		return elementUtil.doGetPageTitle();
	}
	
	public String getHomePageHeader() {
		elementUtil.waitForElementPresent(header);
		return elementUtil.doGetText(header);
	}
	
	public String getLoggedInUserAccountName() {
		elementUtil.waitForElementPresent(accountName);
		return elementUtil.doGetText(accountName);
	}
	
	
	public void clickOnContacts() {
		elementUtil.waitForElementPresent(parentContacts);
		elementUtil.doClick(parentContacts);
		
		elementUtil.waitForElementPresent(childContacts);
		elementUtil.doClick(childContacts);
	}
	
	public ContactsPage goToContactsPage() {
		clickOnContacts();
		return new ContactsPage(driver);
	}
}
