package co.com.empresa.runners.web;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/features/web",
        glue = {"co.com.empresa.steps.web", "co.com.empresa.hooks"},
        tags = "@web",
        plugin = {"pretty",
                "json:target/cucumber-reports/web/Cucumber.json",
                "html:target/cucumber-html-reports/web/web-report.html"
        },

        monochrome = true
)
public class WebTestRunner extends AbstractTestNGCucumberTests {
}

