package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import utils.NameGenerator;
import utils.WaitTool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckoutPage extends LoadableComponent<CheckoutPage> {
	@FindBy (xpath ="//a[@href='cart.php']")
	WebElement link_myCart;

	@FindBy (xpath = "//a[text()='Continue Shopping']/following-sibling::a[1]")
	WebElement link_shoppingCart_checkOut;

	@FindBy (xpath = "//input[@name='billingInfo[Firstname]']")
	WebElement txtBox_FirstName;

	@FindBy (xpath = "//input[@name='billingInfo[Lastname]']")
	WebElement txtBox_LastName;

	@FindBy (xpath = "//input[@name='billingInfo[Email]']")
	WebElement txtBox_Email;

	@FindBy (xpath = "//input[@name='billingInfo[Postcode]']")
	WebElement txtBox_Postcode;

	@FindBy (xpath = "//input[@name='billingInfo[City]']")
	WebElement txtBox_City;

	@FindBy (xpath = "//*[@value='Continue Checkout'][contains(@onclick,'1')]")
	WebElement link_billingAndShippingAddress_CheckOut;

	@FindBy (xpath = "//*[@name='checkbox_shipping_same_as_billing']")
	WebElement chk_billingAddressSameAsShipping;

	@FindBy (xpath = "//div[@class='checkout_buttons']/input[contains(@onclick,'submitStep(2)')]")
	WebElement link_shippingAndPaymentMethod_Checkout;

	Logger log;
	private WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		log = Logger.getLogger(CheckoutPage.class.getName());
		get();
	}

	public ArrayList<List<String>> checkout_shoppingCart() {
		ArrayList<List<String>> myCartItemDetails = new ArrayList<List<String>>();
		List<WebElement> tableElement = new ArrayList<WebElement>();
		tableElement = driver.findElements(By.xpath("//tr"));
		for (int i = 2; i <= tableElement.size(); i++) {
			String item = driver.findElement(By.xpath("//table[@summary='Shopping cart']//tr[" +i+ "]//td[2]")).getText();
			String quantity = driver.findElement(By.xpath("//table[@summary='Shopping cart']//tr[" +i+ "]//td[3]//option[@selected='selected']")).getText();
			String price = driver.findElement(By.xpath("//table[@summary='Shopping cart']//tr[" +i+ "]//td[4]")).getText();
			String total = driver.findElement(By.xpath("//table[@summary='Shopping cart']//tr[" +i+ "]//td[5]")).getText();
			myCartItemDetails.add(Arrays.asList(item,quantity,price,total));

		}

		log.info("Expected cart details" + myCartItemDetails);
		link_shoppingCart_checkOut.click();
		return  myCartItemDetails;
	}

	public void checkout_billingAndShippingAddress() {
		txtBox_FirstName.sendKeys(NameGenerator.getRandomFirstName());
		txtBox_LastName.sendKeys(NameGenerator.getRandomLastName());
		txtBox_Email.sendKeys(NameGenerator.generateRandomEmail());
		txtBox_City.sendKeys(NameGenerator.getRandomCity());
		txtBox_Postcode.sendKeys(NameGenerator.getRandomPostcode());
		chk_billingAddressSameAsShipping.click();
		WaitTool.waitForElementPresent(link_billingAndShippingAddress_CheckOut,50,100);

		link_billingAndShippingAddress_CheckOut.click();

	}

	public void checkout_billingAndShippingMethod(String paymentMethod, String shipmentMethod) {
		List<WebElement> shippingMethods = driver.findElements(By.xpath("//div[@class='shipping_method_list_row']/label"));
		for (WebElement shippingMethod : shippingMethods) {
			if (shippingMethod.getText().equalsIgnoreCase(shipmentMethod)) {
				shippingMethod.click();
				log.info("Shipping method " + shipmentMethod + "is clicked" );
			}
		}
		WaitTool.waitForElementPresent(link_shippingAndPaymentMethod_Checkout,50,100);

		link_shippingAndPaymentMethod_Checkout.click();
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
