package co.com.empresa.pages.mobile.ios;

import co.com.empresa.utilities.BasePage;

public class LoginPageIOS extends BasePage {

    public LoginPageIOS() {
        super();
    }

    private String usernameField = "//XCUIElementTypeStaticText[@name=\"bob@example.com\"]";
    private String loginButton = "//XCUIElementTypeButton[@name=\"Login\"]";

    public void loginSuccessfullMobileIOS() {
        click(usernameField);
        click(loginButton);
    }
}
