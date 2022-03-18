package at.steps;

import at.enums.Roles;
import at.models.Complaint;
import at.models.Opty;
import at.models.Organization;
import at.models.User;
import at.parser.Context;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import pages.*;
import pages.CustomerEmployeePages.InformationAboutOrganizationPage;

import java.util.Map;

import static pages.UniversalPage.checkButtonEnabled;
import static pages.UniversalPage.checkWidgetExist;

public class DbbrEmployeeSteps {
    @Когда("выбирает назначенную заявку")
    public void selectOpty() {
        new MainPage().goToTab("Заявки");
        checkWidgetExist("Мои текущие заявки");
        Opty opty = (Opty) Context.getSavedObject("Заявка");
        new OptysPage().chooseOpty(opty.getNumber());
        new InfoAboutOptyPage().checkAllWidgets();
        new InfoAboutOptyPage().checkAllOptyData((Map<String, String>) Context.getSavedObject("Информация о мероприятии"),
                (Organization) Context.getSavedObject("Организация"),
                (Map<String, String>) Context.getSavedObject("Сведения об объекте проверки"));
        checkButtonEnabled("Взять в обработку");

    }

    @И("выбирает назначенную рекламацию")
    public void selectAppointedComp(){
        new MainPage().goToTab("Рекламации");
        Complaint complaint=(Complaint) Context.getSavedObject("Рекламация");
        new ComplaintsPage().chooseComp(complaint.getNumber());
        checkButtonEnabled("Уточнить отчет");
        checkButtonEnabled("Новая заявка");
        new InfoComplaintPage().checkAllWidgets();
        new InfoComplaintPage().checkAllCompData((Map<String, String>) Context.getSavedObject("Информация о рекламации"));
    }

    @И("выбирает рекламацию на доработку в отклонении")
    public void selectRevisitedComp(){
        new MainPage().goToTab("Рекламации");
        Complaint complaint=(Complaint) Context.getSavedObject("Рекламация");
        new ComplaintsPage().chooseComp(complaint.getNumber());
        checkButtonEnabled("Отклонить рекламацию");
        checkButtonEnabled("Уточнить отчет");
        checkButtonEnabled("Новая заявка");
        new InfoComplaintPage().checkAllWidgets();
        new InfoComplaintPage().checkAllCompData((Map<String, String>) Context.getSavedObject("Информация о рекламации"));
    }

    @И("переходит во вкладку {string}")
    public void goToTab(String tab) {
        new InfoAboutOptyPage().goToTab(tab);
        Roles role = (Roles) Context.getSavedObject("Роль");

        switch (tab) {
            case "Отчет":
                new ReportPage().checkAllWidgets();
                checkButtonEnabled("Загрузить новый документ");
                switch (role) {
                    case DBBR_EMPLOYEE:
                        checkButtonEnabled("Сохранить");
                        break;
                    case DBBR_HEAD:
                        Map<String, String> map = (Map<String, String>) Context.getSavedObject("Отчет по мероприятию");
                        for (Map.Entry<String, String> entry : map.entrySet()) {
                            new ReportPage().checkValueByKey(entry.getKey(), entry.getValue());
                        }
                        checkWidgetExist("Информация по отчету");
                        checkWidgetExist("Загрузка файлов");
                        checkWidgetExist("Файлы");
                        break;
                }
                break;
            case "Заявка":
                new InfoAboutOptyPage().checkAllWidgets();
                switch (role) {
                    case DBBR_HEAD:
                        checkButtonEnabled("Согласовать");
                        checkButtonEnabled("Отклонить");
                        break;
                }
                break;
        }
    }

    @Тогда("заполняет отчет по мероприятию")
    public void fillReportAboutEvent(Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            new ReportPage().setTitleByCategory(entry.getKey(), entry.getValue());
        }
        Context.saveObject("Отчет по мероприятию", map);
    }

    @Тогда("заполняет результат обработки")
    public void fillProccessingResult(Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            new InfoAboutOptyPage().setTitleByCategory(entry.getKey(), entry.getValue());
        }
    }
}
