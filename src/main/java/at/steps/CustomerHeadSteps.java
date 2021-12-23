package at.steps;

import at.models.Opty;
import at.parser.Context;
import io.cucumber.java.ru.И;
import pages.MainPage;
import pages.OptysPage;

public class CustomerHeadSteps {
    @И("выбирает созданную заявку")
    public void chooseCreatedOpty(){
        new MainPage().goToTab("Заявки");
        Opty opty=(Opty) Context.getSavedObject("Заявка");
        new OptysPage().chooseOpty(opty.getNumber());
    }
}
