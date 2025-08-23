package co.com.empresa.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/features/mobile",
        glue = {"co.com.empresa.steps", "co.com.empresa.hooks"},
        tags = "@mobile",
        plugin = {"pretty",
                "json:target/cucumber-reports/mobile/Cucumber.json"
        },

        monochrome = true
)
public class MobileTestRunner extends AbstractTestNGCucumberTests {
}
