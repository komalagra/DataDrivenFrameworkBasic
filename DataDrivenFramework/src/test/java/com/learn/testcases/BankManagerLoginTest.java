package com.learn.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.learn.base.TestBase;

public class BankManagerLoginTest extends TestBase{

	@Test
	public void bankManagerLoginTest() throws InterruptedException
	{
		//(verifyEquals("abc", "xyz");
	
		log.debug("Inside Bank Manager Login Test");
		click("bmlBtn_CSS");
		
		Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustBtn_CSS"))),"Login not successfull");
		log.debug("Login Successfully Executed");

		//Assert.fail("Login not successfull");

	}
}
