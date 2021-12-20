package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    @Step("Меняет роль на {0}")
    public void changeRole(String role){
        $(byAttribute("data-icon","user")).click();
        $(byAttribute("title","Roles")).click();
        $(byText(role)).click();
    }
}
