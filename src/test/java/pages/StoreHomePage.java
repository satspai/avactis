package pages;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.List;

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
	@FindBy (xpath ="//a[text()='Sign In']")
	WebElement signInLink;
	
	@FindBy (xpath ="//a[text()='My Account']")
	WebElement myAccountLink;
	
			
	public StoreHomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		get();
	}
	

	public void naviagetToSignInPage() {
		signInLink.click();
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
