package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import pages.ProductPage;
import pages.StoreHomePage;
import utils.DriverManager;

import static org.testng.Assert.assertEquals;

public class PurchaseSteps {
    WebDriver driver;
    @Then("^Guest user is on Avactis store home page$")
    public void navigation_homepage() throws Throwable {
        DriverManager.InitializeWebdriver();

    }

    @And("^user selects 3 products from various product categories$")
    public void add_products_to_cart() throws  Throwable {
        driver = DriverManager.getDriver();
        StoreHomePage storeHomePage = new StoreHomePage(driver);
        storeHomePage.goToProductPageUsingMenuandSubMenu("Computers","Desktops")
                .placeOrder("31","","","");;
        //DriverManager.quitDriver();
    }
}
