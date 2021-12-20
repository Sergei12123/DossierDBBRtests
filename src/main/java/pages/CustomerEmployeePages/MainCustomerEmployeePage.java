package pages.CustomerEmployeePages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MainCustomerEmployeePage {
    @Step("Перейти на вкладку {0}")
    public void goToTab(String tab){
        $(byText(tab)).click();
    }
}
