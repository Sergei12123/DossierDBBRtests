package pages.CustomerEmployeePages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byTagName;
import static com.codeborne.selenide.Selenide.$$;

public class CheckActualInformationPage {
    @Step("Нажать кнопку {0}")
    public void clickButton(String buttonName){
        $$(byTagName("button")).find(Condition.text(buttonName)).click();
    }
}
