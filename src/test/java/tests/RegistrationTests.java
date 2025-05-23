package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.StoreHomePage;
import utils.DataProviders;

public class RegistrationTests extends BaseTest{
	StoreHomePage storeHomePage;

	//@Test
	public void navigateToSignInPage() {
		storeHomePage.goToSignInPage();

	}


	@Test(dataProvider = "dataFromXLS", dataProviderClass = DataProviders.class)
	public void registerNewUser(String email,String pass, String repass, String firstName, String lastName) {
		storeHomePage.goToMyAccountPage()
					.navigateToRegisterPage()
					.populateDetails(email, pass, repass, firstName, lastName);
	}

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod() {
		storeHomePage = new StoreHomePage(driver);

	}


}
