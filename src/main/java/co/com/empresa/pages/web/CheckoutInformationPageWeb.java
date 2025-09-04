package co.com.empresa.pages.web;

import co.com.empresa.utilities.BasePage;

import static co.com.empresa.utilities.Constants.*;

public class CheckoutInformationPageWeb extends BasePage {

    public CheckoutInformationPageWeb() {
        super();
    }

    private String firstNameField = "//input[@id='first-name']";
    private String lastNameField = "//input[@id='last-name']";
    private String postalCodeField = "//input[@id='postal-code']";
    private String continueButton = "//input[@id='continue']";
    private String finishButton = "//button[@id='finish']";

    public void fillCheckoutInformation() {
        sendKeys(firstNameField, FIRST_NAME);
        sendKeys(lastNameField, LAST_NAME);
        sendKeys(postalCodeField, ZIP_CODE);
        click(continueButton);
        click(finishButton);
    }
}
