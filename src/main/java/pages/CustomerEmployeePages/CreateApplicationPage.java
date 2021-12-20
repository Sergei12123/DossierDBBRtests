package pages.CustomerEmployeePages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CreateApplicationPage {
    @Step("Выбирает тип заявки")
    public void chooseOptyType(String type){
        switch (type){
            case "Заявка на визуальный контроль":
                $(byText("Создание заявки на визуальный контроль")).click();
                break;
            case "Заявка на проверку персональных данных":
                $(byText("Создание заявки на проверку персональных данных")).click();
                break;
        }
    }
}
