package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.StoreHomePage;
import utils.DataProviders;

public class RegistrationTests extends BaseTest{
	StoreHomePage storeHomePage;
	
	//@Test
	public void navigateToSignInPage() {
		storeHomePage.naviagetToSignInPage();
	}
	
	
	@Test(dataProvider = "dataFromXLS", dataProviderClass = DataProviders.class)
	public void registerNewUser(String email,String pass, String repass, String fristName, String lastName) {
		System.out.println(email +"  "+  pass);
		storeHomePage.navigateToMyAccountPage()
					.navigateToRegisterPage()
					.populateDetails(email, pass, repass, fristName, lastName);
	}

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod() {
		storeHomePage = new StoreHomePage(driver);
		
	}


}
