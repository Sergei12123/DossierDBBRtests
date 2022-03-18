package at.steps;

import at.models.Complaint;
import at.models.Opty;
import at.models.Organization;
import at.parser.Context;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import pages.*;

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

    @И("выбирает сформированную рекламацию")
    public void selectComp(){
        new MainPage().goToTab("Рекламации");
        Complaint complaint=(Complaint) Context.getSavedObject("Рекламация");
        new ComplaintsPage().chooseComp(complaint.getNumber());
        checkButtonEnabled("Назначить");
        new InfoComplaintPage().checkAllWidgets();
        new InfoComplaintPage().checkAllCompData((Map<String, String>) Context.getSavedObject("Информация о рекламации"));
    }

    @И("выбирает отклоняемую рекламацию")
    public void selectRejectedComp(){
        new MainPage().goToTab("Рекламации");
        Complaint complaint=(Complaint) Context.getSavedObject("Рекламация");
        new ComplaintsPage().chooseComp(complaint.getNumber());
        checkButtonEnabled("Согласовать");
        checkButtonEnabled("Отклонить");
        new InfoComplaintPage().checkAllWidgets();
        new InfoComplaintPage().checkAllCompData((Map<String, String>) Context.getSavedObject("Информация о рекламации"));
    }

    @И("выбирает исполнителя {string}")
    public void chooseExecutor(String executor) {
        new InfoAboutOptyPage().chooseExecutor(executor);
        Opty opty=(Opty) Context.getSavedObject("Заявка");
    }

    @И("выбирает исполнителя рекламации {string}")
    public void chooseExecutorCompl(String executor) {
        new InfoAboutOptyPage().chooseExecutor(executor);
        Complaint comp=(Complaint) Context.getSavedObject("Рекламация");
    }

    @И("выбирает заявку в работе")
    public void chooseRejectedOpty() {
        new MainPage().goToTab("Заявки");
        checkWidgetExist("Мои текущие заявки");
        Opty opty=(Opty) Context.getSavedObject("Заявка");
        new OptysPage().chooseOpty(opty.getNumber());
        //TODO: добавить проверку виджета "Результаты обработки заявки"
        new InfoAboutOptyPage().checkAllWidgets();

        //TODO: добавить проверку текста на виджете "Результаты обработки заявки" (?)
        new InfoAboutOptyPage().checkAllOptyData((Map<String, String>) Context.getSavedObject("Информация о мероприятии"),
                (Organization) Context.getSavedObject("Организация"),
                (Map<String, String>) Context.getSavedObject("Сведения об объекте проверки"));
        checkButtonEnabled("Согласовать");
        checkButtonEnabled("Отклонить");
        checkButtonEnabled("Направить на изменение срока");
    }
}
