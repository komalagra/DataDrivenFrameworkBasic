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

public class OpenAccountTest extends TestBase {

	@Test(dataProviderClass = TestUtil.class, dataProvider = "dp")
	public void openAccountTest(Hashtable<String,String> data) throws InterruptedException {
		// runmode
		if (!TestUtil.isTestRunnable("openAccountTest", excel)) {
			throw new SkipException("Skipping the Test " + "openAccountTest".toUpperCase() + " as the Runmode is No");
		}

		click("openAcc_XPATH");
		select("Customer_CSS", data.get("customer"));
		select("Currency_CSS", data.get("currency"));
		click("process_CSS");
		Thread.sleep(2000);

		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(data.get("alerttext")));

		alert.accept();
	}

}
