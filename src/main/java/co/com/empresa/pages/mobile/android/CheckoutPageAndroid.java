package co.com.empresa.pages.mobile.android;

import co.com.empresa.utilities.BasePage;

import static co.com.empresa.utilities.Constants.*;

public class CheckoutPageAndroid extends BasePage {

    public CheckoutPageAndroid() {
        super();
    }

    private String fullNameField = "//android.widget.EditText[@resource-id=\"com.saucelabs.mydemoapp.android:id/fullNameET\"]";
    private String adressLineOneField = "//android.widget.EditText[@resource-id=\"com.saucelabs.mydemoapp.android:id/address1ET\"]";
    private String adressLineTwoField = "//android.widget.EditText[@resource-id=\"com.saucelabs.mydemoapp.android:id/address2ET\"]";
    private String cityField = "//android.widget.EditText[@resource-id=\"com.saucelabs.mydemoapp.android:id/cityET\"]";
    private String stateField = "//android.widget.EditText[@resource-id=\"com.saucelabs.mydemoapp.android:id/stateET\"]";
    private String zipCodeField = "//android.widget.EditText[@resource-id=\"com.saucelabs.mydemoapp.android:id/zipET\"]";
    private String countryField = "//android.widget.EditText[@resource-id=\"com.saucelabs.mydemoapp.android:id/countryET\"]";
    private String toPaymentField = "//android.widget.Button[@content-desc=\"Saves user info for checkout\"]";

    public void fillCheckoutInformation() {
        sendKeys(fullNameField, FULL_NAME);
        sendKeys(adressLineOneField, ADRESS_1);
        sendKeys(adressLineTwoField, ADRESS_2);
        sendKeys(cityField, CITY);
        sendKeys(stateField, STATE);
        sendkeysAndScroll(zipCodeField, ZIP_CODE);
        sendKeys(countryField, COUNTRY);
        click(toPaymentField);
    }
}
