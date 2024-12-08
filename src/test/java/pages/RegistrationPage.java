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
		txtEmail.sendKeys(email);
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

}
