package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.crm.qa.base.TestBase;

public class HomePage extends TestBase{
	
	@FindBy(xpath="//td[contains(text(),'User: Suneetha Veeramreddy ')]")
	WebElement LoggedInUserName;
	
	@FindBy(xpath="//a[contains(text(),'Calendar')]")
	WebElement CalendarIcon;
	
	@FindBy(xpath="//a[contains(text(),'Contacts')]")
	WebElement ContactsIcon;
	
	@FindBy(xpath="//a[contains(text(),'Deals')]")
	WebElement DealsIcon;
	
	@FindBy(xpath="//a[@title='New Contact']")
	WebElement NewContactLink;
	
	public HomePage(){
		PageFactory.initElements(driver, this);
	}
	
	public String ValidateHomePageTitle() {
		return driver.getTitle();		
	}
	public boolean ValidateLoggedInUserName() {
		return LoggedInUserName.isDisplayed();
		
	}
	public CalendarPage CalendarIconClick() {
		CalendarIcon.click();
		return new CalendarPage();
	}
	public ContactsPage ContactsIconClick() {
		ContactsIcon.click();
		return new ContactsPage();
	}
	public DealsPage DealsIconClick() {
		DealsIcon.click();
		return new DealsPage();
	}
	public void ClickOnNewContactLink() {
		Actions action=new Actions(driver);
		action.moveToElement(ContactsIcon).build().perform();
		WebElement NewContactLink = driver.findElement(By.xpath("//a[@title='New Contact']"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", NewContactLink);
	}
}
