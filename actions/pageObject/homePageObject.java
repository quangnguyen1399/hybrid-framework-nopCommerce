package pageObject;

import org.openqa.selenium.WebDriver;

import commons.abstractPage;
import pageUIs.homePageUI;

public class homePageObject extends abstractPage {
	WebDriver driver;
	public homePageObject(WebDriver driver) {
		this.driver = driver;
	}
	public registerPageObject clickToRegisterLink() {
		waitForElementClickable(driver, homePageUI.REGISTER_LINK);
		clickToElement(driver, homePageUI.REGISTER_LINK);
		return PageGeneratorManager.getRegisterPage(driver);
	}

	public loginPageObject clickToLoginLink() {
		waitForElementClickable(driver, homePageUI.LOGIN_LINK);
		clickToElement(driver, homePageUI.LOGIN_LINK);
		return PageGeneratorManager.getLoginPage(driver);
	}
	public void clickToLogoutLink() {
		waitForElementClickable(driver, homePageUI.LOGOUT_LINK);
		clickToElement(driver, homePageUI.LOGOUT_LINK);
	}


}
