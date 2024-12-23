package utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverManager {
	private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
	
	public static void InitializeWebdriver() {
		if (driverThreadLocal.get()!=null) {
			throw new IllegalStateException("Driver is already initialised");
		}
		WebDriver driver;
		String browser = ConfigReader.getProperty("browser");
		switch (browser.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
        case "edge":
            driver = new EdgeDriver();
            break;
        case "safari":
            driver = new SafariDriver();
            break;
        default:
            throw new IllegalArgumentException("Unsupported browser: " + browser);
		}
		
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	     driver.manage().window().maximize();
	     driverThreadLocal.set(driver);
	     driver.get(ConfigReader.getProperty("baseUrl"));
	}
	
	/**
     * Returns the current WebDriver instance.
     * 
     * @return the current WebDriver instance
     */
    public static WebDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            throw new IllegalStateException("Driver is not initialized. Call initializeDriver() first.");
        }
        return driverThreadLocal.get();
    }
    
    /**
     * Quits the WebDriver instance and removes it from the ThreadLocal storage.
     */
    public static void quitDriver() {
        if (driverThreadLocal.get() != null) {
            driverThreadLocal.get().quit();
            driverThreadLocal.remove();
        }
    }
}
