package pages.CustomerEmployeePages;

import at.exceptions.WaitUtil;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.junit.Assert;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class CheckActualInformationPage {
    @Step("Подтвердить, что просмотрены все отчеты")
    public void acceptReports(){
        WaitUtil.sleep(1000);
        if(!$(byAttribute("role","document")).find(byText("Ошибка")).exists()) {
            if ($(byAttribute("role", "document")).exists())
                clickOnDialogConformData("Подтверждаю");
        }else{
            $(byAttribute("role","document")).find(byText("Подробности")).shouldBe(Condition.exist).click();
            Assert.fail("Произошла системная ошибка:\n"+$(byAttribute("role","document")).find(byTagName("textarea")).getText());
        }
    }

    @Step("В диалоговом окне выбрать пункт ")
    public void clickOnDialogConformData(String value) {
        $(byAttribute("role","document")).$$(byTagName("button")).find(Condition.text(value)).shouldBe(Condition.exist).click();
    }
}
