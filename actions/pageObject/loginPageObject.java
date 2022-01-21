package pageObject;

import org.openqa.selenium.WebDriver;

import commons.abstractPage;
import pageUIs.homePageUI;

public class loginPageObject extends abstractPage {
	WebDriver driver;
	public loginPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public registerPageObject clickToRegisterLink() {
		waitForElementClickable(driver, homePageUI.REGISTER_LINK);
		clickToElement(driver, homePageUI.REGISTER_LINK);
		return PageGeneratorManager.getRegisterPage(driver);
	}
	
	

}
