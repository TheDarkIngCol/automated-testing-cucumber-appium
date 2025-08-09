package co.com.empresa.steps.mobile;

import co.com.empresa.pages.mobile.ProductsPageMobile;
import co.com.empresa.pages.web.ProductsPageWeb;
import co.com.empresa.utilities.BasePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductStepsMobile extends BasePage {

    ProductsPageMobile products = new ProductsPageMobile();

    @And("estoy en la pagina de productos")
    public void estoy_en_la_pagina_principal() {
        products.textScreen();
    }

    @When("selecciono un producto y lo agrego al carrito en mobile")
    public void selecciono_un_producto_y_lo_agrego_al_carrito() {
        products.selectProducts();
    }

    @Then("el producto aparece en el carrito en mobile")
    public void el_producto_aparece_en_el_carrito() {
        products.productsInCart();
    }

}
