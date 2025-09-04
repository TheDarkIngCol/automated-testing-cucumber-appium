package co.com.empresa.pages.web;

import co.com.empresa.utilities.BasePage;
import co.com.empresa.utilities.CommonElements;
import junit.framework.Assert;

import static co.com.empresa.utilities.Constants.TITLE_PRODUCTS;

public class ProductsPageWeb extends BasePage {

    public ProductsPageWeb() {
        super();
    }

    CommonElements elements = new CommonElements();

    private String productLabsBackpack = "//button[@id='add-to-cart-sauce-labs-backpack']";
    private String productLabsBoltTShirt = "//button[@id='add-to-cart-sauce-labs-bolt-t-shirt']";
    private String productLabsOnesie = "//button[@id='add-to-cart-sauce-labs-onesie']";
    private String cartIcon = "//a[@class='shopping_cart_link']";
    private String cartFullIcon = "//span[@class='shopping_cart_badge']";
    private String buttonCheckout = "//button[@id='checkout']";

    public void textScreen() {
        String actualTitle = getText(elements.TitleTextWeb);
        Assert.assertEquals(actualTitle, TITLE_PRODUCTS);
    }

    public void selectProducts() {
        click(productLabsBackpack);
        click(productLabsBoltTShirt);
        click(productLabsOnesie);
        click(cartIcon);
    }

    public void productsInCart() {
        String elementsSelected = getText(cartFullIcon);
        String cantElementsSelected = elementsSelected.trim();
        System.out.println("Elementos seleccionados: " + cantElementsSelected);
    }

    public void proceedToCheckout() {
        click(buttonCheckout);
    }

}
