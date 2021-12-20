package at.steps;

import at.enums.Roles;
import at.exceptions.StepNotImplementedException;
import at.helpers.Environment;
import at.helpers.HookHelper;
import at.models.User;
import at.parser.Context;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import org.junit.jupiter.api.Assertions;
import pages.DBBRLoginPage;
import pages.MainPage;

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
        Assertions.fail("Падение");
    }

    @И("^выбирает роль (CUSTOMER_EMPLOYEE)")
    public void chooseRole(String role) {
        new MainPage().changeRole(role);
        switch (role){
            case "CUSTOMER_EMPLOYEE":
                Context.saveObject("Текущий пользователь", Roles.CUSTOMER_EMPLOYEE);
            default:
                throw new StepNotImplementedException("выбирает роль", role);
        }
    }
}
