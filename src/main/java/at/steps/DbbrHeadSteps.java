package at.steps;

import at.models.Opty;
import at.parser.Context;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import pages.InfoAboutOptyPage;
import pages.MainPage;
import pages.OptysPage;

public class DbbrHeadSteps {
    @Когда("выбирает сформированную заявку")
    public void selectOpty(){
        new MainPage().goToTab("Заявки");
        Opty opty=(Opty) Context.getSavedObject("Заявка");
        new OptysPage().chooseOpty(opty.getNumber());
    }

    @И("выбирает исполнителя {string}")
    public void chooseExecutor(String executor) {
        new InfoAboutOptyPage().chooseExecutor(executor);
    }
}
