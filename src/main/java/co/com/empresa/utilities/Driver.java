package co.com.empresa.utilities;

import io.appium.java_client.android.options.UiAutomator2Options;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.android.AndroidDriver;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

import static co.com.empresa.utilities.Constants.URL;

public class Driver extends BasePage {

    public static final String BROWSERSTACK_USER = System.getenv("BROWSERSTACK_USER");
    public static final String BROWSERSTACK_KEY = System.getenv("BROWSERSTACK_KEY");
    public static final String BROWSERSTACK_URL = "https://" + BROWSERSTACK_USER + ":" + BROWSERSTACK_KEY + "@hub-cloud.browserstack.com/wd/hub";

    public static void inicioWebDriver(String sessionName) {
        driver = createRemoteWebDriver(sessionName);
        driver.manage().window().maximize();
        driver.get(URL);
        waitDriver = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public static void inicioWebDriverLocal() {
        ChromeOptions options = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        options.addArguments("start-maximized", "--disable-notifications", "--incognito", "--disable-popup-blocking");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(URL);
        waitDriver = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    private static RemoteWebDriver createRemoteWebDriver(String sessionName) {
        if (sessionName == null || sessionName.trim().isEmpty()) {
            throw new IllegalArgumentException("Session name must not be null or empty.");
        }

        HashMap<String, Object> bstackOptions = new HashMap<>();
        bstackOptions.put("sessionName", sessionName);
        bstackOptions.put("projectName", "Testing");
        bstackOptions.put("buildName", "Prueba_ValDispositivos");
        bstackOptions.put("local", "false");
        bstackOptions.put("seleniumVersion", "4.8.0");

        MutableCapabilities capabilities = new MutableCapabilities();
        capabilities.setCapability("bstack:options", bstackOptions);
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("browserVersion", "latest");

        HashMap<String, Object> chromeOptions = new HashMap<>();
        chromeOptions.put("args", new String[]{"--incognito"});
        capabilities.setCapability("goog:chromeOptions", chromeOptions);

        try {
            RemoteWebDriver driver = new RemoteWebDriver(new URI(BROWSERSTACK_URL).toURL(), capabilities);
            DriverManager.setDriver(driver);
            return driver;
        } catch (URISyntaxException | MalformedURLException e) {
            throw new IllegalArgumentException("Error al conectar con BrowserStack: " + BROWSERSTACK_URL, e);
        }
    }

    public static void inicioAppiumDriver(boolean useBrowserStack) {
        try {
            UiAutomator2Options options = new UiAutomator2Options()
                    .setPlatformName("Android")
                    .setPlatformVersion("12.0")
                    .setAutomationName("UiAutomator2")
                    .setFullReset(true)
                    .autoGrantPermissions();

            if (useBrowserStack) {
                options.setDeviceName("Samsung Galaxy S22 Ultra")
                        .setApp("bs://62f8fbe1955d3ecea2cd41c405e9214d858c62a1");
                driver = new AndroidDriver(new URL(BROWSERSTACK_URL), options);
                System.out.println("Driver Mobile BrowserStack iniciado correctamente");
            } else {
                String apkPath = System.getProperty("user.dir") + "/src/test/java/resources/apps/mda-2.2.0-25.apk";
                File apkFile = new File(apkPath);
                if (!apkFile.exists()) {
                    throw new RuntimeException("APK no encontrado en la ruta: " + apkPath);
                }
                options.setDeviceName("Huawei")
                        .setUdid("L4SDU17927002305")
                        .setApp(apkPath)
                        .setNoReset(false);
                driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), options);
                System.out.println("Driver Mobile local iniciado correctamente");
            }

            waitDriver = new WebDriverWait(driver, Duration.ofSeconds(30));
            System.out.println("Capabilities: " + options.asMap());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cerrarWebDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
