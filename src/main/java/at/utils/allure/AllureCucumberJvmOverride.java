package at.utils.allure;

import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm;

/**
 * Работа с плагином allure cucumber version 6
 */
public class AllureCucumberJvmOverride extends AllureCucumber6Jvm {

    static {
        // Инициализируем кастомный AllureLifecycle строго до момента вызова
        // конструктора AllureCucumberJvm, т.к. в нем идет получение текущего AllureLifecycle и
        // если не успеть инициализировать кастомный, то будет установлен дефолтный
        Allure.setLifecycle(new AllureLifecycle());
    }
}
