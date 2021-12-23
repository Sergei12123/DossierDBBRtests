package at.steps;

import at.database.dao.ApplicationDAO;
import at.models.Opty;
import at.parser.Context;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import pages.CustomerEmployeePages.*;
import pages.MainPage;

import java.util.Map;

public class CustomerEmployeeSteps {
    @Когда("^создает заявку с типом (Заявка на визуальный контроль)")
    public void createOpty(String type){
        new MainPage().goToTab("Создание заявки");
        new CreateApplicationPage().chooseOptyType(type);
        Opty opty=new Opty();
        opty.setType(type);
        String optyNum=new ApplicationDAO().getOptyNumber();
        opty.setNumber(optyNum);
        Context.saveObject("Заявка",opty);
    }

    @Тогда("заполняет сведения о мероприятии")
    public void fillInfoAboutEvent(Map<String,String> map) {
        InformationAboutEventPage informationAboutEventPage=new InformationAboutEventPage();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            informationAboutEventPage.chooseTitleByCategory(entry.getKey(),entry.getValue());
        }
    }

    @Когда("заполняет сведения о проверяемой организации")
    public void fillInfoAboutOrganization(Map<String,String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            new InformationAboutOrganizationPage().setTitleByCategory(entry.getKey(),entry.getValue());
        }
    }

    @Тогда("заполняет сведения об объекте проверки")
    public void fillInfoAboutCheckObject(Map<String,String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            new InformationAboutCheckObject().setTitleByCategory(entry.getKey(),entry.getValue());
        }
    }
}
