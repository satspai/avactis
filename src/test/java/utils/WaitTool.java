package utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitTool {
    public static void  waitForElementPresent(WebElement element, int timeOutInSeconds, int pollingTime) {

        try {
            //Browser.driver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); //nullify implicitlyWait()
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOutInSeconds),Duration.ofSeconds(pollingTime));
            element = wait.until(ExpectedConditions.visibilityOf(element));
            //return true; //return the element
        } catch (Exception e) {
            e.getMessage();
            //return false;
        }
    }
}
