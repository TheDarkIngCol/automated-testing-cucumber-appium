package co.com.empresa.steps.mobile.android;

import co.com.empresa.pages.mobile.android.LoginPageAndroid;
import co.com.empresa.pages.mobile.android.MenuPageAndroid;
import co.com.empresa.utilities.BasePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepsMobile extends BasePage {

    LoginPageAndroid loginPageAndroid = new LoginPageAndroid();
    MenuPageAndroid menuPageAndroid = new MenuPageAndroid();

    @When("ingreso usuario y contrasena correctos en mobile")
    public void ingreso_usuario_y_contrasena() {
        menuPageAndroid.menuSuccess();
        loginPageAndroid.loginSuccessfullMobile();
    }

    @When("ingreso usuario y contrasena incorrecto en mobile")
    public void ingreso_usuario_y_contrasena_incorrecto() {
        menuPageAndroid.menuSuccess();
        loginPageAndroid.loginFailedMobile();
    }

    @Then("debe aparecer un mensaje de error")
    public void debe_aparecer_un_mensaje_de_error() {
        loginPageAndroid.errorMessage();
    }
}
