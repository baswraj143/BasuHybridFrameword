package com.accenture.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LogoutPage {
	
	WebDriver driver;
	
	public LogoutPage(WebDriver driver) {
		
		this.driver=driver;
	}
	
	By welcomeText=By.xpath("//a[contains(text(),'Welcome ')]");
	By logoutText=By.xpath("//a[contains(text(),'Logout ')]");
	
	
	public void logoutApplication() throws InterruptedException{
		
		driver.findElement(welcomeText).getText().contains("Welcome");
		Thread.sleep(1000);
		driver.findElement(logoutText).click();
		
		
		//Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
		//return status;
		
	}
	
}
