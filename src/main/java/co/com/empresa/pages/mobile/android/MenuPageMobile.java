package co.com.empresa.pages.mobile.android;

import co.com.empresa.utilities.BasePage;
import org.openqa.selenium.By;

public class MenuPageMobile extends BasePage {

    public MenuPageMobile() {
        super();
    }

        By menuIcon = By.id("com.saucelabs.mydemoapp.android:id/menuIV") ;
        String logginButton = "//android.widget.TextView[@content-desc=\"Login Menu Item\"]";


    public void menuSuccess() {
        clickBy(menuIcon);
        clickAndScroll(logginButton);
    }
}
