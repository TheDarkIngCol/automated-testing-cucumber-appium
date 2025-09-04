package co.com.empresa.steps.mobile.ios;

import co.com.empresa.pages.mobile.ios.MenuPageIOS;
import co.com.empresa.utilities.BasePage;
import io.cucumber.java.en.When;

public class LoginStepsMobileIOS extends BasePage {

    MenuPageIOS menuPageIOS = new MenuPageIOS();

    @When("ingreso usuario y contraseña correctos en IOS")
    public void ingreso_usuario_y_contraseña_correctos_en_mobile_ios() {
        menuPageIOS.menuSuccessIOS();
    }
}
