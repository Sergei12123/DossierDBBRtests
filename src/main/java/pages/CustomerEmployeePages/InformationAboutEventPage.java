package pages.CustomerEmployeePages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class InformationAboutEventPage {
    @Step("Заполнить {0}")
    public void chooseEventType(String category,String type){
        $(byText(category)).parent().parent().lastChild().setValue(type).pressEnter();
        Assertions.assertEquals(type,$(byText(category)).parent().parent().lastChild().getText());
    }
}
