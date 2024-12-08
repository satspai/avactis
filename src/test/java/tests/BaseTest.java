package tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
	protected WebDriver driver;
	protected WebDriverWait wait;
	private String url = "http://localhost/Avactis/";
	// set up method to initialise the web driver
	@BeforeClass
	public void setUp() {
		driver = new ChromeDriver();
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get(url);
	}
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
