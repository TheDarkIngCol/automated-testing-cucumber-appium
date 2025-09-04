package co.com.empresa.pages.mobile.android;

import co.com.empresa.utilities.BasePage;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MenuPageAndroid extends BasePage {

    public MenuPageAndroid() {
        super();
    }

        By menuIcon = By.id("com.saucelabs.mydemoapp.android:id/menuIV") ;
        By logginButton = MobileBy.AccessibilityId("Login Menu Item");


    public void menuSuccess() throws InterruptedException {
        clickBy(menuIcon);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(logginButton));
        Thread.sleep(500);
        clickBy(logginButton);
    }
}
