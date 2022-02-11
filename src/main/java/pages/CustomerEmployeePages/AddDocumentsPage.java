package pages.CustomerEmployeePages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;


import java.util.Optional;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$$;

public class AddDocumentsPage {
    @Step("Нажать кнопку {0}")
    public void clickButton(String buttonName){
        $$(byTagName("button")).find(Condition.text(buttonName)).click();
    }

    @Step("Указать название документа")
    public void setDocName(String name){
        final ElementsCollection labels = $$(byTagName("label"));
        final Optional<SelenideElement> first = labels.stream().filter(label -> label.getText().equals("Название документа")).findFirst();
        first.ifPresent(el->el.parent().parent().$(byTagName("input")).setValue(name));
    }
}
