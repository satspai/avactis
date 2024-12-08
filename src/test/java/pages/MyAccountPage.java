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

public class MyAccountPage extends LoadableComponent<MyAccountPage> {
	@FindBy (xpath ="//button[text()='Register']")
	WebElement registerLink;
	
	private WebDriver driver;
	private WebDriverWait wait;

	public MyAccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		get();
	}
	

	public RegistrationPage navigateToRegisterPage() {
		registerLink.click();
		System.out.println("after register click");
		return new RegistrationPage(driver);
	}
	
	public String getPageTitleAfterLogin() {
		return driver.getTitle();
	}

	public void closeBrowser() {
		driver.quit();
	}

	@Override
	protected void isLoaded() throws Error {		
	}

	@Override
	protected void load() {
	}

}
