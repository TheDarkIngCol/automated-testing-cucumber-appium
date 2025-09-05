package co.com.empresa.utilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.time.Duration;
import java.util.Arrays;

public class BasePage {

    public static WebDriver driver;
    public static WebDriverWait waitDriver;


    public WebElement findVisible(String locator) {
        return waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
    }

    public WebElement findClickable(By locator) {
        return waitDriver.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement findClickable(String locator) {
        return waitDriver.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
    }

    public void click(String locator) {
        findClickable(locator).click();
    }

    public void clickBy(By locator) {
        findClickable(locator).click();
    }

    public void clickVisible(String locator) {
        findVisible(locator).click();
    }

    public void sendKeys(String locator, String data) {
        WebElement element = findVisible(locator);
        element.clear();
        element.sendKeys(data);
    }

    public String getText(String locator) {
        return findVisible(locator).getText();
    }

    public void uploadFile(By locator, String filePath) {
        File file = new File(filePath);
        driver.findElement(locator).sendKeys(file.getAbsolutePath());
    }

    public void scrollToTop() {
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
        } else if (driver instanceof AppiumDriver) {
            swipeVertical(0.2, 0.8);
        }
    }

    public void scrollToElement(String locator) {
        if (driver instanceof AppiumDriver) {
            scrollToElementMobile(locator);
        } else {
            scrollToElementWeb(locator);
        }
    }

    public void clickAndScroll(String locator) {
        if (driver instanceof AppiumDriver) {
            scrollToElementMobile(locator);
            click(locator);
        } else {
            scrollToElementWeb(locator);
            click(locator);
        }
    }

    public void sendkeysAndScroll(String locator, String data) {
        if (driver instanceof AppiumDriver) {
            scrollToElementMobile(locator);
            sendKeys(locator, data);
        } else {
            scrollToElementWeb(locator);
            sendKeys(locator, data);
        }
    }



    private void scrollToElementWeb(String locator) {
        WebElement element = findVisible(locator);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'nearest'});", element);
        sleep(500);
    }

    private void scrollToElementMobile(String locator) {
        AppiumDriver appiumDriver = (AppiumDriver) driver;
        int maxSwipes = 10;
        boolean found = false;

        for (int i = 0; i < maxSwipes; i++) {
            try {
                WebElement el = appiumDriver.findElement(By.xpath(locator));
                if (el.isDisplayed()) {
                    System.out.println("Elemento encontrado en swipe #" + (i + 1));
                    found = true;
                    break;
                }
            } catch (NoSuchElementException ignored) {

            }
            swipeVertical(0.7, 0.3);
            sleep(700);
        }

        if (!found) {
            throw new NoSuchElementException("No se encontró el elemento tras scroll: " + locator);
        }
    }




    public void swipeVertical(double startPercentage, double endPercentage) {
        Dimension size = driver.manage().window().getSize();
        int width = size.width / 2;
        int startPoint = (int) (size.height * startPercentage);
        int endPoint = (int) (size.height * endPercentage);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), width, startPoint));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), width, endPoint));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        ((AppiumDriver) driver).perform(Arrays.asList(swipe));
    }


    public void hoverOverElement(String locator) {
        if (!(driver instanceof AppiumDriver)) {
            org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
            actions.moveToElement(findVisible(locator)).perform();
        }
    }

    public boolean isElementVisible(String locator) {
        try {
            return findVisible(locator).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public boolean isElementEnabled(String locator) {
        try {
            return findVisible(locator).isEnabled();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public boolean waitForElementToDisappear(String locator) {
        return waitDriver.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locator)));
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {}
    }
}
