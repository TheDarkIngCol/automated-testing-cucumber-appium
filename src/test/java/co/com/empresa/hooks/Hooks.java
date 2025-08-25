package co.com.empresa.hooks;

import co.com.empresa.utilities.BasePage;
import co.com.empresa.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks extends BasePage {

    @Before
    public void setUp(Scenario scenario) {
        if (scenario.getSourceTagNames().contains("@mobile")) {
            Driver.inicioAppiumDriver();
        } else {
            String sessionName = scenario.getName();
            Driver.inicioWebDriver(sessionName); // idem
        }
    }

    @Given("que estoy en la página de login")
    public void estoyEnLaPaginaDeLogin() {
    }

    @After
    public void tearDown(Scenario scenario) {
        if (!scenario.isFailed())
        {
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
            Driver.cerrarWebDriver();
        }
        else if (scenario.isFailed())
        {
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
            Driver.cerrarWebDriver();
        }
    }
}