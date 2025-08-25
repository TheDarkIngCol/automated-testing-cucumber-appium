package co.com.empresa.utilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

import static co.com.empresa.utilities.Constants.URL;

public class Driver extends BasePage {

    private static final String BROWSERSTACK_USER = System.getenv("BROWSERSTACK_USER");
    private static final String BROWSERSTACK_KEY = System.getenv("BROWSERSTACK_KEY");
    private static final String BROWSERSTACK_URL = "https://" + BROWSERSTACK_USER + ":" + BROWSERSTACK_KEY + "@hub-cloud.browserstack.com/wd/hub";

    /** Devuelve si se debe usar BrowserStack según parámetro Maven */
    public static boolean isBrowserStack() {
        return Boolean.parseBoolean(System.getProperty("browserstack", "false"));
    }

    /** Inicio de WebDriver */
    public static void inicioWebDriver(String sessionName) {
        if (isBrowserStack()) {
            driver = createRemoteWebDriver(sessionName);
            System.out.println("Driver Web BrowserStack iniciado correctamente");
        } else {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized", "--disable-notifications", "--incognito", "--disable-popup-blocking");
            driver = new ChromeDriver(options);
            System.out.println("Driver Web local iniciado correctamente");
        }

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
        } catch (Exception e) {
            throw new RuntimeException("Error al conectar con BrowserStack: " + BROWSERSTACK_URL, e);
        }
    }

    /** Inicio de Appium Driver totalmente dinámico */
    public static void inicioAppiumDriver() {
        try {
            UiAutomator2Options options = new UiAutomator2Options()
                    .setPlatformName("Android")
                    .setAutomationName("UiAutomator2")
                    .autoGrantPermissions();

            if (isBrowserStack()) {
                options.setDeviceName("Samsung Galaxy S23 Ultra")
                        .setPlatformVersion("13.0")
                        .setApp("bs://62f8fbe1955d3ecea2cd41c405e9214d858c62a1");
                driver = new AndroidDriver(new URL(BROWSERSTACK_URL), options);
                System.out.println("Driver Mobile BrowserStack iniciado correctamente");
            } else {
                DeviceInfo device = obtenerPrimerDispositivoConectado();
                if (device == null) throw new RuntimeException("No se encontró ningún dispositivo Android conectado.");

                options.setUdid(device.udid)
                        .setDeviceName(device.model)
                        .setPlatformVersion(device.version)
                        .setAppPackage("com.saucelabs.mydemoapp.android")
                        .setAppActivity("com.saucelabs.mydemoapp.android.view.activities.SplashActivity")
                        .setNoReset(false);

                driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), options);
                System.out.println("Driver Mobile local iniciado correctamente en dispositivo: " + device);
            }

            waitDriver = new WebDriverWait(driver, Duration.ofSeconds(30));
            System.out.println("Capabilities: " + options.asMap());

        } catch (Exception e) {
            throw new RuntimeException("Error al iniciar Appium Driver: " + e.getMessage(), e);
        }
    }

    /** Cierra el WebDriver */
    public static void cerrarWebDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            waitDriver = null;
        }
    }

    /** Clase para guardar info del dispositivo */
    private static class DeviceInfo {
        String udid;
        String model;
        String version;

        DeviceInfo(String udid, String model, String version) {
            this.udid = udid;
            this.model = model;
            this.version = version;
        }

        @Override
        public String toString() {
            return model + " (UDID: " + udid + ", Android " + version + ")";
        }
    }

    /** Detecta el primer dispositivo conectado y obtiene su información */
    private static DeviceInfo obtenerPrimerDispositivoConectado() {
        try {
            Process process = Runtime.getRuntime().exec("adb devices");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.endsWith("device") && !line.startsWith("List")) {
                    String udid = line.split("\t")[0];
                    String model = ejecutarComandoADB("adb -s " + udid + " shell getprop ro.product.model").trim();
                    String version = ejecutarComandoADB("adb -s " + udid + " shell getprop ro.build.version.release").trim();
                    return new DeviceInfo(udid, model, version);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /** Ejecuta un comando ADB y devuelve el resultado */
    private static String ejecutarComandoADB(String comando) {
        try {
            Process process = Runtime.getRuntime().exec(comando);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line);
            }
            process.waitFor();
            return output.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
