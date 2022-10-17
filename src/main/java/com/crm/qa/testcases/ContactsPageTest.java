package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.utilities.TestUtil;

public class ContactsPageTest extends TestBase {
	LoginPage loginpage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactspage;
	String sheetName="Contacts";
	
	public ContactsPageTest() {
		super();
	}
	@BeforeMethod
	public void SetUp() throws InterruptedException {
		initialization();
		 testUtil=new TestUtil();
		 contactspage=new ContactsPage();
		 loginpage=new LoginPage();
		 homePage=loginpage.ValidateLoginTest(prop.getProperty("Username"), prop.getProperty("Password"));
		 testUtil.switchToFrame();
		 contactspage=homePage.ContactsIconClick();
	}
	@Test(priority=1)
	public void VerifyContactsLabelTest(){
		Assert.assertTrue(contactspage.VerifyContactsLabel(),"Contacts label is not displayed");
	}
	@Test(priority=2)
	public void VerifyCheckboxClickTest(){
		contactspage.VerifyCheckboxClick("Venkata Subbu");
	}
	@Test(priority=3)
	public void VerifyMultipleCheckboxClickTest() {
		contactspage.VerifyCheckboxClick("Venkata Subbu");
		contactspage.VerifyCheckboxClick("Swecha Antara");
	}
	@DataProvider
	public Object[][] getTestData() {
		Object data[][]=TestUtil.getTestData(sheetName);
		return data;
		
	}
	@Test(priority=4 , dataProvider="getTestData")
	public void ValidateCreateNewContact(String title,String firstName,String lastName,String company) {
		homePage.ClickOnNewContactLink();
		//contactspage.CreateNewContact("Mr.", "Nagendra", "Sunkara", "Cognizant");
		contactspage.CreateNewContact(title, firstName, lastName, company);
		
	}
	@AfterMethod
	public void teardown() {
		driver.quit();
	}

}
