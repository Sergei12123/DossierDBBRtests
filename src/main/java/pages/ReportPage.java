package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ReportPage {
    @Step("Заполняет данные категории '{0}'")
    public void setTitleByCategory(String key, String value) {
        switch (key){
            case "Дата проведения мероприятия":
                $(byAttribute("placeholder","Выберите дату")).click();
                $$(byAttribute("placeholder","Выберите дату")).find(Condition.not(Condition.attribute("readonly"))).setValue(value).pressEnter();
                break;
            case "Примечание":
            case "Информация негативного характера":
            case "При проведении мероприятия установлено":
                $(byText(key)).parent().parent().$(byTagName("textarea")).setValue(value);
                break;
            default:
                $(byText(key)).parent().parent().$(byTagName("input")).setValue(value);

        }
        $(byText(key)).parent().parent().$(byTagName("input")).setValue(value);
    }

    @Step("Нажать кнопку {0}")
    public void clickButton(String buttonName){
        $$(byTagName("button")).find(Condition.text(buttonName)).click();
    }
}
