package at.steps;

import at.helpers.HookHelper;
import at.parser.Context;
import at.utils.allure.AllureHelper;
import at.utils.allure.StepsAspects;
import com.codeborne.selenide.Configuration;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;


/**
 * Класс содержит действия для каждого сценария или шага
 */
@Log4j2
public class Hook {
    public static boolean isHeadless=false;

    /**
     * Подключение к базе данных
     */
    @Before(order = 1)
    public void initializeDB() {
        HookHelper.initDatabase(HookHelper.getEnvironment());
    }

    @Before(order = 2)
    public void setConfigTimeout() {
        Configuration.timeout=40000;
        HookHelper.ready=true;
        isHeadless=false;
    }

    @Before(order = 3)
    public void initializeBrowser() {
        HookHelper.initWebDriver(isHeadless);
    }

    /**
     * Сделать скриншот если тест упал
     */
    @After(order = 2)
    public void makeScreenshotIfFail(Scenario scenario) {
        AllureHelper.execIgnoreException("Сделать скриншот при падении теста", () ->
        {
            if(scenario.isFailed()) HookHelper.makeScreenshot();
            return null;
        });

    }
    /**
     * Закрыть браузер и закрыть подключение к БД
     */
    @After(order = 1)
    @Step("Закрытие браузера и подключений к БД")
    public void clear() {
        AllureHelper.execIgnoreException("Закрытие браузера", () ->
        {
            HookHelper.clearWebDriver();
            return null;
        });
        AllureHelper.execIgnoreException("Закрытие коннекта к БД", () ->
        {
            HookHelper.clearDatabaseConnections();
            HookHelper.clearWebDriver();
            return null;
        });
    }

    /**
     * Очистить контекст
     */
    @After(order = 3)
    @Step("Очистка контекста")
    public void clearContext() {
        HookHelper.ready=false;
        AllureHelper.execIgnoreException("Очистка", () ->
        {
            Context.clearLocalStorage();
            return null;
        });
    }
}
