package com.crm.qa.testcases;


import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;

public class LoginPageTest extends TestBase {
	LoginPage loginpage;
	HomePage homePage;
	
	
	public LoginPageTest() {
		super();
	}
	
	@BeforeMethod
	public void SetUp() {
		initialization();
		 loginpage=new LoginPage();
		 
	}
	
	@Test(priority=1)
	public void LoginPageTitletest() {
		String title=loginpage.ValidateLoginPageTitle();
		String Expectedtitle="Free CRM - CRM software for customer relationship management, sales, and support.";
		Assert.assertEquals(title, Expectedtitle);
		
	}
	@Test(priority=2)
	public void CRMLogoImageTest() {
		boolean flag=  loginpage.ValidateCRMlogoImg();
		Assert.assertTrue(flag);
	}
	@Test(priority=3)
	public void LoginTest() throws InterruptedException {
		homePage=loginpage.ValidateLoginTest(prop.getProperty("Username"), prop.getProperty("Password"));
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

}
