package at.steps;

import at.database.dao.ApplicationDAO;
import at.enums.Roles;
import at.exceptions.*;
import at.helpers.Environment;
import at.helpers.HookHelper;
import at.models.Opty;
import at.models.User;
import at.parser.Context;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import org.junit.jupiter.api.Assertions;
import pages.CustomerEmployeePages.*;
import pages.DBBRLoginPage;
import pages.InfoAboutOptyPage;
import pages.MainPage;
import pages.ReportPage;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.open;

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
        Assertions.assertNotNull(user,"Пользователь не найден");
        Context.saveObject("Пользователь",user);
        open(environment.urls.get("loginPage"));
        new DBBRLoginPage().login(user.getLogin(),user.getPassword());
    }

    @И("^выбирает роль (CUSTOMER_EMPLOYEE|CUSTOMER_HEAD|DBBR_HEAD|DBBR_EMPLOYEE)")
    public void chooseRole(String role) {
        new MainPage().changeRole(role);
        switch (role){
            case "CUSTOMER_EMPLOYEE":
                Context.saveObject("Текущий пользователь", Roles.CUSTOMER_EMPLOYEE);
                break;
            case "CUSTOMER_HEAD":
                Context.saveObject("Текущий пользователь", Roles.CUSTOMER_HEAD);
                break;
            case "DBBR_HEAD":
                Context.saveObject("Текущий пользователь", Roles.DBBR_HEAD);
                break;
            case "DBBR_EMPLOYEE":
                Context.saveObject("Текущий пользователь", Roles.DBBR_EMPLOYEE);
                break;
            default:
                throw new StepNotImplementedException("выбирает роль", role);
        }
    }

    @И("нажимает кнопку {string} на экране {string}")
    public void clickButton(String buttonName, String screenName) {

        switch (screenName){
            case "Сведения о мероприятии":
                new InformationAboutEventPage().clickButton(buttonName);
                break;
            case "Сведения о проверяемой организации":
                new InformationAboutOrganizationPage().clickButton(buttonName);
                break;
            case "Сведения об объекте проверки":
                new InformationAboutCheckObject().clickButton(buttonName);
                break;
            case "Проверка актуальной информации":
                new CheckActualInformationPage().clickButton(buttonName);
                break;
            case "Добавление документов":
                new AddDocumentsPage().clickButton(buttonName);
                break;
            case "Информация о заявке":
                new InfoAboutOptyPage().clickButton(buttonName);
                break;
            case "Отчет по мероприятию":
                new ReportPage().clickButton(buttonName);
                break;
        }
    }

    @И("статус заявки {string}")
    public void checkStatus(String status) {
        String statusDB="";
        Opty opty=(Opty) Context.getSavedObject("Заявка");
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
        }
        Assertions.assertEquals(statusDB,new ApplicationDAO().getOptyStatus(opty.getNumber()));
        opty.setStatus(statusDB);
    }

    @И("выходит из приложения")
    public void logOut() {
        new MainPage().logOut();
    }
}
