package at.steps;

import at.database.dao.ApplicationDAO;
import at.enums.DocPath;
import at.enums.Roles;
import at.exceptions.*;
import at.helpers.Environment;
import at.helpers.HookHelper;
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

import java.util.Map;
import java.util.Objects;

import static at.exceptions.WaitUtil.sleep;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
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
        Context.saveObject("Пользователь",user);
        open(environment.urls.get("loginPage"));
        new DBBRLoginPage().login(user.getLogin(),user.getPassword());
    }

    @И("^выбирает роль (Сотрудник подразделения-инициатора|Руководитель подразделения-инициатора|Руководитель ДББР|Сотрудник ДББР)")
    public void chooseRole(String role) {
        switch (role){
            case "Сотрудник подразделения-инициатора":
                Context.saveObject("Роль", Roles.CUSTOMER_EMPLOYEE);
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
            default:
                throw new StepNotImplementedException("выбирает роль", role);
        }
        new MainPage().changeRole(((Roles)Context.getSavedObject("Роль")).getRole());



    }

    @И("нажимает кнопку {string} на экране {string}")
    public void clickButton(String buttonName, String screenName) {
        Opty opty=(Opty) Context.getSavedObject("Заявка");
        switch (screenName){
            case "Сведения о мероприятии":
                new InformationAboutEventPage().clickButton(buttonName);
                break;
            case "Сведения о проверяемой организации":
                new InformationAboutOrganizationPage().clickButton(buttonName);
                switch (buttonName){
                    case "Найти организацию":
                        Organization organization=(Organization)Context.getSavedObject("Организация");
                        checkTextInTextField("Регистрационный номер",organization.getRegNum());
                        checkTextInTextField("Наименование организации",organization.getName());
                        checkTextInTextField("ИНН",organization.getInn());
                        checkTextInTextField("ОГРН", organization.getOgrn());
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
                new InformationAboutCheckObject().clickButton(buttonName);
                switch (buttonName){
                    case "Далее":
                        checkWidgetExist("Шаги создания заявки");
                        checkWidgetExist("Актуальная информация по заявке");
                        checkActiveStep(4);
                        checkButtonEnabled("Отменить заявку");
                        checkButtonEnabled("Далее");
                        break;
                }
                break;
            case "Проверка актуальной информации":
                new CheckActualInformationPage().clickButton(buttonName);
                switch (buttonName){
                    case "Далее":
                        checkWidgetExist("Шаги создания заявки");
                        checkWidgetExist("Загрузка документов");
                        checkActiveStep(5);
                        checkButtonEnabled("Отменить заявку");
                        checkButtonEnabled("Отправить на согласование");
                        break;
                }
                break;
            case "Добавление документов":
                new AddDocumentsPage().clickButton(buttonName);
                switch (buttonName){
                    case "Отправить на согласование":
                        checkWidgetExist("Мои текущие заявки");
                        break;
                }
                break;
            case "Информация о заявке":
                new InfoAboutOptyPage().clickButton(buttonName);
                switch (buttonName){
                    case "Согласовать":
                        checkWidgetExist("Мои текущие заявки");
                        new OptysPage().checkOptyAbsence(opty.getNumber());
                        break;
                    case "Назначить":
                        opty=(Opty) Context.getSavedObject("Заявка");
                        checkWidgetExist("Мои текущие заявки");
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
                        new InfoAboutOptyPage().checkValueInKey("Статус","В работе");
                        break;
                    case "Заявка исполнена":
                        checkWidgetExist("Заявка");
                        new InfoAboutOptyPage().checkAllWidgets();
                        new InfoAboutOptyPage().checkValueInKey("Статус","Выполнено");
                        break;
                }
                break;
            case "Отчет по мероприятию":
                new ReportPage().clickButton(buttonName);
                switch (buttonName){
                    case "Отправить отчет на согласование":
                        opty=(Opty) Context.getSavedObject("Заявка");
                        checkWidgetExist("Мои текущие заявки");
                        new OptysPage().checkOptyAbsence(opty.getNumber());
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
        }
        Assert.assertEquals(statusDB,new ApplicationDAO().getOptyStatus(opty.getNumber()));
        opty.setStatus(statusDB);
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
                page.addDocument(DocPath.FIRSTDOCUMENT.getPath());
                page.setDocName("Документ №1");
                page.clickButton("Сохранить");
                break;
        }
    }
}
