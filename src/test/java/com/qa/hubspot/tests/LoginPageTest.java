package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
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

@Epic("Epic - 101 : Create login page features")
@Feature("US - 501 : create test for login page on hubspot")
public class LoginPageTest {
		WebDriver driver;
		BasePage basepage;
		Properties prop;
		LoginPage loginpage;
		Credentials userCred;
		
		@BeforeMethod(alwaysRun=true)
		@Parameters(value ={"browser"})
		public void setUp(@Optional String browser) {
			String browsername=null;
			basepage=new BasePage();
			prop=basepage.init_properties();
			
			if(browser.equals(null) || browser.equals("") || browser.isEmpty()){
				 browsername = prop.getProperty("browser");
			}else{
				browsername = browser;
			}
			
			driver=basepage.init_driver(browsername);
			driver.get(prop.getProperty("url"));
			loginpage=new LoginPage(driver);
			userCred=new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		}
		
		@Test(priority=1)
		@Description("verify LoginPage Title Test....")
		@Severity(SeverityLevel.NORMAL)
		public void verifyLoginPageTest() throws InterruptedException {
			//Thread.sleep(5000);
			String title=loginpage.getPageTitle();
			System.out.println("login page title is: "+title);
			Assert.assertEquals(title, AppConstants.LOGIN_PAGE_TITLE);
		}
		
		@Test(priority=2,groups="sanity")
		@Description("verify Sign up link Test....")
		@Severity(SeverityLevel.CRITICAL)
		public void verifySignUpLinkTest() {
			Assert.assertTrue(loginpage.checkSignUpLink());
		}
		
		@Test(priority=3)
		@Description("verify Login Test")
		@Severity(SeverityLevel.BLOCKER)
		public void loginTest() {
			HomePage homePage=loginpage.doLogin(userCred);
			String accountName=homePage.getLoggedInUserAccountName();
			Assert.assertEquals(accountName, prop.getProperty("accountname"));
		}
		
		@DataProvider
		public Object[][] getLoginInvalidData() {
			Object data[][]= {{"test156@gmail.com","test123"},
							  {"test2we@gmail.com"," "},
							  {"  ","test12345"},
							  {"test123","test"},
							  {" "," "}
							  };
			return data;
		}
		
		@Test(priority=4, dataProvider="getLoginInvalidData",enabled=false)
		public void login_InvalidTestCases(String username,String pwd) {
			userCred.setAppUsername(username);
			userCred.setAppPassword(pwd);
			loginpage.doLogin(userCred);
			
			Assert.assertTrue(loginpage.checkLoginErrorMsg());
		}
		
		@AfterMethod(alwaysRun=true)
		public void tearDown() {
			driver.quit();
		}
	
}
