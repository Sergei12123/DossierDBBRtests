package at.database.dao;

import at.database.DatabaseDAO;

import java.util.ArrayList;
import java.util.Map;

public class DAOTest extends DatabaseDAO {
    /**
     * Метод возвращающий статус продукта по номеру, категории и подкатегории
     *
     * @return
     */
    public String getRequestStatus() {
        String query = "select * from ACT_GE_PROPERTY";
        stepAllureQueryText(query);
        ArrayList<Map<String, String>> result = this.database.select(query);
        return (result.size() > 0) ? result.get(0).get("name") : null;
    }
}
