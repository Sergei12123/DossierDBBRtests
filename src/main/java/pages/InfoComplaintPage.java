package pages;

import at.models.Organization;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.junit.Assert;

import java.util.Map;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.UniversalPage.checkWidgetExist;

public class InfoComplaintPage {
    @Step("Заполняет данные категории '{0}'")
    public void setTitleByCategory(String key, String value) {
        switch (key){
            case "Утвержденная дата исполнения":
                $(byAttribute("placeholder","Выберите дату")).click();
                $$(byAttribute("placeholder","Выберите дату")).find(Condition.not(Condition.attribute("readonly"))).setValue(value).pressEnter();
                break;
            case "Описание причины рекламации":
                $(byText(key)).parent().parent().$(byTagName("textarea")).setValue(value);
                break;
            default:
                $(byText(key)).parent().parent().$(byTagName("input")).setValue(value);
        }
    }

    @Step("Проверить наличие всех необходимых виджетов")
    public void checkAllWidgets(){
        checkWidgetExist("Информация о рекламации");
        checkWidgetExist("Документы");
    }

    @Step("Проверить данные по рекламации")
    public void checkAllCompData(Map<String,String> infoAboutCompl){
        checkInfoAboutComp(infoAboutCompl);
    }

    @Step("Проверить сведения о рекламации")
    public void checkInfoAboutComp(Map<String,String> infoAboutCompl){
        checkMap("Информация о рекламации",infoAboutCompl);
    }

    private void checkMap(String widget,Map<String,String> map){
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String text=$(byText(widget)).parent().parent().find(byText(entry.getKey())).parent().parent().lastChild().getText();
            Assert.assertEquals("Значение поля не совпало с ожидаемым",entry.getValue(),text);
        }
    }

    @Step("Нажать кнопку {0}")
    public void clickButton(String buttonName){
        $$(byTagName("button")).find(Condition.text(buttonName)).click();
    }
}
