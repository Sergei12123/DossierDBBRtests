package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;

public class MainPage {
    @Step("Меняет роль на {0}")
    public void changeRole(String role){
        refresh();
        $(byAttribute("data-icon","user")).click();
        $(byAttribute("title","Roles")).click();
        $(byText(role)).click();
    }

    public void logOut() {
        refresh();
        $(byAttribute("data-icon","user")).click();
        $(byText("Log out")).click();
    }

    @Step("Перейти на вкладку {0}")
    public void goToTab(String tab){
        $(byText(tab)).click();
    }
}
