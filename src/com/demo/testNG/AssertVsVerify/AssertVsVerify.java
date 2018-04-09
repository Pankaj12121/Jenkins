package com.demo.testNG.AssertVsVerify;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.TestRunner;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.beust.testng.TestNG;

public class AssertVsVerify {
	static WebDriver driver ;
	@Test
	public void testNG() {
		System.setProperty("webdriver.chrome.driver","\\Grid\\chromedriver.exe");
		driver = new ChromeDriver();
		Report("Driver Launched");
		String baseUrl = "http://opensource.demo.orangehrmlive.com/";
		String expectedTitle = "OrangeHRM1";
		String actualTitle = "";
		String ExpectedWelcomeUser="Welcome Admin1";
		driver.get(baseUrl);
		Report("Navigated to"+baseUrl);
		actualTitle = driver.getTitle();
		login();
		WebElement welcome= driver.findElement(By.linkText("Welcome Admin"));
		String ActualWelcomeUser= welcome.getText();
		try{
			//verify(actualTitle, expectedTitle);

			//verify(ActualWelcomeUser, ExpectedWelcomeUser);
			/* ....................................Verify Vs Assert.........................................................	*/

			//hardAssert(ActualWelcomeUser, ExpectedWelcomeUser);

			//hardAssert(actualTitle, expectedTitle);

			softAssert(ActualWelcomeUser, ExpectedWelcomeUser);

			softAssert(actualTitle, expectedTitle);

		}catch(Exception e){

			Report("Test case execution stops as checkpoint failed- HardAssert used");

		}
		exitApplication();
	}

	public void verify(String actualTitle, String expectedTitle){

		if (actualTitle.contentEquals(expectedTitle)){
			Report("Test Passed!");
		} else {
			Report("Test Failed");
		}
		Report("Verify continues the execution even if checkpoint get failed");

	}

	public void hardAssert(String actualTitle, String expectedTitle){

		Assert.assertEquals(actualTitle, expectedTitle);

	}
	public void softAssert(String actualTitle, String expectedTitle){
		try{

			Assert.assertEquals(actualTitle, expectedTitle);

		}catch(AssertionError e){

			e.printStackTrace();

		}
	}

	public void login(){
		WebElement userName= driver.findElement(By.id("txtUsername"));//By id 	
		WebElement password= driver.findElement(By.name("txtPassword"));//By name 
		WebElement submit= driver.findElement(By.className("button"));//By name 	
		userName.sendKeys("Admin");
		Report("UserName entered "+ "Admin");
		password.sendKeys("admin");
		Report("Password entered "+ "admin");
		submit.click();
		Report("submit button clicked");
	}

	public void exitApplication(){
		driver.close();

		Report("Test case executed successfully");

	}

	public void Report(String s){

		Reporter.log(s);

	}
	@BeforeSuite
	public void GenReport(ITestContext ctx){

		System.out.println("In after suite");
		TestRunner runner = (TestRunner) ctx;
		runner.setOutputDirectory("C:\\Tosca");


	}

}
