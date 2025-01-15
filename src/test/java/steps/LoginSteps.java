package steps;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.MyAccountPage;
import pages.StoreHomePage;
import utils.DriverManager;

public class LoginSteps {
	WebDriver driver;
	StoreHomePage storeHomePage;
	@And("^user logs in with valid credentials$")
	public void user_login_valid() throws Throwable {
		driver = DriverManager.getDriver();	
		MyAccountPage myAccountPage = new MyAccountPage(driver);
		storeHomePage = myAccountPage.validLogin();
		
		System.out.println(storeHomePage.checkLogin());
		
	}
	@And("^user logs in with invalid credentials$")
	public void user_login_invalid() throws Throwable {
		driver = DriverManager.getDriver();
		MyAccountPage myAccountPage = new MyAccountPage(driver);
		storeHomePage = myAccountPage.invalidLogin();

		System.out.println(storeHomePage.checkLogin());

	}

	@Then("^user should see landing page$")
	public void confirm_login() throws Throwable {
		WebElement successMessage = driver.findElement(By.xpath("//h1[text()='My Account  ']"));

		assertTrue(successMessage.isDisplayed(),"Success message not displayed");
		DriverManager.quitDriver();
	}

	@Then("^user should see the login error message$")
	public void confirm_login_error() throws Throwable {
		WebElement successMessage = driver.findElement(By.xpath("//div[text()='Account and password could not be identified. Try again or create an account.']"));

		assertTrue(successMessage.isDisplayed(),"Error message not displayed");
		DriverManager.quitDriver();
	}
}
