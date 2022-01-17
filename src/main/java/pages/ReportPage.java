package pages;

import at.enums.Roles;
import at.parser.Context;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.Assert;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.UniversalPage.checkWidgetExist;

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
    }

    @Step
    public void checkAllWidgets(){
        Roles role= (Roles) Context.getSavedObject("Роль");
        switch (role){
            case DBBR_HEAD:
                checkWidgetExist("Информация по отчету");
                break;
            default:
                checkWidgetExist("Внесение информации для отчета");

        }
        checkWidgetExist("Загрузка файлов");
        checkWidgetExist("Файлы");

    }

    @Step("Нажать кнопку {0}")
    public void clickButton(String buttonName){
        $$(byTagName("button")).find(Condition.text(buttonName)).click();
    }

    @Step("Проверить пункт в отчете")
    public void checkValueByKey(String key, String value) {
        String text=$(byText(key)).parent().parent().lastChild().getText();
        Assert.assertEquals("Значение поля "+key+" не совпало с ожидаемым: ",value,text);
    }
}
