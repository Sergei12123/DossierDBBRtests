package at.steps;

import at.models.Opty;
import at.models.Organization;
import at.parser.Context;
import io.cucumber.java.ru.И;
import pages.InfoAboutOptyPage;
import pages.MainPage;
import pages.OptysPage;

import java.util.Map;

import static pages.UniversalPage.checkButtonEnabled;
import static pages.UniversalPage.checkWidgetExist;

public class CustomerHeadSteps {
    @И("выбирает созданную заявку")
    public void chooseCreatedOpty(){
        new MainPage().goToTab("Заявки");
        Opty opty=(Opty) Context.getSavedObject("Заявка");
        new OptysPage().chooseOpty(opty.getNumber());
        checkButtonEnabled("Согласовать");
        checkButtonEnabled("На доработку");
        new InfoAboutOptyPage().checkAllWidgets();
        new InfoAboutOptyPage().checkAllOptyData((Map<String, String>) Context.getSavedObject("Информация о мероприятии"),
                (Organization) Context.getSavedObject("Организация"),
                (Map<String, String>) Context.getSavedObject("Сведения об объекте проверки"));

    }
}
