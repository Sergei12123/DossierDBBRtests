package at.steps;
import at.database.dao.DAOTest;
import at.helpers.HookHelper;
import at.models.User;
import io.cucumber.java.ru.Пусть;
import org.junit.jupiter.api.Assertions;

import java.util.Objects;

public class PreconditionSteps {
    @Пусть("^существует пользователь (.*)")
    public void isUserExist(String role){
        boolean flag= false;
        for(User user: HookHelper.getEnvironment().users){
            if(Objects.equals(user.getRole(), role)){
                flag=true;
                break;
            }
        }
        Assertions.assertTrue(flag,"Пользователь с роль");
    }
}
