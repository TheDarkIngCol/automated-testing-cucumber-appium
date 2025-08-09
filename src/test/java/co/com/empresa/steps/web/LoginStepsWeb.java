package co.com.empresa.steps.web;

import co.com.empresa.pages.web.LoginPageWeb;
import co.com.empresa.utilities.BasePage;
import io.cucumber.java.en.*;

public class LoginStepsWeb extends BasePage {

    LoginPageWeb loginPageWeb = new LoginPageWeb();

    @When("ingreso usuario y contraseña correctos en web")
    public void ingreso_usuario_y_contrasena() {
        loginPageWeb.loginSuccessfull();
    }

    @When("ingreso usuario y contraseña incorrecto en web")
    public void ingreso_usuario_y_contrasena_incorrecto_en_web() {
        loginPageWeb.loginFailed();
    }

    @Then("debe aparecer un mensaje de error en web")
    public void debe_aparecer_un_mensaje_de_error() {
        loginPageWeb.errorMessage();
    }
}
