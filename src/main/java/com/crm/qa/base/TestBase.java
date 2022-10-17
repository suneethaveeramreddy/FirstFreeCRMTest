package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.crm.qa.utilities.TestUtil;
import com.crm.qa.utilities.WebEventListener;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	
	public static WebDriver driver;
	public static String DriverPath="C:\\Users\\sunee\\Downloads\\chromedriver_win32\\chromedriver.exe";
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	/*
	 * public static ExtentReports extent; public static ExtentSparkReporter spark;
	 * public static ExtentTest test;
	 * 
	 * public WebDriver getDriver() { return driver; }
	 */

	public TestBase() {
		
		try {
			 prop=new Properties();
			FileInputStream ip= new FileInputStream("C:\\Users\\sunee\\eclipse-workspace\\FreeCRMTest\\src"
					+ "\\main\\java\\com\\crm\\qa\\config\\config.properties");
			prop.load(ip);
			//System.out.println(prop.getProperty("Browser"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		public  static void initialization() {
			String Browsername=prop.getProperty("Browser");
			if(Browsername.equals("Chrome")) {
				/* driver=WebDriverManager.chromedriver().create(); */
				
				 System.setProperty("webdriver.chrome.driver", DriverPath); 
				 driver=new ChromeDriver();				
			}
			e_driver=new EventFiringWebDriver(driver);
			//Now create object of EventListerHandler to register it with eventFiringWebDriver
			eventListener= new WebEventListener();
			e_driver.register(eventListener);
			driver =e_driver;
			
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
			driver.get(prop.getProperty("url"));
		}
	
}
