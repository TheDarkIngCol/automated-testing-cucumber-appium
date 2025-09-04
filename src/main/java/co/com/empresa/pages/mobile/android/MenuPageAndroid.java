package co.com.empresa.pages.mobile.android;

import co.com.empresa.utilities.BasePage;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

public class MenuPageAndroid extends BasePage {

    public MenuPageAndroid() {
        super();
    }

        By menuIcon = By.id("com.saucelabs.mydemoapp.android:id/menuIV") ;
        By logginButton = MobileBy.AccessibilityId("Login Menu Item");


    public void menuSuccess() throws InterruptedException {
        clickBy(menuIcon);
        Thread.sleep(2000);
        clickBy(logginButton);
    }
}
