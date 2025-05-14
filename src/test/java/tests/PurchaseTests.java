package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.StoreHomePage;
import utils.DataProviders;

public class PurchaseTests extends BaseTest{
	StoreHomePage storeHomePage;


	@Test(dataProvider = "dataFromXLS", dataProviderClass = DataProviders.class)
	public void registerNewUser(String mainmenu,String submenu, String productid,String paymentid, String shipmentid, String moreflag) {

		storeHomePage.goToProductPageUsingMenuandSubMenu(mainmenu,submenu)
				.placeOrder(productid,paymentid,shipmentid,moreflag);;
	}

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod() {
		storeHomePage = new StoreHomePage(driver);

	}


}
