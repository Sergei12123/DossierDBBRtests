package pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import io.qameta.allure.Step;

import at.exceptions.WaitUtil;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byTagName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

import static at.exceptions.WaitUtil.sleep;

public class MainPage {
    @Step("Меняет роль на {0}")
    public void changeRole(String role){
        int count=10;
        do {
            sleep(1500);
            $(byAttribute("data-icon", "user")).shouldBe(Condition.exist).hover().click();
            sleep(1500);
            $(byAttribute("title", "Роли")).shouldBe(Condition.exist).hover().click();
            sleep(1500);
            $(byText(role)).hover().click();
            $(byAttribute("data-icon", "user")).shouldBe(Condition.exist).hover().click();
            count--;
        }while(!$(byText(role)).exists() && count>0);
    }

    @Step("Произвести logOut")
    public void logOut() {
        refresh();
        int count=10;
        $(byAttribute("data-icon","user")).shouldBe(Condition.exist);
        while (count>0 && $(byAttribute("data-icon","user")).exists()){
            $(byAttribute("data-icon","user")).hover().click();
            sleep(1500);
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

    @Step("Нажать на кнопку {0} на виджете {1}")
    public void clickButton(String buttonName,String widgetName){
      if($$(byTagName("button")).size()>0) {
          int count=countButtonsWithEqualText(buttonName);
          if(count>1) {
              final ElementsCollection widget = $$(byText(widgetName));
              final Optional<SelenideElement> first = widget.stream()
                  .filter(el -> Objects.requireNonNull(el.getAttribute("class")).contains("widget")).findFirst();
              first.ifPresent(el -> el.parent().parent().findAll(byTagName("button")).filter(Condition.text(buttonName)).first().shouldBe(Condition.exist)
                  .click());
          }else{
              if(count==1)
                  $$(byTagName("button")).find(Condition.text(buttonName)).click();
              else Assert.fail("На экране нет кнопки с именем "+buttonName);          }
      }else{
          Assert.fail("На странице нет кнопки "+buttonName);
      }
    }

    @Step("Нажать на кнопку {0}")
    public void clickButton(String buttonName){
        if($$(byTagName("button")).size()>0) {
            int count=countButtonsWithEqualText(buttonName);
            if(count>1) {
                Assert.fail("На экране больше одной кнопки с именем "+buttonName);
            }else{
                if(count==1)
                    $$(byTagName("button")).find(Condition.text(buttonName)).click();
                else Assert.fail("На экране нет кнопки с именем "+buttonName);
            }
        }else{
            Assert.fail("На странице нет кнопки "+buttonName);
        }
    }

    private int countButtonsWithEqualText(String buttonName){
        Configuration.timeout=2000;
        final AtomicInteger count = new AtomicInteger();
        int count1=10;
        while(count1>0){
            try {
                count1--;
                $$(byAttribute("type", "button")).forEach(el -> {
                    if (Objects.equals(el.getText(), buttonName)) {
                        count.getAndIncrement();
                    }
                });
                break;
            }catch (Exception|Error e) {
                WaitUtil.sleep(10000);
            }
        }

        Configuration.timeout=40000;
        return count.get();
    }

    @Step("Прикрепить документ")
    public void addDocument(String path) {
        clickButton("Загрузить новый документ");
        File file=new File(System.getProperty("user.dir").concat(path));
        $(byText("выберите файл")).click();
        sleep(4000);
        try {
            Robot robot=new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
            robot.keyRelease(KeyEvent.VK_ESCAPE);
        } catch (AWTException e) {
            e.printStackTrace();
        }
        $(byAttribute("type","file")).sendKeys(file.getPath());
    }

}
