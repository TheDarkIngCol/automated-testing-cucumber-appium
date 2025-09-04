package co.com.empresa.steps.web;

import co.com.empresa.pages.web.CheckoutInformationPageWeb;
import co.com.empresa.pages.web.ProductsPageWeb;
import co.com.empresa.utilities.BasePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductStepsWeb extends BasePage {

    ProductsPageWeb products = new ProductsPageWeb();
    CheckoutInformationPageWeb checkout = new CheckoutInformationPageWeb();

    @Given("estoy en la pagina principal")
    public void estoy_en_la_pagina_principal() {
        products.textScreen();
    }

    @When("selecciono varios producto y lo agrego al carrito")
    public void selecciono_varios_producto_y_lo_agrego_al_carrito() {
        products.selectProducts();
    }

    @Then("el producto aparece en el carrito")
    public void el_producto_aparece_en_el_carrito() {
        products.productsInCart();
        products.proceedToCheckout();
    }

    @And("procedo a realizar el checkout")
    public void procedo_a_realizar_el_checkout() {
        checkout.fillCheckoutInformation();
    }

}
