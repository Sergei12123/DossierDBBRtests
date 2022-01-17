package pages.CustomerEmployeePages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CreateApplicationPage {
    @Step("Выбирает тип заявки")
    public void chooseOptyType(String type){
        switch (type){
            case "Заявка на визуальный контроль":
                $(byText("Создать заявку на визуальный контроль")).parent().click();
                break;
            case "Заявка на проверку персональных данных":
                $(byText("Создать заявку на проверку персональных данных")).parent().click();
                break;
        }
    }
}
