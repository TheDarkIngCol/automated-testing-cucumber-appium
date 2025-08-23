package co.com.empresa.pages.mobile;

import co.com.empresa.utilities.BasePage;
import co.com.empresa.utilities.CommonElements;
import junit.framework.Assert;

import static co.com.empresa.utilities.Constants.TITLE_PRODUCTS;

public class ProductsPageMobile extends BasePage {

    public ProductsPageMobile() {
        super();
    }

    CommonElements elements = new CommonElements();

    private String productLabsBackpack = "(//android.widget.ImageView[@content-desc=\"Product Image\"])[1]";
    private String productLabsBackpackOrange = "(//android.widget.ImageView[@content-desc=\"Product Image\"])[3]";
    private String cartIcon = "//android.widget.RelativeLayout[@content-desc=\"View cart\"]";
    private String cartFullIcon = "//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/cartTV\"]";
    private String addToCart = "//android.widget.Button[@content-desc=\"Tap to add product to cart\"]";

    public void textScreen() {
        String actualTitle = getText(elements.TitleTextMobile);
        Assert.assertEquals(actualTitle, TITLE_PRODUCTS);
        System.out.println("Ingreso a la pantalla: " + actualTitle);
    }

    public void selectProducts() {
        selectProduct(productLabsBackpack);
        selectProduct(productLabsBackpackOrange);
    }

    private void selectProduct(String locator) {
        clickAndScroll(locator);
        clickAndScroll(addToCart);
        driver.navigate().back();
    }

    public void productsInCart() {
        click(cartIcon);
        String elementsSelected = getText(cartFullIcon);
        String cantElementsSelected = elementsSelected.trim();
        System.out.println("Elementos seleccionados: " + cantElementsSelected);
    }

}
