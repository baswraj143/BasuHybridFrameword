package com.accenture.pages;

import static org.testng.AssertJUnit.assertTrue;

import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class LoginPage {
	WebDriver driver;
	
	public LoginPage(WebDriver driver){
		
		this.driver=driver;
	}
	
	By username=By.id("txtUsername");
	By password1=By.id("txtPassword");
	By loginBtn=By.className("button");
	
	public boolean loginToApplication(String usernam, String password) throws InterruptedException{
		boolean status=false;
		
		driver.findElement(username).sendKeys(usernam);
		Thread.sleep(1000);
		driver.findElement(password1).sendKeys(password);
		Thread.sleep(1000);
		driver.findElement(loginBtn).click();
		
		try {
			 //status=driver.getCurrentUrl().contains("dashboard");
				Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"), "Login failed");
				status=true;
		}catch(Exception e) {
			
			System.out.println("Login failed");
			
		}
		
		
		//Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
		return status;
		
	}
}
