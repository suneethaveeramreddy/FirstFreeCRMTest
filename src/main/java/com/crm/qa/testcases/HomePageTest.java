package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.CalendarPage;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.DealsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.utilities.TestUtil;

public class HomePageTest extends TestBase{
	LoginPage loginpage;
	HomePage homePage;
	TestUtil testUtil;
	CalendarPage calendarpage;
	ContactsPage contactspage;
	DealsPage dealspage;
	
	public HomePageTest() {
		super();
	}
	
	//test cases should be separated --independent with each other
	//before each test case -- launch the browser and login 
	//@test -- execute test case
	//after each test case -- close the browser 	
	
	@BeforeMethod
	public void SetUp() throws InterruptedException {
		initialization();
		 loginpage=new LoginPage();
		 testUtil=new TestUtil();
		 calendarpage=new CalendarPage();
		 contactspage=new ContactsPage();
		 dealspage=new DealsPage();
		 homePage=loginpage.ValidateLoginTest(prop.getProperty("Username"), prop.getProperty("Password"));
	}
	@Test(priority=1)
	public void ValidateHomePageTitleTest() {
		String title=homePage.ValidateHomePageTitle();
		String Expectedtitle="CRMPRO";
		Assert.assertEquals(title, Expectedtitle,"Home page title is not matched");
	}
	@Test(priority=2)
	public void ValidateLoggedInUserNameTest() {
		testUtil.switchToFrame();
		Assert.assertTrue(homePage.ValidateLoggedInUserName());
	}
	@Test(priority=3)
	public void VerifyCalendarpageClick() throws InterruptedException {
		testUtil.switchToFrame();
		calendarpage=homePage.CalendarIconClick();
		Thread.sleep(2000);
	}
	@Test(priority=4)
	public void VerifyContactsPage() throws InterruptedException {
		testUtil.switchToFrame();
		contactspage=homePage.ContactsIconClick();
		Thread.sleep(2000);
	}
	@Test(priority=5)
	public void VerifyDealsIconClick() throws InterruptedException {
		testUtil.switchToFrame();
		dealspage=homePage.DealsIconClick();
		Thread.sleep(2000);
	}
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
}
