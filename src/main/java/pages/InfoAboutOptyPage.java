package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byTagName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class InfoAboutOptyPage {
    @Step("Нажать кнопку {0}")
    public void clickButton(String buttonName){
        $$(byTagName("button")).find(Condition.text(buttonName)).click();
    }

    @Step("Выбрать исполнителя")
    public void chooseExecutor(String executor) {
        $(byAttribute("aria-label","icon: paper-clip")).click();
        $$(byTagName("td")).find(Condition.text(executor)).click();
    }

    public void goToTab(String tab) {
        $$(byAttribute("role","tab")).find(Condition.text(tab)).click();
    }
}
