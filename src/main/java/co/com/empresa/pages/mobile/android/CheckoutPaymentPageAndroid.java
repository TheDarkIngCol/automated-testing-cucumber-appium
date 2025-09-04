package co.com.empresa.pages.mobile.android;

import co.com.empresa.utilities.BasePage;

import static co.com.empresa.utilities.Constants.*;

public class CheckoutPaymentPageAndroid extends BasePage {

    public CheckoutPaymentPageAndroid() {
        super();
    }

    private String fullNameInput = "//android.widget.EditText[@resource-id=\"com.saucelabs.mydemoapp.android:id/nameET\"]";
    private String cardNumberInput = "//android.widget.EditText[@resource-id=\"com.saucelabs.mydemoapp.android:id/cardNumberET\"]";
    private String expeditionDateInput = "//android.widget.EditText[@resource-id=\"com.saucelabs.mydemoapp.android:id/expirationDateET\"]";
    private String securityCodeInput = "//android.widget.EditText[@resource-id=\"com.saucelabs.mydemoapp.android:id/securityCodeET\"]";
    private String reviewOrderButton = "//android.widget.Button[@content-desc=\"Saves payment info and launches screen to review checkout data\"]";
    private String placeOrderButton = "//android.widget.Button[@content-desc=\"Completes the process of checkout\"]";

    public void checkoutPayment() {
        sendKeys(fullNameInput, FULL_NAME);
        sendKeys(cardNumberInput, CARD_NUMBER);
        sendkeysAndScroll(expeditionDateInput, EXPEDITION_DATE);
        sendKeys(securityCodeInput,SECURITY_CODE);
        click(reviewOrderButton);
    }

    public void placeOrden() {
        click(placeOrderButton);
    }

}
