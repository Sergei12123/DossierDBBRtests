package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.Step;
import lombok.extern.java.Log;
import org.junit.Assert;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class UniversalPage {
    @Step("Проверка на существование элемента")
    public static void checkWidgetExist(String widgetName){
        switch (widgetName){
            case "Шаги создания заявки":
                $(".ant-steps-item").shouldBe(Condition.exist).shouldBe(Condition.visible);
                break;
            default:
                $(byText(widgetName)).shouldBe(Condition.exist).shouldBe(Condition.visible);
                break;
        }
    }

    @Step("Проверка на то что в поле {0} указано значение {1}")
    public static void checkTextInTextField(String textFieldName,String value){
        if(!$(byText(textFieldName)).parent().parent().find(byText(value)).exists()){
            System.out.println("Не удалось найти элемент по тексту");
            if(!$(byText(textFieldName)).parent().parent().find(byAttribute("title",value)).exists()) {
                System.out.println("Не удалось найти элемент по атрибуду title");
                $(byText(textFieldName)).parent().parent().find(byAttribute("value",value)).shouldBe(Condition.exist);
            }
        }

    }

    @Step("Проверка что на виджете «Шаги создания заявки» выделен этап «Шаг {0}»")
    public static void checkActiveStep(int stepNum){
        $(".ant-steps-item-process").find(byText("Шаг "+stepNum)).shouldBe(Condition.exist);
    }

    @Step("Проверка доступности кнопки")
    public static void checkButtonEnabled(String buttonName){
        $$(byTagName("button")).find(Condition.text(buttonName)).shouldBe(Condition.exist).shouldBe(Condition.enabled).shouldBe(Condition.visible);
    }
}
