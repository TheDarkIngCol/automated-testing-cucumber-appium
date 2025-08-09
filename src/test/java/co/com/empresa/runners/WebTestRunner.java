package co.com.empresa.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/features/web",
        glue = {"co.com.empresa.steps", "co.com.empresa.hooks"},
        tags = "@web",
        plugin = {"pretty",
                "html:target/cucumber-reports/web/cucumber.html",
                "json:target/cucumber-reports/web/Cucumber.json"
        },

        monochrome = true
)
public class WebTestRunner extends AbstractTestNGCucumberTests {
}

