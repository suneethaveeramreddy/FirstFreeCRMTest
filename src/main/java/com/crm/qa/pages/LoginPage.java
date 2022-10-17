package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase {
	 //page factory or Object Repository
	
	@FindBy(name="username")
	WebElement Username;

	@FindBy(name="password")
	WebElement Password;
	
	@FindBy(xpath="//div[@class='input-group-btn']//input")
	WebElement LoginBtn;
	
	@FindBy(xpath="//a[contains(text(),'Sign Up')]")
	WebElement SignUPLink;
	
	@FindBy(xpath="//a[@class='navbar-brand']//img")
	WebElement CRMPROLogo;
	
	public  LoginPage() {
		PageFactory.initElements(driver, this);
	}
	//#Actions
	public String ValidateLoginPageTitle() {
		 return driver.getTitle();
	}
	public boolean ValidateCRMlogoImg() {
		return CRMPROLogo.isDisplayed();
	}
	public  HomePage ValidateLoginTest(String un, String pwd) throws InterruptedException {
		Username.sendKeys(un);
		Password.sendKeys(pwd);
		LoginBtn.click();
		Thread.sleep(3000);
		return new HomePage();
	}
}
