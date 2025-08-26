package co.com.empresa.steps.mobile.android;

import co.com.empresa.pages.mobile.android.LoginPageMobile;
import co.com.empresa.pages.mobile.android.MenuPageMobile;
import co.com.empresa.utilities.BasePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepsMobile extends BasePage {

    LoginPageMobile loginPageMobile = new LoginPageMobile();
    MenuPageMobile menuPageMobile = new MenuPageMobile();

    @When("ingreso usuario y contrasena correctos en mobile")
    public void ingreso_usuario_y_contrasena() {
        menuPageMobile.menuSuccess();
        loginPageMobile.loginSuccessfullMobile();
    }

    @When("ingreso usuario y contrasena incorrecto en mobile")
    public void ingreso_usuario_y_contrasena_incorrecto() {
        menuPageMobile.menuSuccess();
        loginPageMobile.loginFailedMobile();
    }

    @Then("debe aparecer un mensaje de error")
    public void debe_aparecer_un_mensaje_de_error() {
        loginPageMobile.errorMessage();
    }
}
