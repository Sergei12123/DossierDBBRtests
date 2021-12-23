package pages.CustomerEmployeePages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class InformationAboutEventPage {
    @Step("Заполнить данные категории '{0}'")
    public void chooseTitleByCategory(String category, String title){
        SelenideElement sel=$(byText(category)).parent().parent().$(byAttribute("role","combobox"));
        sel.click();
        sel.parent().parent().find(byAttribute("title",title)).click();
    }
    @Step("Нажать кнопку {0}")
    public void clickButton(String buttonName){
        $$(byTagName("button")).find(Condition.text(buttonName)).click();
    }
}
