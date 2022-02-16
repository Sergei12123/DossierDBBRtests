package pages;

import at.exceptions.WaitUtil;
import at.models.Organization;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static pages.UniversalPage.checkWidgetExist;

public class InfoAboutOptyPage {
    @Step("Нажать кнопку {0}")
    public void clickButton(String buttonName){
        $$(byTagName("button")).find(Condition.text(buttonName)).shouldBe(Condition.exist).click();
    }

    @Step("Выбрать исполнителя")
    public void chooseExecutor(String executor) {
        $(byAttribute("aria-label","icon: paper-clip")).click();
        $$(byTagName("td")).find(Condition.text(executor)).click();
    }

    @Step("Проверить наличие всех необходимых виджетов")
    public void checkAllWidgets(){
        checkWidgetExist("Информация о заявке");
        checkWidgetExist("Сведения о мероприятии");
        checkWidgetExist("Сведения об организации");
        checkWidgetExist("Сведения об объекте проверки");
        checkWidgetExist("Документы");
    }

    @Step("Проверить все данные по заявке")
    public void checkAllOptyData(Map<String,String> infoAboutEvent, Organization infoAboutOrg, Map<String,String> infoAboutCheckObject){
        checkInfoAboutEvent(infoAboutEvent);
        checkInfoAboutOrganization(infoAboutOrg);
        checkInfoAboutCheckObject(infoAboutCheckObject);
    }

    @Step("Проверить сведения о мероприятии")
    public void checkInfoAboutEvent(Map<String,String> infoAboutEvent){
        checkMap("Сведения о мероприятии",infoAboutEvent);
    }

    @Step("Проверить сведения об организации")
    public void checkInfoAboutOrganization(Organization infoAboutOrganization){
        Map<String,String> info=new HashMap<>();
        info.put("Наименование поднадзорной организации",infoAboutOrganization.getName());
        info.put("Регистрационный номер",infoAboutOrganization.getRegNum());
        info.put("ОГРН",infoAboutOrganization.getOgrn());
        info.put("ИНН",infoAboutOrganization.getInn());
        checkMap("Сведения об организации",info);
    }

    @Step("Проверить сведения об объекте проверки")
    public void checkInfoAboutCheckObject(Map<String,String> infoAboutCheckObject){
        Map<String, String> newMap = new HashMap<>(infoAboutCheckObject);
        newMap.remove("Код подразделения");
        checkMap("Сведения об объекте проверки",newMap);
    }

    private void checkMap(String widget,Map<String,String> map){
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String text=$(byText(widget)).parent().find(byText(entry.getKey())).parent().parent().lastChild().getText();
            Assert.assertEquals("Значение поля не совпало с ожидаемым",entry.getValue(),text);
        }
    }

    @Step("Перейти во вкладку {0}")
    public void goToTab(String tab) {
        $$(byAttribute("role","tab")).find(Condition.text(tab)).click();
        Assert.assertEquals(tab,$(byAttribute("aria-selected","true")).getText());
    }

    @Step("Проверить что в пункте {0} указано значение {1}")
    public void checkValueInKey(String key,String value) {
        String text=$(byText("Информация о заявке")).parent().find(byText("Статус")).parent().parent().lastChild().getText();
        if(!text.equals(value)){
            WaitUtil.sleep(1000);
            refresh();
            text=$(byText("Информация о заявке")).parent().find(byText("Статус")).parent().parent().lastChild().getText();
        }
        Assert.assertEquals(key+" не совпал c ожидаемым",value,text);
    }

    @Step("Заполняет данные категории '{0}'")
    public void setTitleByCategory(String key, String value) {
        switch (key){
            case "Обоснование отклонения":
            case "Обоснование уточнения":
                $(byText(key)).parent().parent().$(byTagName("textarea")).setValue(value);
                break;
            default:
                $(byText(key)).parent().parent().$(byTagName("input")).setValue(value);
        }
    }
}
