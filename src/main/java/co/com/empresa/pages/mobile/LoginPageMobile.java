package co.com.empresa.pages.mobile;

import co.com.empresa.utilities.BasePage;
import org.testng.Assert;

import static co.com.empresa.utilities.Constants.*;

public class LoginPageMobile extends BasePage {

    public LoginPageMobile() {
        super();
    }

    private String usernameField = "//android.widget.EditText[@resource-id=\"com.saucelabs.mydemoapp.android:id/nameET\"]";
    private String passwordField = "//android.widget.EditText[@resource-id=\"com.saucelabs.mydemoapp.android:id/passwordET\"]";
    private String loginButton = "//android.widget.Button[@content-desc=\"Tap to login with given credentials\"]";
    private String textFailed = "//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/passwordErrorTV\"]";

    public void loginSuccessfullMobile() {
        sendKeys(usernameField, USERNAME);
        sendKeys(passwordField, PASS);
        click(loginButton);
    }

    public void loginFailedMobile() {
        sendKeys(usernameField, USERNAME_WRONG);
        sendKeys(passwordField, PASS_WRONG);
        click(loginButton);
    }

    public void errorMessage() {
        String textFail = getText(textFailed);
        Assert.assertEquals(textFail, TEXT_EXPECTED);
        System.out.println("El texto encontrado es: " + textFail);
    }
}
