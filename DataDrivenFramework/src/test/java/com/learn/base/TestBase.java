package com.learn.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.learn.utilities.ExcelReader;
import com.learn.utilities.ExtentManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties Config = new Properties();
	public static Properties  OR = new Properties();
	public static FileInputStream fis;
	public static  Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\TestData.xlsx");
	public static WebDriverWait wait;
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	public static String browser;
	
			
	@BeforeSuite
	public void setUp()
	{
		if(driver==null)
		{
			
			try {
				fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				Config.load(fis);
				log.debug("config file Loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.debug("OR file Loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(System.getenv("browser")!=null && !System.getenv("browser").isEmpty()){
				browser=System.getenv("browser");
			}else{
				browser=Config.getProperty("browser");
			}
			
			Config.setProperty("browser", browser);
			
			if(Config.getProperty("browser").equals("firefox")){
				driver = new FirefoxDriver();
			}else if(Config.getProperty("browser").equals("chrome")){
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +"\\src\\test\\resources\\executables\\chromedriver.exe");
				driver = new ChromeDriver();
				log.debug("chrome browser Launched");
			}
			
			driver.get(Config.getProperty("testsiteurl"));
			log.debug("Navigated to :" + Config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("implicit.wait")), TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 5);
		}
	}
	
	//for locator writing in easy way
	public void click(String locator)
	{	
		if(locator.endsWith("_CSS")){
		driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		}else if(locator.endsWith("_XPATH")){
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
		}else if(locator.endsWith("_ID")){
			driver.findElement(By.id(OR.getProperty(locator))).click();
		}
		
		test.log(LogStatus.INFO, " clicking on : "+locator);
	}
	
	//for locator writing in easy way
	public void type(String locator, String value)
	{
		if(locator.endsWith("_CSS")){
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		}else if(locator.endsWith("_XPATH")){
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		}else if(locator.endsWith("_ID")){
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
		}
		test.log(LogStatus.INFO, " Typing in : "+locator+ " entered value as " +value);
	}
	
	static WebElement dropDown;
	
	public void select(String locator, String value)
	{
		if(locator.endsWith("_CSS")){
			dropDown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
		}else if(locator.endsWith("_XPATH")){
			dropDown = driver.findElement(By.xpath(OR.getProperty(locator)));
		}else if(locator.endsWith("_ID")){
			dropDown = driver.findElement(By.id(OR.getProperty(locator)));
		}	
		
		Select select = new Select(dropDown);
		select.selectByVisibleText(value);
		test.log(LogStatus.INFO, " Selecting from dropdown : "+locator+ " value as " +value);
	}
	
	public boolean isElementPresent(By by){
		
		try{
			
			driver.findElement(by);
			return true;
			
		}catch(NoSuchElementException e){
			return false;
		}
		
	}
	
	@AfterSuite
	public void tearDown()
	{
		if(driver!=null)
		{
			driver.quit();
		}
		
		log.debug("Test Execution Completed");

	}
	
	

}
