package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static at.exceptions.WaitUtil.sleep;
import static com.codeborne.selenide.Selectors.byTagName;
import static com.codeborne.selenide.Selenide.$$;

public class OptysPage {
    @Step("Показать информацио о заявке номер {0}")
    public void chooseOpty(String optyNum){
        sleep(2000);
        $$(byTagName("a")).find(Condition.text(optyNum)).shouldBe(Condition.exist).parent().click();
        $$(byTagName("a")).find(Condition.text(optyNum)).shouldBe(Condition.not(Condition.exist));
    }

    @Step("Проверка что заявка исчезла из списка заявок")
    public void checkOptyAbsence(String number) {
        $$(byTagName("a")).find(Condition.text(number)).shouldBe(Condition.not(Condition.exist));
    }
}
