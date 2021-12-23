package at.steps;

import at.models.Opty;
import at.parser.Context;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import pages.CustomerEmployeePages.InformationAboutOrganizationPage;
import pages.InfoAboutOptyPage;
import pages.MainPage;
import pages.OptysPage;
import pages.ReportPage;

import java.util.Map;

public class DbbrEmployeeSteps {
    @Когда("выбирает назначенную заявку")
    public void selectOpty(){
        new MainPage().goToTab("Заявки");
        Opty opty=(Opty) Context.getSavedObject("Заявка");
        new OptysPage().chooseOpty(opty.getNumber());
    }

    @И("переходит во вкладку {string}")
    public void goToTab(String tab) {
        new InfoAboutOptyPage().goToTab(tab);
    }

    @Тогда("заполняет отчет по мероприятию")
    public void fillReportAboutEvent(Map<String,String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            new ReportPage().setTitleByCategory(entry.getKey(),entry.getValue());
        }
    }
}
