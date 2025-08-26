package co.com.empresa.pages.mobile.ios;

import co.com.empresa.utilities.BasePage;
import junit.framework.Assert;

import static co.com.empresa.utilities.Constants.*;

public class LoginPageMobileIOS extends BasePage {

    public LoginPageMobileIOS() {
        super();
    }

    private String usernameField = "//android.widget.EditText[@resource-id=\"com.saucelabs.mydemoapp.android:id/nameET\"]";
    private String passwordField = "//android.widget.EditText[@resource-id=\"com.saucelabs.mydemoapp.android:id/passwordET\"]";
    private String loginButton = "//android.widget.Button[@content-desc=\"Tap to login with given credentials\"]";
    private String textFailed = "//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/passwordErrorTV\"]";

    public void loginSuccessfullMobileIOS() {
        sendKeys(usernameField, USERNAME);
        sendKeys(passwordField, PASS);
        click(loginButton);
    }
}
