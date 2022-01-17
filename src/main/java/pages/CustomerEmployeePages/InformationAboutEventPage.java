package pages.CustomerEmployeePages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class InformationAboutEventPage {
    @Step("Заполнить данные категории '{0}'")
    public void chooseTitleByCategory(String category, String title){
        sleep(1000);
        SelenideElement sel=$(byText(category)).parent().parent().$(byAttribute("role","combobox"));
        sel.click();
        sel.parent().parent().find(byAttribute("title",title)).click();
    }



    @Step("Нажать кнопку {0}")
    public void clickButton(String buttonName){
        $$(byTagName("button")).find(Condition.text(buttonName)).click();
    }
}
