package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    @Step("Меняет роль на {0}")
    public void changeRole(String role){
        refresh();
        $(byAttribute("data-icon","user")).click();
        int count=10;
        while(!$(byText(role)).exists() && count>0) {
            $(byAttribute("data-icon","user")).click();
            $(byAttribute("title","Роли")).click();
            count--;
        }
        $(byText(role)).click();
    }

    @Step("Произвести logOut")
    public void logOut() {
        refresh();
        int count=10;
        $(byAttribute("data-icon","user")).shouldBe(Condition.exist);
        while (count>0 && $(byAttribute("data-icon","user")).exists()){
            $(byAttribute("data-icon","user")).hover();
            sleep(1000);
            if($(byText("Выход")).exists())
                $(byText("Выход")).click();
            count--;
        }
        $(byAttribute("data-icon","user")).shouldBe(Condition.not(Condition.exist));
    }

    @Step("Перейти на вкладку {0}")
    public void goToTab(String tab){
        $(byText(tab)).click();
    }
}
