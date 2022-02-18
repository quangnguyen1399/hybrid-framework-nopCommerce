package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.abstractTest;
import pageObject.PageGeneratorManager;
import pageObject.homePageObject;
import pageObject.loginPageObject;
import pageObject.searchPageObject;

public class User_04_Search extends abstractTest{
	WebDriver driver;
	homePageObject homePage;
	loginPageObject loginPage;
	searchPageObject searchPage;
	
	String messageNotExitData = "No products were found that matched your criteria.";
	
	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appurl) {
		driver = getBrowserDriver(browserName, appurl);
		
		log.info("Pre-condition Step 01: Open homepage nop commerce");
		homePage = PageGeneratorManager.getHomePage(driver);
		
		log.info("Pre-condition Step 02: Click and open login link");
		loginPage = homePage.clickToLoginLink();
		
		log.info("Pre-condition Step 03: Input to email textbox: " + User_01_Register.email);
		loginPage.inputToEmailTextbox(User_01_Register.email);
		
		log.info("Pre-condition Step 04: Input to password textbox: " + User_01_Register.password);
		loginPage.inputToPasswordTextbox(User_01_Register.password);
		
		log.info("Pre-condition Step 05: Click login button");
		homePage = loginPage.clickToLoginButton();
		
		log.info("Pre-condition Step 06: Click to search link at footer");
		searchPage = homePage.clickToSearchLink();
	}
	
	@Test
	public void Search_01_Empty_Data() {
		log.info("Search_Empty data Step 01: click to search button");
		searchPage.clickToSearchButton();
		
		log.info("Search_Empty data Step 02: verify error empty data displayed");
		verifyEquals(searchPage.isErrorEmptyDataDisplayed(), "Search term minimum length is 3 characters");		
	}
	
	@Test
	public void Search_02_Not_Exit_Data() {
		log.info("Search_Not exit data Step 01: ");
		searchPage.inputToSearchKeywordTextbox("macbook pro 2015");
		
		searchPage.clickToSearchButton();
		
		verifyEquals(searchPage.isMessageNotExitData(), messageNotExitData);
	}
	
	@Test
	public void Search_03_Relative_Product() {
		searchPage.inputToSearchKeywordTextbox("Lenovo");
		
		searchPage.clickToSearchButton();
		
		verifyEquals(searchPage.isLenovoIdeaCentre600AllInOnePC(), "Lenovo IdeaCentre 600 All-in-One PC");
		verifyEquals(searchPage.isLenovoThinkpadX1CarbonLaptop(), "Lenovo Thinkpad X1 Carbon Laptop");
		
	}
	
	@Test
	public void Search_04_Absolute_Product() {
		searchPage.inputToSearchKeywordTextbox("ThinkPad X1 Carbon");
		
		searchPage.clickToSearchButton();
		
		verifyEquals(searchPage.isLenovoThinkpadX1CarbonLaptop(), "Lenovo Thinkpad X1 Carbon Laptop");
	}
	
	@Test
	public void Search_05_Parent_Categories() {
		searchPage.inputToSearchKeywordTextbox("Apple MacBook Pro");
		
		searchPage.clickToAdvancedSearch();
		
		searchPage.inputToCategory("Computers");
		
		searchPage.clickToSearchButton();
		
		verifyEquals(searchPage.isMessageNotExitData(), messageNotExitData);
		
		
	}
	
	@Test
	public void Search_06_Sub_Categories() {
		searchPage.inputToSearchKeywordTextbox("Apple MacBook Pro");
		
		searchPage.inputToCategory("Computers");
		
		searchPage.clickToSubCategories();
		
		searchPage.clickToSearchButton();
		
		verifyEquals(searchPage.isAppleMacbookPro13inch(), "Apple MacBook Pro 13-inch");
		
	}
	
	@Test
	public void Search_07_Incorrect_Manufacturer() {
		searchPage.inputToSearchKeywordTextbox("Apple MacBook Pro");
		
		searchPage.inputToCategory("Computers");
		
		searchPage.inputToManufacturer("HP");
		
		searchPage.clickToSearchButton();
		
		verifyEquals(searchPage.isMessageNotExitData(), messageNotExitData);
	}
	
	@Test
	public void Search_08_Correct_Manufacturer() {
		searchPage.inputToSearchKeywordTextbox("Apple MacBook Pro");
		
		searchPage.inputToCategory("Computers");
		
		searchPage.inputToManufacturer("Apple");
		
		searchPage.clickToSearchButton();
		
		verifyEquals(searchPage.isAppleMacbookPro13inch(), "Apple MacBook Pro 13-inch");
	}
	
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

}
