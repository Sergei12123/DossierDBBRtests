package pages.CustomerEmployeePages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.junit.Assert;

import static com.codeborne.selenide.Selectors.byTagName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class InformationAboutOrganizationPage {
    @Step("Нажать кнопку {0}")
    public void clickButton(String buttonName){
        $$(byTagName("button")).find(Condition.text(buttonName)).click();
        if(buttonName.equals("Найти организацию")){
            String inn=$(byText("ИНН")).parent().parent().$(byTagName("input")).getAttribute("value");
            Assert.assertNotNull(inn);
        }
    }

    @Step("Заполняет данные категории '{0}'")
    public void setTitleByCategory(String key, String value) {

        $(byText(key)).parent().parent().$(byTagName("input")).setValue(value);
    }
}
