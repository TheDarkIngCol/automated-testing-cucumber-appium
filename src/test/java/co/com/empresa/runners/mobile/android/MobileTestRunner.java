package co.com.empresa.runners.mobile.android;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/features/mobile/android",
        glue = {"co.com.empresa.steps.mobile.android", "co.com.empresa.hooks"},
        tags = "@E2E",
        plugin = {"pretty",
                "json:target/cucumber-reports/mobile/android/Cucumber.json",
                "html:target/cucumber-html-reports/mobile/android/android-report.html"
        },

        monochrome = true
)
public class MobileTestRunner extends AbstractTestNGCucumberTests {
}
