package at.helpers;


import at.managers.DatabaseManager;
import at.models.Database;
import at.parser.Context;
import at.utils.allure.AllureHelper;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
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
import java.util.stream.Collectors;

import static com.codeborne.selenide.WebDriverRunner.setWebDriver;
@Log4j2
public class HookHelper {
    private static final String DB_NAME = "dbbr_db";
    private static Environment environment;
    public static boolean ready=false;
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

    /**
     * Подключение к базе данных
     * @param environment свойства среды
     */
    @Step("Инициализация БД")
    public static void initDatabase(Environment environment) {
        if (DatabaseManager.getDatabase(DB_NAME) == null) {
            Map<String, String> databaseProp = environment.databases.get(DB_NAME);
            Database database = DatabaseManager.getDatabase(DB_NAME, databaseProp.get("driver"),
                    databaseProp.get("host"),
                    databaseProp.get("port"),
                    databaseProp.get("service"),
                    databaseProp.get("login"),
                    databaseProp.get("password"));
            log.info("Получение данных БД "+database.getAlias()+" прошло успешно");
        }
    }
    /**
     * Закрыть подключение к БД
     */
    public static void clearDatabaseConnections() {
        if (DatabaseManager.getDatabase(DB_NAME) != null) {
            DatabaseManager.setDatabase(null, DB_NAME);
        }
    }
    /**
     * Сделать скриншот для отчета
     */
    public static void makeScreenshot() {
        AllureHelper.addAttachmentsToCase();
    }

    /**
     * Вывод контекста
     */
    @Step("Вывод содержимого контекста")
    public static void printContext() {
        String context = Context.getSavedVariables().entrySet().stream()
                .map(e -> e.getKey().concat(" : ").concat(e.getValue()))
                .collect(Collectors.joining("\n"));
        context = context.concat("\n\nObjects:\n\n");
        context += Context.getSavedObjects().entrySet().stream().map(e -> e.getKey().concat(" : ")
                .concat(e.getValue().toString())
                .concat(" ")).collect(Collectors.joining("\n"));
        AllureHelper.makeAttachTXT("Context", context);
        log.info("Context:\n".concat(context));
    }
}
