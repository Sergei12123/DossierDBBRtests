package pages;

import at.parser.Context;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byTagName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DBBRLoginPage {

    @Step("Пользователь с логином {0} и паролем {1} заходит в приложение")
    public void login(String login, String password){
        if(Context.getSavedObjects().containsKey("пользователь")){
            $$(byTagName("button")).find(Condition.text("Сменить пользователя")).should(Condition.exist).click();
        }
        $("#login").shouldBe(Condition.exist).setValue(login);
        $("#password").setValue(password);
        $("#bind").click();
        $("#bind").should(Condition.not(Condition.exist));
    }
}
