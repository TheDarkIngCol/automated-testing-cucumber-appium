package co.com.empresa.steps.web;

import co.com.empresa.pages.web.LoginPage;
import co.com.empresa.utilities.BasePage;
import io.cucumber.java.en.*;

public class LoginSteps extends BasePage {

    LoginPage loginPage = new LoginPage();

    @When("ingreso usuario y contraseña correctos en web")
    public void ingreso_usuario_y_contrasena() {
        loginPage.loginSuccessfull();
    }

    @When("ingreso usuario y contraseña incorrecto en web")
    public void ingreso_usuario_y_contrasena_incorrecto_en_web() {
        loginPage.loginFailed();
    }

    @Then("debe aparecer un mensaje de error en web")
    public void debe_aparecer_un_mensaje_de_error() {
        loginPage.errorMessage();
    }
}
