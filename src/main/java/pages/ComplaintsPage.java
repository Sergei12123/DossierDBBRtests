package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static at.exceptions.WaitUtil.sleep;
import static com.codeborne.selenide.Selectors.byTagName;
import static com.codeborne.selenide.Selenide.$$;

public class ComplaintsPage {
    @Step("Показать информацио о рекламации номер {0}")
    public void chooseComp(String compNum){
        sleep(2000);
        $$(byTagName("a")).find(Condition.text(compNum)).shouldBe(Condition.exist).parent().click();
        $$(byTagName("a")).find(Condition.text(compNum)).shouldBe(Condition.not(Condition.exist));
    }

    @Step("Проверка что рекламация исчезла из списка рекламаций")
    public void checkOptyAbsence(String number) {
        $$(byTagName("a")).find(Condition.text(number)).shouldBe(Condition.not(Condition.exist));
    }
}
