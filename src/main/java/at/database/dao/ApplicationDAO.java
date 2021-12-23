package at.database.dao;

import at.database.DatabaseDAO;

import java.util.ArrayList;
import java.util.Map;

public class ApplicationDAO extends DatabaseDAO {

    private final String ApplicationTABLE ="APPLICATION";

    /**
     * Метод возвращающий номер созданной заявки
     *
     * @return
     */
    public String getOptyNumber() {

        String query = "select NUM from "+ ApplicationTABLE +" order by CREATED_DATE desc";

        stepAllureQueryText(query);
        ArrayList<Map<String, String>> result = this.database.select(query);

        return (result.size() > 0) ? result.get(0).get("NUM") : null;

    }

    /**
     * Метод возвращающий статус заявки по ее номеру
     *
     * @param optyNumber
     * @return
     */
    public String getOptyStatus(String optyNumber) {
        String query = "select STATUS_CD from "+ ApplicationTABLE +" WHERE NUM='"+optyNumber+"'";
        stepAllureQueryText(query);
        ArrayList<Map<String, String>> result = this.database.select(query);

        return (result.size() > 0) ? result.get(0).get("STATUS_CD") : null;

    }
}
