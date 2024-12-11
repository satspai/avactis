package pages;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StoreHomePage extends BasePage {
	Logger log;
	
	@FindBy (xpath ="//a[text()='Sign In']")
	WebElement signInLink;
	
	@FindBy (xpath ="//a[text()='My Account']")
	WebElement myAccountLink;
	
			
	public StoreHomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		DOMConfigurator.configure("log4j-config.xml");
		log = Logger.getLogger(StoreHomePage.class.getName());
		get();
	}
	

	public void naviagetToSignInPage() {
		log.trace("Before the sign-in link click");
		signInLink.click();
		log.trace("After the sign-in link click");

	}
	
	public MyAccountPage navigateToMyAccountPage() {
		myAccountLink.click();
		return new MyAccountPage(driver);
	}

	public String getPageTitleAfterLogin() {
		return driver.getTitle();
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
