package steps;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
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
		storeHomePage.naviagetToSignInPage();
	}

	@Then("^user is on sign in page$")
	public void then() throws Throwable {
	}

}
