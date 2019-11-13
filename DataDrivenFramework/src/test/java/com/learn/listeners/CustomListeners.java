package com.learn.listeners;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.learn.base.TestBase;
import com.learn.utilities.MonitoringMail;
import com.learn.utilities.TestConfig;
import com.learn.utilities.TestUtil;
import com.relevantcodes.extentreports.LogStatus;

public class CustomListeners  extends TestBase implements ITestListener, ISuiteListener{

	public String msgBody;

	
	public void onTestStart(ITestResult arg0) {
		test = rep.startTest(arg0.getName().toUpperCase());
		
	}

	public void onTestSuccess(ITestResult arg0) {
		test.log(LogStatus.PASS, arg0.getName().toUpperCase()+"PASS ");
		rep.endTest(test);
		rep.flush();
		
	}

	public void onTestFailure(ITestResult arg0) {
	
		try {
			TestUtil.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		test.log(LogStatus.FAIL, arg0.getName().toUpperCase()+"Failed with exception : "+arg0.getThrowable());
		test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
		
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		Reporter.log("Captureing ScreenShot");
		Reporter.log("<a target=\"blank\"href="+TestUtil.screenshotName+">ScreenShot</a>");
		rep.endTest(test);
		rep.flush();
		
	}

	public void onTestSkipped(ITestResult arg0) {
		test.log(LogStatus.SKIP, arg0.getName().toUpperCase()+"Skipped the test as the run mode in No");
		rep.endTest(test);
		rep.flush();
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ISuite suite) {
			

		MonitoringMail mail = new MonitoringMail();
		try {
			msgBody = "http://" + InetAddress.getLocalHost().getHostAddress() + ":8080/job/DataDrivenFrameworkProject/Extent_20Report/";
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, msgBody);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
