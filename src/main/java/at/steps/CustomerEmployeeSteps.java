package at.steps;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import pages.CustomerEmployeePages.CreateApplicationPage;
import pages.CustomerEmployeePages.InformationAboutEventPage;
import pages.CustomerEmployeePages.MainCustomerEmployeePage;

import java.util.Map;

public class CustomerEmployeeSteps {
    @Когда("^создает заявку с типом (Заявка на визуальный контроль)")
    public void createOpty(String type){
        new MainCustomerEmployeePage().goToTab("Создание заявки");
        new CreateApplicationPage().chooseOptyType(type);
    }

    @Тогда("заполняет сведения о мероприятии")
    public void fillInfoAboutEvent(Map<String,String> map) {
        InformationAboutEventPage informationAboutEventPage=new InformationAboutEventPage();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            informationAboutEventPage.chooseEventType(entry.getKey(),entry.getValue());
        }
    }
}
