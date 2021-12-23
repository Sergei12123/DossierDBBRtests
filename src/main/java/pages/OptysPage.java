package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byTagName;
import static com.codeborne.selenide.Selenide.$$;

public class OptysPage {
    @Step("Показать информацио о заявке номер {0}")
    public void chooseOpty(String optyNum){
        $$(byTagName("a")).find(Condition.text(optyNum)).click();
    }
}
