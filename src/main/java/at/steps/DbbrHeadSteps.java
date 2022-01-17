package at.steps;

import at.models.Opty;
import at.models.Organization;
import at.parser.Context;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import pages.InfoAboutOptyPage;
import pages.MainPage;
import pages.OptysPage;

import java.util.Map;

import static pages.UniversalPage.checkButtonEnabled;
import static pages.UniversalPage.checkWidgetExist;

public class DbbrHeadSteps {
    @Когда("выбирает сформированную заявку")
    public void selectOpty(){
        new MainPage().goToTab("Заявки");
        checkWidgetExist("Мои текущие заявки");
        Opty opty=(Opty) Context.getSavedObject("Заявка");
        new OptysPage().chooseOpty(opty.getNumber());
        checkButtonEnabled("Назначить");
        new InfoAboutOptyPage().checkAllWidgets();
        new InfoAboutOptyPage().checkAllOptyData((Map<String, String>) Context.getSavedObject("Информация о мероприятии"),
                (Organization) Context.getSavedObject("Организация"),
                (Map<String, String>) Context.getSavedObject("Сведения об объекте проверки"));
    }

    @И("выбирает исполнителя {string}")
    public void chooseExecutor(String executor) {
        new InfoAboutOptyPage().chooseExecutor(executor);
        Opty opty=(Opty) Context.getSavedObject("Заявка");

    }

    @И("выбирает заявку в работе")
    public void chooseInWorkOpty() {
        new MainPage().goToTab("Заявки");
        checkWidgetExist("Мои текущие заявки");
        Opty opty=(Opty) Context.getSavedObject("Заявка");
        new OptysPage().chooseOpty(opty.getNumber());
        new InfoAboutOptyPage().checkAllWidgets();
        new InfoAboutOptyPage().checkAllOptyData((Map<String, String>) Context.getSavedObject("Информация о мероприятии"),
                (Organization) Context.getSavedObject("Организация"),
                (Map<String, String>) Context.getSavedObject("Сведения об объекте проверки"));
        checkButtonEnabled("Согласовать");
        checkButtonEnabled("Отклонить");

    }
}
