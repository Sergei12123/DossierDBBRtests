package at.helpers;


import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

public class HookHelper {
    private static Environment environment;
    /**
     * Инициализация переменных для заданного стенда
     * @return Environment - свойство в формате xml
     */
    public static Environment getEnvironment() {
        if (environment != null)
            return environment;
        File xml = new File("src/main/resources/environments.xml");
        environment = Environment.getProperties(xml,"test-DossierDBBR");
        return environment;
    }

    /**
     * Запустить браузер
     *
     */
    @Step("Инициализация браузера")
    public static void initWebDriver() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        RemoteWebDriver driver = new ChromeDriver(loadBrowserCapabilities());
        driver.manage().window().maximize();
        setWebDriver(driver);
        System.out.println("WebDriver установлен");
    }

    /**
     * Установить свойства браузера
     * @return DesiredCapabilities - свойства браузера
     */
    private static DesiredCapabilities loadBrowserCapabilities() {
        DesiredCapabilities capabilities =  new DesiredCapabilities(BrowserType.CHROME, "", Platform.ANY);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addExtensions(new File("src/main/resources/extensions/ModifyHeaders_2.2.4_0.crx"));
        chromeOptions.setExperimentalOption("useAutomationExtension", false);
        chromeOptions.addArguments("--window-size=1280,1024", "--ignore-certificate-errors");
        Map<String, Object> prefs = new HashMap();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        chromeOptions.setExperimentalOption("prefs", prefs);
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
                UnexpectedAlertBehaviour.IGNORE);
        String tags = System.getProperty("cucumber.tags");
        if (tags != null) {
            capabilities.setCapability("name", tags);
        }
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("screenResolution", "1920x1080x24");
        capabilities.setCapability("env", new String[]{"LANG=ru_RU.UTF-8", "LANGUAGE=ru:en", "LC_ALL=ru_RU.UTF-8"});
        return capabilities;
    }

    /**
     * Закрыть браузер
     */
    public static void clearWebDriver() {
        try {
            Selenide.closeWebDriver();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

}
