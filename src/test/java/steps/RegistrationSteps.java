package steps;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.StoreHomePage;
import utils.DriverManager;

public class RegistrationSteps {
	WebDriver driver;

	@Given("^user is on store home page$")
	public void navigate_to_signin() throws Throwable {
		DriverManager.InitializeWebdriver();
		driver = DriverManager.getDriver();	
	}

	@When("^user navigates to signin page$")
	public void navigate_to_register_page() throws Throwable {
		StoreHomePage storeHomePage = new StoreHomePage(driver);
		storeHomePage.goToSignInPage();
	}
	
	@When("^user navigates to registration page and registers with new data$")
	public void Do_registeration_new_data() throws Throwable {
		StoreHomePage storeHomePage = new StoreHomePage(driver);
		storeHomePage.goToMyAccountPage()
		.navigateToRegisterPage()
		.populateMandatoryNewDetails();
	}
	
	@When("^user navigates to registration page and registers with existing data$")
	public void Do_registeration_existing_data() throws Throwable {
		StoreHomePage storeHomePage = new StoreHomePage(driver);
		storeHomePage.goToMyAccountPage()
		.navigateToRegisterPage()
		.populateMandatoryExistingDetails();
	}

	@Then("^user is on sign in page$")
	public void confirm_navigation_signin() throws Throwable {
		String expectedURL = "http://localhost/Avactis/sign-in.php";
		String actualURL = driver.getCurrentUrl();
		
		assertEquals("User did not navigate to sign in page", expectedURL, actualURL);
		DriverManager.quitDriver();
	}
	
	
	@Then("^user should see the registration confirmation message$")
	public void confirm_registration() throws Throwable {
		WebElement successMessage = driver.findElement(By.xpath("//div[text()='Account created successfully. You are now registered.']"));
		
		assertTrue(successMessage.isDisplayed(),"Success message not displayed");
		DriverManager.quitDriver();
	}
	
	@Then("^user should see the registration error message$")
	public void confirm_registration_error() throws Throwable {
		WebElement successMessage = driver.findElement(By.xpath("//div[text()='This account name is already taken. Please choose a different account name.']"));
		
		assertTrue(successMessage.isDisplayed(),"Error message not displayed");
		DriverManager.quitDriver();
	}
	
	
}
