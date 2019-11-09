package com.learn.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.learn.base.TestBase;

public class BankManagerLoginTest extends TestBase{

	@Test
	public void loginAsBankManager() throws InterruptedException
	{
		log.debug("Inside Bank Manager Login Test");
		driver.findElement(By.cssSelector(OR.getProperty("bmlBtn"))).click();
		Thread.sleep(3000);
		Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustBtn"))),"Login not successfull");
		log.debug("Login Successfully Executed");
		Reporter.log("Login Successfully Executed");
		Assert.assertTrue(false);

	}
}
