package pages;

import static org.testng.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.qameta.allure.Allure;

public class StoreHomePage extends BasePage {
	Logger log;

	@FindBy (xpath ="//a[text()='Sign In']")
	WebElement signInLink;

	@FindBy (xpath ="//a[text()='My Account']")
	WebElement myAccountLink;

	@FindBy (xpath ="//h3")
	WebElement myAccountHeader;

	public StoreHomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);

		log = Logger.getLogger(StoreHomePage.class.getName());
		get();
	}


	public void naviagetToSignInPage() {
		log.trace("Praparing to sign in");
		signInLink.click();
		log.trace("After the sign-in click");
		Allure.addAttachment("On sign in page ", getPageTitleAfterLogin());
	}

	public MyAccountPage navigateToMyAccountPage() {
		myAccountLink.click();
		return new MyAccountPage(driver);
	}

	public String getPageTitleAfterLogin() {
		return driver.getTitle();
	}
	
	public String checkLogin() {
		return myAccountHeader.getText();
	}

	public void closeBrowser() {
		driver.quit();
	}

	@Override
	protected void isLoaded() throws Error {
		assertEquals(driver.getTitle(), "Avactis Demo Store","Application Loading Error!!" );
	}

	@Override
	protected void load() {

	}

}
