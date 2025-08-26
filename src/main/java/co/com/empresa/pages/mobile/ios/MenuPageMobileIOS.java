package co.com.empresa.pages.mobile.ios;

import co.com.empresa.utilities.BasePage;
import org.openqa.selenium.By;

public class MenuPageMobileIOS extends BasePage {

    public MenuPageMobileIOS() {
        super();
    }

        By menuIcon = By.id("More-tab-item") ;
        String logginButton = "//android.widget.TextView[@content-desc=\"Login Menu Item\"]";


    public void menuSuccessIOS() {
        clickBy(menuIcon);
        //clickAndScroll(logginButton);
    }
}
