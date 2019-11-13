package com.learn.testcases;

import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.learn.base.TestBase;
import com.learn.utilities.TestUtil;

public class AddCustomerLoginTest extends TestBase{

	@Test(dataProviderClass=TestUtil.class, dataProvider="dp")
	public void addCustomerTest(Hashtable<String,String> data) throws InterruptedException
  	{
		if(!data.get("runmode").equals("Y")){
			throw new SkipException("Skipping the test as the run mode is No");
		}
		
		click("addCustBtn_CSS");
		type("firstName_CSS", data.get("firstName"));
		type("lastName_XPATH", data.get("lastName"));
		type("postCode_CSS", data.get("postCode"));
		click("addCustBtn1_CSS");
		
		Thread.sleep(3000);
		
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(data.get("alerttext"))); 
		
		alert.accept();
		
		Thread.sleep(3000);
		
		//Assert.fail("Customer not added successfully");
	}
	
	
}
