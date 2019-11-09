package com.learn.utilities;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.learn.base.TestBase;


public class TestUtil extends TestBase{
	
	public static String screenshotPath;
	public static String screenshotName;

	public static void captureScreenshot() throws IOException
	{
		Date d = new Date();
		File scrfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";
		FileUtils.copyFile(scrfile, new File(System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\"+screenshotName));
	}
}
