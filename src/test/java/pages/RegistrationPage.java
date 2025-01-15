package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ConfigReader;
import utils.NameGenerator;

public class RegistrationPage extends LoadableComponent<RegistrationPage> {
	@FindBy (xpath ="//input[@name='customer_info[Customer][Email]']")
	WebElement txtEmail;

	@FindBy (xpath ="//input[@name='customer_info[Customer][Password]']")
	WebElement txtPassword;

	@FindBy (xpath ="//input[@name='customer_info[Customer][RePassword]']")
	WebElement txtRePassword;


	@FindBy (xpath ="//input[@name='customer_info[Customer][FirstName]']")
	WebElement txtFirstName;


	@FindBy (xpath ="//input[@name='customer_info[Customer][LastName]']")
	WebElement txtLastName;

	@FindBy (xpath ="//input[@class='en btn btn-primary btn-register input_submit']")
	WebElement btnSubmit;


	private WebDriver driver;
	private WebDriverWait wait;

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		get();
	}


	public RegistrationPage populateDetails(String email, String pass, String repass, String firstname, String lastname) {
		//txtEmail.sendKeys(email);
		txtEmail.sendKeys(NameGenerator.generateRandomEmail());
		txtPassword.sendKeys(pass);
		txtRePassword.sendKeys(repass);
		txtFirstName.sendKeys(firstname);
		txtLastName.sendKeys(lastname);
		btnSubmit.click();
		return this;
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


	public RegistrationPage populateMandatoryNewDetails() {
		txtEmail.sendKeys(NameGenerator.generateRandomEmail());
		txtPassword.sendKeys(ConfigReader.getProperty("success.password"));
		txtRePassword.sendKeys(ConfigReader.getProperty("success.password"));
		txtFirstName.sendKeys(NameGenerator.getRandomFirstName());
		txtLastName.sendKeys(NameGenerator.getRandomLastName());
		btnSubmit.click();
		return this;		
	}
	
	public RegistrationPage populateMandatoryExistingDetails() {
		txtEmail.sendKeys(ConfigReader.getProperty("error.email"));
		txtPassword.sendKeys(ConfigReader.getProperty("error.password"));
		txtRePassword.sendKeys(ConfigReader.getProperty("error.password"));
		txtFirstName.sendKeys(ConfigReader.getProperty("error.firstname"));
		txtLastName.sendKeys(ConfigReader.getProperty("error.lastname"));
		btnSubmit.click();
		return this;		
	}

}
