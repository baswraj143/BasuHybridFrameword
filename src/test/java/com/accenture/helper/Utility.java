package com.accenture.helper;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.time.Duration.*;
import java.time.Period.*;

//import javax.xml.datatype.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.*;

public class Utility {
	
	
	/*
	 * public static void waitForWebelementAndClick(WebDriver driver, By locator,
	 * String logInfo) {
	 * 
	 * new WebDriverWait(driver,
	 * Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(locator
	 * )).click(); System.out.println("LOG: INFO"+ logInfo);
	 * 
	 * 
	 * }
	 */
	
		
	public static String captureScreenShot(WebDriver driver) {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String filePath=System.getProperty("user.dir")+"/ScreenShots/"+getCurrentDate()+".PNG";
		System.out.println("File path B ="+filePath);
		
		try {
			FileHandler.copy(src, new File(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Could not capture the screen shot"+e.getMessage());
		}
		return filePath;
		
	}

	public static String getCurrentDate() {
		
		
		SimpleDateFormat myformat=new SimpleDateFormat("HH_mm_ss_dd_MM_yyyy");
		Date dt = new Date();
		return myformat.format(dt);
	}

	/*
	 * public static void acceptAlert(WebDriver driver,By locator,String logInfo) {
	 * new WebDriverWait(driver,
	 * Duration.ofSeconds(30)).until(ExpectedConditions.alertIsPresent()).accept();
	 * System.out.println("LOG:INFO "+logInfo); }
	 * 
	 * public static void acceptAlert(WebDriver driver,By locator,int time,String
	 * logInfo) { new WebDriverWait(driver,
	 * Duration.ofSeconds(time)).until(ExpectedConditions.alertIsPresent()).accept()
	 * ; System.out.println("LOG:INFO "+logInfo); }
	 * 
	 * public static void dismissAlert(WebDriver driver,By locator,String logInfo) {
	 * new WebDriverWait(driver,
	 * Duration.ofSeconds(30)).until(ExpectedConditions.alertIsPresent()).dismiss();
	 * System.out.println("LOG:INFO "+logInfo); }
	 * 
	 * public static void dismissAlert(WebDriver driver,By locator,int time,String
	 * logInfo) { new WebDriverWait(driver,
	 * Duration.ofSeconds(time)).until(ExpectedConditions.alertIsPresent()).dismiss(
	 * ); System.out.println("LOG:INFO "+logInfo); }
	 * 
	 * 
	 * 
	 * public static void waitForMessAndPartialVerify(WebDriver driver,By
	 * locator,String expected,String logInfo) {
	 * 
	 * WebElement element=new WebDriverWait(driver,
	 * Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(locator
	 * )); String actual=element.getText(); if(actual.contains(expected)) {
	 * System.out.println("LOG:PASS- Message verified"); } else {
	 * System.out.println("LOG:FAIL- Message validation failed"); }
	 * 
	 * System.out.println("LOG:INFO- Waited for message validation");
	 * 
	 * 
	 * if(new WebDriverWait(driver,
	 * Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(locator
	 * )).getText().contains(expected)) {
	 * 
	 * } }
	 * 
	 * 
	 * public static WebElement waitForWebElement(WebDriver driver,By locator,String
	 * logInfo) { WebElement element=new WebDriverWait(driver,
	 * Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(locator
	 * )); System.out.println("LOG:INFO-"+logInfo); return element; }
	 * 
	 * 
	 * public static void waitForWebElementAndType(WebDriver driver,By
	 * locator,String textToBeType,String logInfo) { new WebDriverWait(driver,
	 * Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(locator
	 * )).sendKeys(textToBeType); System.out.println("LOG:INFO-"+logInfo); }
	 * 
	 * public static void waitForWebElementAndClick(WebDriver driver,By
	 * locator,String logInfo) { new WebDriverWait(driver,
	 * Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(locator
	 * )).click(); System.out.println("LOG:INFO-"+logInfo); }
	 * 
	 * public static void waitForWebElementAndClick(WebDriver driver,By locator,int
	 * time,String logInfo) { new WebDriverWait(driver,
	 * Duration.ofSeconds(time)).until(ExpectedConditions.elementToBeClickable(
	 * locator)).click(); System.out.println("LOG:INFO-"+logInfo); }
	 */

	public static void waitForSeconds(int second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {

		}
	}

	public static void verifyBrokenLinkOrImage(String url) throws IOException, MalformedURLException {
		HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();

		conn.connect();

		int code = conn.getResponseCode();

		String serverResponse = conn.getResponseMessage();
		System.out.println("Checking url " + url);
		System.out.println("Code from server is " + code);
		System.out.println("Response from server is " + serverResponse);

		// &&
		// ||

		if (code == 200 || code == 301 || code == 302) {
			System.out.println("Link is working fine");
		} else {
			System.out.println("Broken link " + url);
		}

	}

	public static void selectValueFromList(WebDriver driver, String xpath, String value) {
		List<WebElement> allSugg = driver.findElements(By.xpath(xpath));

		for (WebElement element : allSugg) {
			if (element.getText().contains(value)) {
				System.out.println("LOG:INFO- Element found");
				element.click();
				break;
			}
		}
	}

	public static void selectValueFromListUsingIterator(WebDriver driver, String xpath, String value) {
		List<WebElement> allSugg = driver.findElements(By.xpath(xpath));

		Iterator<WebElement> itr = allSugg.iterator();

		while (itr.hasNext()) {
			WebElement element = itr.next();

			if (element.getText().contains(value)) {
				System.out.println("LOG:INFO- Element found");
				element.click();
				break;
			}
		}

	}

}
