package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;
import utils.WaitTool;

import java.time.Duration;

public class ProductPage extends LoadableComponent<ProductPage> {


	Logger log;
	private WebDriver driver;

	public ProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		log = Logger.getLogger(ProductPage.class.getName());
		get();
	}

	public void placeOrder(String productId, String paymentId , String shipmentId, String moreFlag) {
		if (addToCart(productId)) {
			log.info("Add to Cart is successful");
		}
		else {
			log.info("Add to cart failed");
		}
	}

	public boolean addToCart(String productId) {
		String addToCartButtonForGivenProduct = "ProductForm_" + productId;
		log.info("Searching for product Id " + productId);

		WaitTool.waitForElementPresent(driver.findElement(By.id(addToCartButtonForGivenProduct)), 100,100);
		WebElement addToCartButton;

		try {
			addToCartButton = driver.findElement(By.xpath("//*[@id='"+addToCartButtonForGivenProduct+"']/descendant::input[@value='Add To Cart']"));
			addToCartButton.click();
			log.info(productId +" got added to cart");
		}
		catch (NoSuchElementException e) {
			log.error("Add to Cart button not found");
			return false;
		}

		return true;
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
