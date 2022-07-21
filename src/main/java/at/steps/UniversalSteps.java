package at.steps;

import at.database.dao.ApplicationDAO;
import at.enums.DocPath;
import at.enums.Roles;
import at.exceptions.*;
import at.helpers.Environment;
import at.helpers.HookHelper;
import at.models.Complaint;
import at.models.Opty;
import at.models.Organization;
import at.models.User;
import at.parser.Context;
import com.codeborne.selenide.Condition;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import org.junit.Assert;
import pages.*;
import pages.CustomerEmployeePages.*;

import java.util.Objects;

import static at.exceptions.WaitUtil.sleep;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static pages.UniversalPage.*;

public class UniversalSteps {
    @Когда("^(.*) заходит в приложение")
    public void login(String alias){
        Environment environment=HookHelper.getEnvironment();
        User user=null;
        for(User user1 : environment.users){
            if(Objects.equals(user1.getAlias(), alias)){
               user=user1;
               break;
            }
        }
        Assert.assertNotNull("Пользователь не найден",user);
        open(environment.urls.get("loginPage"));
        new DBBRLoginPage().login(user.getLogin(),user.getPassword());
        Context.saveObject("Пользователь",user);
    }

    @И("^выбирает роль (Сотрудник подразделения-инициатора \\(ВК\\)|Сотрудник подразделения-инициатора \\(ППД\\)|Руководитель подразделения-инициатора|Руководитель ДББР|Сотрудник ДББР|Руководитель УБ/ОБ|Сотрудник ГВКиППД)")
    public void chooseRole(String role) {
        switch (role){
            case "Сотрудник подразделения-инициатора (ВК)":
                Context.saveObject("Роль", Roles.CUSTOMER_EMPLOYEE_VK);
                break;
            case "Сотрудник подразделения-инициатора (ППД)":
                Context.saveObject("Роль", Roles.CUSTOMER_EMPLOYEE_PPD);
                break;
            case "Руководитель подразделения-инициатора":
                Context.saveObject("Роль", Roles.CUSTOMER_HEAD);
                break;
            case "Руководитель ДББР":
                Context.saveObject("Роль", Roles.DBBR_HEAD);
                break;
            case "Сотрудник ДББР":
                Context.saveObject("Роль", Roles.DBBR_EMPLOYEE);
                break;
            case "Руководитель УБ/ОБ":
                Context.saveObject("Роль", Roles.GVKPPD_HEAD);
                break;
            case "Сотрудник ГВКиППД":
                Context.saveObject("Роль", Roles.GVKPPD_EMPLOYEE);
                break;
            default:
                throw new StepNotImplementedException("выбирает роль", role);
        }
        new MainPage().changeRole(((Roles)Context.getSavedObject("Роль")).getRole());



    }

    @И("нажимает кнопку {string} на виджете {string}")
    public void clickButton(String buttonName, String widgetName) {
        Opty opty=(Opty) Context.getSavedObject("Заявка");
        new MainPage().clickButton(buttonName,widgetName);
        switch (widgetName){
            case "Сведения о мероприятии":
                switch (buttonName){
                    case "Далее":
                        checkWidgetExist("Шаги создания заявки");
                        checkWidgetExist("Сведения о проверяемой организации");
                        checkActiveStep(2);
                        checkButtonEnabled("Отменить заявку");
                        checkButtonEnabled("Найти организацию");
                        break;
                    case "Сохранить":
                        WaitUtil.sleep(2000);
                        checkButtonEnabled("Отправить на согласование");
                        break;
                }
                break;
            case "Сведения о проверяемой организации":
                switch (buttonName){
                    case "Найти организацию":
                        Organization organization=(Organization)Context.getSavedObject("Организация");
                        checkTextInTextField("Регистрационный номер",organization.getRegNum());
                        checkTextInTextField("Наименование организации",organization.getName());
                        checkTextInTextField("ИНН",organization.getInn());
                        checkTextInTextField("ОГРН", organization.getOgrn());
                        checkButtonEnabled("Далее");
                        break;
                    case "Далее":
                        checkWidgetExist("Шаги создания заявки");
                        checkWidgetExist("Сведения об объекте проверки");
                        checkActiveStep(3);
                        checkButtonEnabled("Отменить заявку");
                        checkButtonEnabled("Далее");
                        break;
                }
                break;
            case "Сведения об объекте проверки":
                switch (buttonName){
                    case "Далее":
                        checkWidgetExist("Шаги создания заявки");
                        checkWidgetExist("Актуальная информация по заявке");
                        checkActiveStep(4);
                        checkButtonEnabled("Отменить заявку");
                        checkButtonEnabled("Далее");
                        break;
                    case "Сохранить":
                        WaitUtil.sleep(2000);
                        checkButtonEnabled("Отправить на согласование");
                        break;
                }
                break;
            case "Актуальная информация по заявке":
                switch (buttonName){
                    case "Далее":
                        new CheckActualInformationPage().acceptReports();
                        checkWidgetExist("Шаги создания заявки");
                        checkWidgetExist("Документы");
                        checkActiveStep(5);
                        checkButtonEnabled("Отменить заявку");
                        checkButtonEnabled("Отправить на согласование");
                        break;
                }
                break;
            case "Документы":
                switch (buttonName){
                    case "Отправить на согласование":
                    case "Отменить заявку":
                        checkWidgetExist("Список заявок");
                        break;
                }
                break;
            case "Заявка":
                switch (buttonName){
                    case "Согласовать":
                        checkWidgetExist("Список заявок");
//                        new OptysPage().checkOptyAbsence(opty.getNumber());
                        break;
                    case "На доработку":
                        checkWidgetExist("Список заявок");
                        new OptysPage().checkOptyAbsence(opty.getNumber());
                        break;
                    case "Назначить":
                        opty=(Opty) Context.getSavedObject("Заявка");
                        checkWidgetExist("Список заявок");
                        new OptysPage().checkOptyAbsence(opty.getNumber());
                        break;
                    case "Взять в обработку":
                        new InfoAboutOptyPage().checkAllWidgets();
                        checkButtonEnabled("В очередь");
                        checkButtonEnabled("Сохранить");
                        new InfoAboutOptyPage().checkValueInKey("Статус","В обработке");
                        break;
                    case "В очередь":
                        new InfoAboutOptyPage().checkAllWidgets();
                        checkButtonEnabled("В работу");
                        new InfoAboutOptyPage().checkValueInKey("Статус","В очереди");
                        break;
                    case "В работу":
                        new InfoAboutOptyPage().checkAllWidgets();
                        checkButtonEnabled("Уведомить о получении дополнительной информации");
                        new InfoAboutOptyPage().checkValueInKey("Статус","В работе");
                        break;
                    case "Заявка исполнена":
                        checkWidgetExist("Заявка");
                        new InfoAboutOptyPage().checkAllWidgets();
                        break;
                }
                break;
            case "Отчет по мероприятию":
                switch (buttonName){
                    case "Сохранить":
                        checkButtonEnabled("Отправить отчет на согласование");
                        break;
                    case "Отправить отчет на согласование":
                        checkWidgetExist("Список заявок");
//                        new OptysPage().checkOptyAbsence(opty.getNumber());
                        break;
                }
                break;
            case "Результаты обработки рекламации":
                switch (buttonName){
                    case "Сохранить":
                        checkButtonEnabled("Отклонить рекламацию");
                        break;
                }
                break;
            case "Результаты обработки заявки":
                switch (buttonName){
                    case "Сохранить":
                        if((Context.getSavedObject("Результат обработки")).equals("Обоснование уточнения"))
                            checkButtonEnabled("На уточнение");
                        else checkButtonEnabled("Отклонить заявку");
                        break;
                }
                break;
        }
    }

    @И("статус заявки {string}")
    public void checkStatus(String status) {
        String statusDB="";
        Opty opty=(Opty) Context.getSavedObject("Заявка");
        sleep(3000);
        switch (status){
            case "Черновик":
                statusDB="DRAFT";
                break;
            case "Сформирована":
                statusDB="FORMED";
                break;
            case "Назначена":
                statusDB="ASSIGNED";
                break;
            case "В работе":
                statusDB="IN_WORK";
                break;
            case "Исполнена":
                statusDB="COMPLETED";
                break;
            case "В очереди":
                statusDB="QUEUE";
                break;
            case "Отложена":
                statusDB="POSTPONED";
                break;
        }
        Assert.assertEquals(statusDB,new ApplicationDAO().getOptyStatus(opty.getNumber()));
        opty.setStatus(statusDB);
    }

    @И("статус рекламации {string}")
    public void checkStatusCompl(String status) {
        String statusDB="";
        Complaint comp=(Complaint) Context.getSavedObject("Рекламация");
        sleep(3000);
        switch (status){
            case "Черновик":
                statusDB="DRAFT";
                break;
            case "Сформирована":
                statusDB="FORMED";
                break;
            case "Назначена":
                statusDB="ASSIGNED";
                break;
            case "В работе":
                statusDB="IN_WORK";
                break;
            case "Исполнена":
                statusDB="COMPLETED";
                break;
        }
        Assert.assertEquals(statusDB,new ApplicationDAO().getOptyStatus(comp.getNumber()));
        comp.setStatus(statusDB);
    }

    @И("выходит из приложения")
    public void logOut() {
        new MainPage().logOut();
    }

    @И("добавляет документ на шаге {string}")
    public void addDocument(String step) {
        switch (step){
            case "Добавление документов":
                AddDocumentsPage page=new AddDocumentsPage();
                new MainPage().addDocument(DocPath.FIRSTDOCUMENT.getPath());
                page.setDocName("Документ №1");
                page.clickButton("ОК");
                break;
        }
    }
}
