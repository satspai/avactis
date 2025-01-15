package pages;

import java.time.Duration;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ConfigReader;

public class MyAccountPage extends LoadableComponent<MyAccountPage> {
	@FindBy (xpath ="//button[text()='Register']")
	WebElement registerLink;
	
	@FindBy (id="account_sign_in_form_email_id")
	WebElement userEmail;
	
	@FindBy (id="account_sign_in_form_passwd_id")
	WebElement userPassword;
	
	@FindBy (xpath ="//input[@type='submit']")
	WebElement signIn;
	
	Logger log;
	private WebDriver driver;
	private WebDriverWait wait;

	public MyAccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		log = Logger.getLogger(MyAccountPage.class.getName());
		get();
	}


	public RegistrationPage navigateToRegisterPage() {
		log.info("Clicking the registration link");
		registerLink.click();
		log.info("After registeration click");
		return new RegistrationPage(driver);
	}
	
	public StoreHomePage validLogin() {
		userEmail.sendKeys(ConfigReader.getProperty("success.email"));
		userPassword.sendKeys(ConfigReader.getProperty("success.password"));
		signIn.click();
		return new StoreHomePage(driver);
	}

	public StoreHomePage invalidLogin() {
		userEmail.sendKeys(ConfigReader.getProperty("error.email"));
		userPassword.sendKeys(ConfigReader.getProperty("error.password"));
		signIn.click();
		return new StoreHomePage(driver);
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
