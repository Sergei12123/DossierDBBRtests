package at.managers;


import at.models.Database;


/**
 * Класс содержит методы для работы с базой данных
 */
public abstract class DatabaseManager {

    private static ThreadLocal<Database> databaseThreadLocal = new ThreadLocal<>();

    /**
     * Метод устанавливает Database
     *
     * @param database Database atc.crm.soui:utility
     * @param alias обозначение даты данных
     */
    public static void setDatabase(Database database, String alias) {
        switch (alias){
            case "dbbr_db" : { databaseThreadLocal.set(database); break; }
            default : { break; }
        }
    }

    /**
     * Метод возвращает Database
     *
     * @param alias обозначение даты данных
     * @return Database
     */
    public static Database getDatabase(String alias) {
        switch (alias){
            case "dbbr_db" : { return databaseThreadLocal.get(); }
            default : { return null; }
        }
    }

    public static Database getDatabase(String alias, String driver, String host, String port, String service, String login, String password) {
        setDatabase(new Database(alias,driver, host, port, service, login, password), alias);
        return getDatabase(alias);
    }




}