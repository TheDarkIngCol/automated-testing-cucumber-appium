package co.com.empresa.runners.mobile.ios;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/features/mobile/ios",
        glue = {"co.com.empresa.steps.mobile.ios", "co.com.empresa.hooks"},
        tags = "@ios",
        plugin = {"pretty",
                "json:target/cucumber-reports/mobile/iOS/Cucumber.json",
                "html:target/cucumber-html-reports/mobile/iOS/iOS-report.html"
        },

        monochrome = true
)
public class MobileTestRunnerIOS extends AbstractTestNGCucumberTests {
}
