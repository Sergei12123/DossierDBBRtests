package pages.CustomerEmployeePages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class InformationAboutCheckObject {
    @Step("Заполняет данные категории '{0}'")
    public void setTitleByCategory(String key, String value) {
        switch (key){
            case "Гражданство":
            case "Регион":
                SelenideElement sel=$(byText(key)).parent().parent().$(byAttribute("role","combobox"));
                sel.click();
                sel.parent().parent().find(byAttribute("title",value)).click();
                break;
            case "Дополнительная информация":
                $(byText(key)).parent().parent().$(byTagName("textarea")).setValue(value);
                break;
            case "Дата рождения":
            case "Утвержденная дата исполнения":
                $(byAttribute("placeholder","Выберите дату")).click();
                $$(byAttribute("placeholder","Выберите дату")).find(Condition.not(Condition.attribute("readonly"))).setValue(value).pressEnter();
                break;
            default:
                $(byText(key)).parent().parent().$(byTagName("input")).setValue(value);
        }

    }

    @Step("Нажать кнопку {0}")
    public void clickButton(String buttonName){
        $$(byTagName("button")).find(Condition.text(buttonName)).click();
    }
}
