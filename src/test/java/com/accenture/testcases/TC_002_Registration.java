package com.accenture.testcases;

import org.testng.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.accenture.helper.BaseClass;
import com.accenture.helper.BrowserFactory;
import com.accenture.helper.ExcelReader;
import com.accenture.pages.LoginPage;
import com.accenture.pages.LogoutPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class TC_002_Registration extends BaseClass{
	
	/*
	 * @BeforeMethod public void reporting() { report = new ExtentReports();
	 * ExtentSparkReporter sparkReport= new
	 * ExtentSparkReporter(System.getProperty("user.dir")+"/Reports/Autmation.html")
	 * ; sparkReport.config().setDocumentTitle("Automation TestReport");
	 * sparkReport.config().setReportName("Selenium framework building reports");
	 * sparkReport.config().setTheme(Theme.DARK);
	 * report.attachReporter(sparkReport);
	 * 
	 * }
	 */	
	@Test(priority=2)
	public void loginAsAdmin() throws InterruptedException {
		logger = report.createTest("Test case 1", "Login Test");
		logger.info("LOG: INFO Login test started");
		LoginPage login =PageFactory.initElements(driver,LoginPage.class);
		ExcelReader reader=new ExcelReader();
		String username=reader.getCellData(0, 0, 0);
		String password=reader.getCellData(0, 0, 1);
		//LoginPage login=new LoginPage(driver);
		login.loginToApplication(username,password);
	}
	
	@Test(priority=2,dependsOnMethods= "loginAsAdmin")
	public void logoutFromApplication() throws InterruptedException {
	
	logger=report.createTest("Test Case 2 : Lotout Test");
	LogoutPage logout=PageFactory.initElements(driver, LogoutPage.class);
	logout.logoutApplication();
		
		
	}

}
