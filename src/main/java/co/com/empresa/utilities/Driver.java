package co.com.empresa.utilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static co.com.empresa.utilities.Constants.URL;

public class Driver extends BasePage {

    public static final String BROWSERSTACK_USER = System.getenv("BROWSERSTACK_USER");
    public static final String BROWSERSTACK_KEY = System.getenv("BROWSERSTACK_KEY");
    public static final String BROWSERSTACK_URL = "https://" + BROWSERSTACK_USER + ":" + BROWSERSTACK_KEY + "@hub-cloud.browserstack.com/wd/hub";

    public static void inicioWebDriver(String sessionName, boolean useBrowserStack) {
        if (useBrowserStack) {
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
        } catch (URISyntaxException | MalformedURLException e) {
            throw new IllegalArgumentException("Error al conectar con BrowserStack: " + BROWSERSTACK_URL, e);
        }
    }

    public static void inicioAppiumDriver(boolean useBrowserStack, String deviceName, String appPath, int deviceIndex) {
        try {
            UiAutomator2Options options = new UiAutomator2Options()
                    .setPlatformName("Android")
                    .setAutomationName("UiAutomator2")
                    .setApp(appPath)
                    .autoGrantPermissions();

            if (useBrowserStack) {
                if (deviceName == null || deviceName.isEmpty() || appPath == null || appPath.isEmpty()) {
                    throw new IllegalArgumentException("Para BrowserStack debe especificar deviceName y appPath");
                }
                options.setDeviceName(deviceName)
                        .setPlatformVersion("12.0")
                        .setApp(appPath)
                        .setFullReset(true);
                driver = new AndroidDriver(new URL(BROWSERSTACK_URL), options);
                System.out.println("Driver Mobile BrowserStack iniciado correctamente");
            } else {
                List<DeviceInfo> devices = getConnectedDevices();
                if (devices.isEmpty()) {
                    throw new RuntimeException("No se encontró ningún dispositivo Android conectado");
                }

                if (deviceIndex >= devices.size()) {
                    throw new IllegalArgumentException("deviceIndex fuera de rango, dispositivos conectados: " + devices.size());
                }

                DeviceInfo device = devices.get(deviceIndex);
                File apkFile = new File(appPath);
                if (!apkFile.exists()) {
                    throw new RuntimeException("APK no encontrado en la ruta: " + appPath);
                }

                options.setDeviceName(device.model)
                        .setPlatformVersion(device.androidVersion)
                        .setUdid(device.udid)
                        .setApp(appPath)
                        .setNoReset(false);

                driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), options);
                System.out.println("Driver Mobile local iniciado correctamente: " + device);
            }

            waitDriver = new WebDriverWait(driver, Duration.ofSeconds(30));

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al iniciar Appium Driver: " + e.getMessage(), e);
        }
    }

    private static List<DeviceInfo> getConnectedDevices() {
        List<DeviceInfo> devices = new ArrayList<>();
        try {
            Process process = Runtime.getRuntime().exec("adb devices");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.endsWith("\tdevice")) {
                    String udid = line.split("\t")[0];
                    devices.add(getDeviceInfo(udid));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return devices;
    }

    private static DeviceInfo getDeviceInfo(String udid) {
        DeviceInfo info = new DeviceInfo();
        info.udid = udid;
        try {
            info.model = execADBCommand(udid, "getprop ro.product.model");
            info.brand = execADBCommand(udid, "getprop ro.product.brand");
            info.androidVersion = execADBCommand(udid, "getprop ro.build.version.release");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }

    private static String execADBCommand(String udid, String command) throws Exception {
        Process process = Runtime.getRuntime().exec("adb -s " + udid + " shell " + command);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String output = reader.readLine();
        reader.close();
        return output != null ? output.trim() : "";
    }

    private static class DeviceInfo {
        String udid;
        String model;
        String brand;
        String androidVersion;

        @Override
        public String toString() {
            return brand + " " + model + " (Android " + androidVersion + ") UDID: " + udid;
        }
    }

    public static AndroidDriver getDriver() {
        if (driver instanceof AndroidDriver) {
            return (AndroidDriver) driver;
        } else {
            throw new IllegalStateException("El driver actual no es un AndroidDriver");
        }
    }

    public static RemoteWebDriver getWebDriver() {
        if (driver instanceof RemoteWebDriver) {
            return (RemoteWebDriver) driver;
        } else {
            throw new IllegalStateException("El driver actual no es un RemoteWebDriver");
        }
    }


    public static void cerrarWebDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
