package com.accenture.helper;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseClass {
	
	public ExtentReports report;
	public WebDriver driver;
	public ExtentTest logger;
	
	@BeforeSuite
	public void setUpReport() {
		
	 System.out.println("LOG: INFO setting up the report");	
	 report = new ExtentReports();
	  ExtentSparkReporter sparkReport= new
	  ExtentSparkReporter(System.getProperty("user.dir")+"/Reports/Autmation"+Utility.getCurrentDate()+".html");
	  sparkReport.config().setDocumentTitle("Automation TestReport");
	  sparkReport.config().setReportName("Selenium framework building reports");
	  sparkReport.config().setTheme(Theme.DARK);
	  //sparkReport.config().setReportName("BSA");
	  report.attachReporter(sparkReport);
	  System.out.println("LOG: INFO report set up and ready to use");	
	}
	
	@BeforeClass
	public void openBrowser() {
		System.out.println("LOG: INFO Setting up Browser");	
		ConfigReader config = new ConfigReader();
		String browser=config.getValue("Browser");
		String url=config.getValue("StagingUrl");
		driver=BrowserFactory.startApplication(browser, url);
		System.out.println("LOG: INFO browser and application running up ");	
	}
	@AfterClass
	public void closeBrowser() {
		driver.quit();
		System.out.println("LOG: INFO Browser terminated");	
	}

	
	@AfterMethod
	public void tearDownReport(ITestResult result) {
		System.out.println("LOG: INFO capturing the test result");	
		if(result.getStatus()==ITestResult.SUCCESS){
			System.out.println("LOG: INFO Test passed");	
			logger.pass("Login test pass",MediaEntityBuilder.createScreenCaptureFromPath(Utility.captureScreenShot(driver)).build());
		}else if(result.getStatus()==ITestResult.FAILURE){
			System.out.println("LOG: INFO Test BSA failed"+result.getThrowable().getMessage());	
			//logger.fail("Login test failed"+ result.getThrowable().getMessage());
			try {
			logger.fail("Login Test failed"+ result.getThrowable().getMessage(),MediaEntityBuilder.createScreenCaptureFromPath(Utility.captureScreenShot(driver)).build());
			}catch(Exception e){
				System.out.println("Coult not able to capture the screen shot"+e.getMessage());
			}
		}
		report.flush();
		
		System.out.println("LOG: INFO report is generated");	
	}


}
