package pages.CustomerEmployeePages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import java.io.File;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class AddDocumentsPage {
    @Step("Нажать кнопку {0}")
    public void clickButton(String buttonName){
        $$(byTagName("button")).find(Condition.text(buttonName)).click();
    }

    @Step("Прикрепить документ")
    public void addDocument(String path) {
        clickButton("Загрузить новый документ");
        File file=new File(System.getProperty("user.dir").concat(path));
        $(byAttribute("type","file")).sendKeys(file.getPath());
        $(byAttribute("type","file")).uploadFile(file);
    }

    @Step("Указать название документа")
    public void setDocName(String name){
        $(byTagName("label")).find(byText("Название документа")).parent().parent().lastChild().setValue(name);
    }
}
