package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.page.HomePage;
import com.qa.hubspot.page.LoginPage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.Credentials;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Epic("Epic - 102 : Create Home page features")
@Feature("US - 502 : create test for home page on hubspot")
public class HomePageTest {
	WebDriver driver;
	BasePage basepage;
	Properties prop;
	LoginPage loginpage;
	HomePage homepage;
	Credentials userCred;
	
	@BeforeTest
	public void setUp() throws InterruptedException {
		basepage=new BasePage();
		prop=basepage.init_properties();
		String browsername=prop.getProperty("browser");
		driver=basepage.init_driver(browsername);
		driver.get(prop.getProperty("url"));
		loginpage=new LoginPage(driver);
		userCred=new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		homepage=loginpage.doLogin(userCred);
		//Thread.sleep(5000);
	}
	
	@Test(priority=1)
	@Description("verify HomePage Title Test....")
	@Severity(SeverityLevel.NORMAL)
	public void verifyHomePageTitleTest() {
		String title=homepage.getHomePageTitle();
		System.out.println("home page title is: "+title);
		Assert.assertEquals(title, AppConstants.HOME_PAGE_TITLE);
	}
	
	@Test(priority=2)
	@Description("verify Home page header Test....")
	@Severity(SeverityLevel.NORMAL)
	public void verifyHomePageHeaderTest() {
		String header=homepage.getHomePageHeader();
		System.out.println("Home page header is: "+header);
		Assert.assertEquals(header, AppConstants.HOME_PAGE_HEADER);
	}
	
	@Test(priority=3)
	@Description("verify LoginPage Test")
	@Severity(SeverityLevel.CRITICAL)
	public void verifyLoggedInUserTest() {
		String accountName=homepage.getLoggedInUserAccountName();
		System.out.println("Logged in account name: "+accountName);
		Assert.assertEquals(accountName, prop.getProperty("accountname"));
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
