package runner;

import org.openqa.selenium.WebDriver;

/*import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.TestNGCucumberRunner;
import io.cucumber.testng.FeatureWrapper;
*/
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/purchase.feature",
        glue = {"steps"},
        //tags = "@Regression",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "rerun:target/cucumber-reports/rerun.txt"
        },
        		snippets = CucumberOptions.SnippetType.CAMELCASE,
        monochrome = true)

public class TestRunner extends AbstractTestNGCucumberTests {
	WebDriver driver;
  
}