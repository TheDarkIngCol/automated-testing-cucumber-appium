package co.com.empresa.hooks;

import co.com.empresa.utilities.BasePage;
import co.com.empresa.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks extends BasePage {

    @Before
    public void setUp(Scenario scenario) {
        String nombreEscenario = scenario.getName();

        if (scenario.getSourceTagNames().contains("@android")) {
            Driver.inicioDriver("android", nombreEscenario);
            System.out.println("📱 Escenario Android iniciado: " + nombreEscenario);
        } else if (scenario.getSourceTagNames().contains("@ios")) {
            Driver.inicioDriver("ios", nombreEscenario);
            System.out.println("🍏 Escenario iOS iniciado: " + nombreEscenario);
        } else if (scenario.getSourceTagNames().contains("@web")) {
            Driver.inicioDriver("web", nombreEscenario);
            System.out.println("💻 Escenario Web iniciado: " + nombreEscenario);
        } else {
            Driver.inicioDriver("web", nombreEscenario);
            System.out.println("💻 Escenario Web iniciado (por defecto): " + nombreEscenario);
        }
    }

    @AfterStep
    public void takeScreenshot(Scenario scenario) {
        try {
            final byte[] screenshot = ((TakesScreenshot) BasePage.driver)
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Step Screenshot");
        } catch (Exception e) {
            System.out.println("⚠️ No se pudo tomar screenshot: " + e.getMessage());
        }
    }


    @Given("que estoy en la página de login")
    public void estoyEnLaPaginaDeLogin() {

    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        } catch (Exception e) {
            System.out.println("No se pudo tomar screenshot: " + e.getMessage());
        }
        Driver.cerrarDriver();
    }

}