package co.com.empresa.pages.mobile.android;

import co.com.empresa.utilities.BasePage;
import co.com.empresa.utilities.CommonElements;
import junit.framework.Assert;
import org.openqa.selenium.By;

import static co.com.empresa.utilities.Constants.TITLE_PRODUCTS;

public class ProductsPageAndroid extends BasePage {

    public ProductsPageAndroid() {
        super();
    }

    CommonElements elements = new CommonElements();

    private String productLabsBackpack = "(//android.widget.ImageView[@content-desc=\"Product Image\"])[1]";
    private String productLabsBackpackOrange = "(//android.widget.ImageView[@content-desc=\"Product Image\"])[3]";
    private String cartIcon = "//android.widget.RelativeLayout[@content-desc=\"View cart\"]";
    private String cartFullIcon = "//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/cartTV\"]";
    private String addToCart = "//android.widget.Button[@content-desc=\"Tap to add product to cart\"]";
    private By checkoutButton = By.id("com.saucelabs.mydemoapp.android:id/cartBt");

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

    public void checkOutApp() {
        clickBy(checkoutButton);
    }

}
