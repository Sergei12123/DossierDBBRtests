package at.steps;

import at.database.dao.ApplicationDAO;
import at.exceptions.WaitUtil;
import at.helpers.HookHelper;
import at.models.Complaint;
import at.models.Opty;
import at.models.Organization;
import at.parser.Context;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;

import pages.InfoComplaintPage;
import pages.CustomerEmployeePages.*;
import pages.InfoAboutOptyPage;
import pages.MainPage;
import pages.OptysPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static at.exceptions.WaitUtil.sleep;
import static pages.UniversalPage.*;

public class CustomerEmployeeSteps {
    @Когда("^создает заявку с типом (Заявка на визуальный контроль|Заявка на проверку персональных данных)")
    public void createOpty(String type){
        new MainPage().goToTab("Заявки");
        checkButtonEnabled("Создать заявку на визуальный контроль");
        checkButtonEnabled("Создать заявку на проверку персональных данных");
        new CreateApplicationPage().chooseOptyType(type);
        checkWidgetExist("Шаги создания заявки");
        checkWidgetExist("Сведения о мероприятии");
        checkActiveStep(1);
        checkButtonEnabled("Далее");
        checkButtonEnabled("Отменить заявку");
        switch (type){
            case "Заявка на визуальный контроль":
                checkTextInTextField("Тип заявки","Визуальный контроль");
                break;
            case "Заявка на проверку персональных данных":
                checkTextInTextField("Тип заявки","Проверка персональных данных");
                break;
        }
        Opty opty=new Opty();
        opty.setType(type);
        sleep(2000);
        String optyNum=new ApplicationDAO().getOptyNumber();
        opty.setNumber(optyNum);
        Context.saveObject("Заявка",opty);
    }

    @Тогда("создаёт рекламацию")
    public void createComp(){
        new MainPage().clickButton("Создать рекламацию","Заявка");
        Complaint comp=new Complaint();
        sleep(2000);
        String compNum=new ApplicationDAO().getOptyNumber();
        comp.setNumber(compNum);
        Context.saveObject("Рекламация",comp);
    }

    @Тогда("заполняет сведения о мероприятии")
    public void fillInfoAboutEvent(Map<String,String> map) {
        InformationAboutEventPage informationAboutEventPage=new InformationAboutEventPage();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            informationAboutEventPage.chooseTitleByCategory(entry.getKey(),entry.getValue());
        }
        Context.saveObject("Информация о мероприятии",new HashMap<>(map));
        checkTextInTextField("Срок исполнения","10");
    }

    @Тогда("перезаполняет сведения о мероприятии")
    public void refillInfoAboutEvent(Map<String,String> map) {
        HashMap<String, String> eventInfo= (HashMap<String, String>) Context.getSavedObject("Информация о мероприятии");
        for(HashMap.Entry<String, String> entry : map.entrySet()){
            new InformationAboutEventPage().chooseTitleByCategory(entry.getKey(),entry.getValue());
            String key=entry.getKey();
            if(eventInfo.containsKey(key)){
                eventInfo.put(key,map.get(key));
            }
        }
    }

    @Когда("заполняет сведения о проверяемой организации")
    public void fillInfoAboutOrganization(Map<String,String> map) {
        Organization orgNew=new Organization();
        List<Organization> organizationList = new ArrayList<>(Arrays.asList(HookHelper.getEnvironment().organizations));
        for (Map.Entry<String, String> entry : map.entrySet()) {
            switch (entry.getKey()){
                case "Регистрационный номер":
                    orgNew.setRegNum(entry.getValue());
                    organizationList=organizationList.stream().filter(a->a.getRegNum().equals(entry.getValue())).collect(Collectors.toList());
                    break;
                case "Наименование организации":
                    orgNew.setName(entry.getValue());
                    organizationList=organizationList.stream().filter(a->a.getName().equals(entry.getValue())).collect(Collectors.toList());
                    break;
                case "ИНН":
                    orgNew.setInn(entry.getValue());
                    organizationList=organizationList.stream().filter(a->a.getInn().equals(entry.getValue())).collect(Collectors.toList());
                    break;
                case "ОГРН":
                    orgNew.setOgrn(entry.getValue());
                    organizationList=organizationList.stream().filter(a->a.getOgrn().equals(entry.getValue())).collect(Collectors.toList());
                    WaitUtil.sleep(1000);
                    break;
                case "ФИО руководителя":
                    orgNew.setHeadfio(entry.getValue());
                    break;
                case "Должность руководителя":
                    orgNew.setHeadtitle(entry.getValue());
                    break;
            }
            Context.saveObject("Организация",organizationList.get(0));
            new InformationAboutOrganizationPage().setTitleByCategory(entry.getKey(),entry.getValue());
        }
    }

    @Тогда("заполняет сведения об объекте проверки")
    public void fillInfoAboutCheckObject(Map<String,String> map) {
        InformationAboutCheckObject informationAboutCheckObject =new InformationAboutCheckObject();
        Map<String,String> map2= new HashMap<>();;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            informationAboutCheckObject.setTitleByCategory(entry.getKey(),entry.getValue());
            if (entry.getKey().equals("ИНН физического лица"))
                map2.put("ИНН физ. лица", entry.getValue());
            else
                map2.put(entry.getKey(), entry.getValue());
        }
        Context.saveObject("Сведения об объекте проверки",map2);
    }

    @Тогда("заполняет дату исполнения")
    public void fillExecutionDate(Map<String,String> map) {
        InformationAboutCheckObject informationAboutEventPage=new InformationAboutCheckObject();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            informationAboutEventPage.setTitleByCategory(entry.getKey(),entry.getValue());
        }
    }

    @И("перезаполняет сведения об объекте проверки")
    public void refillInfoAboutCheckObject(Map<String,String> map) {
        HashMap<String, String> eventInfo= (HashMap<String, String>) Context.getSavedObject("Сведения об объекте проверки");
        for(HashMap.Entry<String, String> entry : map.entrySet()){
            new InformationAboutCheckObject().setTitleByCategory(entry.getKey(),entry.getValue());
            String key=entry.getKey();
            if(eventInfo.containsKey(key)){
                eventInfo.put(key,map.get(key));
            }
        }
    }

    @И("выбирает пункт меню {string}")
    public void chooseMenuTab(String tab) {
        new MainPage().goToTab(tab);
    }

    @И("выбирает согласованную заявку")
    public void chooseCompletedOpty() {
        checkWidgetExist("Мои текущие заявки");
        Opty opty=(Opty) Context.getSavedObject("Заявка");
        new OptysPage().chooseOpty(opty.getNumber());
        new InfoAboutOptyPage().checkAllWidgets();
        new InfoAboutOptyPage().checkAllOptyData((Map<String, String>) Context.getSavedObject("Информация о мероприятии"),
                (Organization) Context.getSavedObject("Организация"),
                (Map<String, String>) Context.getSavedObject("Сведения об объекте проверки"));
        checkButtonEnabled("Создать рекламацию");
        checkButtonEnabled("Заявка исполнена");
    }

    @И("выбирает заявку в очереди")
    public void chooseQueueOpty() {
        checkWidgetExist("Мои текущие заявки");
        Opty opty=(Opty) Context.getSavedObject("Заявка");
        new OptysPage().chooseOpty(opty.getNumber());
        new InfoAboutOptyPage().checkAllWidgets();
        new InfoAboutOptyPage().checkAllOptyData((Map<String, String>) Context.getSavedObject("Информация о мероприятии"),
                (Organization) Context.getSavedObject("Организация"),
                (Map<String, String>) Context.getSavedObject("Сведения об объекте проверки"));
        //checkButtonEnabled("Отправить на согласование");
    }

    @И("выбирает отправленную на доработку заявку")
    public void chooseRevisitedOpty() {
        checkWidgetExist("Мои текущие заявки");
        Opty opty=(Opty) Context.getSavedObject("Заявка");
        new OptysPage().chooseOpty(opty.getNumber());
        new InfoAboutOptyPage().checkAllWidgets();
/*        new InfoAboutOptyPage().checkAllOptyData((Map<String, String>) Context.getSavedObject("Информация о мероприятии"),
                (Organization) Context.getSavedObject("Организация"),
                (Map<String, String>) Context.getSavedObject("Сведения об объекте проверки"));*/
        checkButtonEnabled("Отправить на согласование");
    }

    @И("выбирает отклоненную заявку")
    public void chooseRejectedOpty() {
        checkWidgetExist("Мои текущие заявки");
        Opty opty=(Opty) Context.getSavedObject("Заявка");
        new OptysPage().chooseOpty(opty.getNumber());
        new InfoAboutOptyPage().checkAllWidgets();
        new InfoAboutOptyPage().checkAllOptyData((Map<String, String>) Context.getSavedObject("Информация о мероприятии"),
                (Organization) Context.getSavedObject("Организация"),
                (Map<String, String>) Context.getSavedObject("Сведения об объекте проверки"));
    }

    @И("очищает поля с данными о проверяемой организации")
    public void cleanAllOranizationData() {
        InformationAboutOrganizationPage page=new InformationAboutOrganizationPage();
        page.setTitleByCategory("Регистрационный номер","");
        page.setTitleByCategory("Наименование организации","");
        page.setTitleByCategory("ИНН","");
        page.setTitleByCategory("ОГРН","");
    }

    @И("вносит данные о мероприятии рекламации")
    public void fillInfoAboutComplaint(Map<String,String> map) {
        InfoComplaintPage informationInfoComplaintPage =new InfoComplaintPage();
        Map<String,String> map2= new HashMap<>();;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            informationInfoComplaintPage.setTitleByCategory(entry.getKey(),entry.getValue());
            if (!entry.getKey().equals("Утвержденная дата исполнения"))
                map2.put(entry.getKey(), entry.getValue());
        }
        Context.saveObject("Информация о рекламации",map2);
        checkTextInTextField("Срок исполнения","5");
    }
}
