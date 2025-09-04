package co.com.empresa.steps.mobile.android;

import co.com.empresa.pages.mobile.android.CheckoutPageAndroid;
import co.com.empresa.pages.mobile.android.CheckoutPaymentPageAndroid;
import co.com.empresa.pages.mobile.android.ProductsPageAndroid;
import co.com.empresa.utilities.BasePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductStepsMobile extends BasePage {

    ProductsPageAndroid products = new ProductsPageAndroid();
    CheckoutPageAndroid checkout = new CheckoutPageAndroid();
    CheckoutPaymentPageAndroid payment = new CheckoutPaymentPageAndroid();

    @And("estoy en la pagina de productos")
    public void estoy_en_la_pagina_principal() {
        products.textScreen();
    }

    @When("selecciono un producto y lo agrego al carrito en mobile")
    public void selecciono_un_producto_y_lo_agrego_al_carrito() {
        products.selectProducts();
    }

    @And("el producto aparece en el carrito en mobile")
    public void el_producto_aparece_en_el_carrito() {
        products.productsInCart();
        products.checkOutApp();
    }

    @And("procedo a realizar el checkout en mobile")
    public void procedo_a_realizar_el_checkout_en_mobile() {
        checkout.fillCheckoutInformation();
    }

    @And("agrego los metodos de pago")
    public void agrego_los_metodos_de_pago() {
        payment.checkoutPayment();
    }

    @Then("confirmo la orden")
    public void confirmo_la_orden() {
        payment.placeOrden();
    }
}
