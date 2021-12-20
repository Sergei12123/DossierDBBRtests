package at.models;

import at.managers.DatabaseManager;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.testng.Assert;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;


@Getter
@Setter
public class Database {
    @SerializedName("Алиас БД")
    private String alias;
    @SerializedName("Драйвер")
    private String driver;
    @SerializedName("Хост")
    private String host;
    @SerializedName("Порт")
    private String port;
    @SerializedName("Сервис")
    private String service;
    @SerializedName("Логин")
    private String login;
    @SerializedName("Пароль")
    private String password;
    @SerializedName("schema")
    private String schema="DBBR";

    private int sqlQueryTimeout = 300;

    public Database(String alias, String driver, String host, String port, String service, String login, String password){
        this.alias = alias;
        this.driver = driver;
        this.host = host;
        this.port = port;
        this.service = service;
        this.login = login;
        this.password = password;
        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Map<String, String>> select(String user, String password, String sqlQuery) {
        Connection connection = getConnection(user, password);
        Assert.assertNotNull(connection, "Не удалось установить соединение с базой данных!");
        System.out.println("SQL QUERY:\n  " + sqlQuery.replaceAll("\\n", "\n  "));
        System.out.println("Execute SQL QUERY");

        Throwable var6;
        try {
            Statement statement = connection.createStatement();
            var6 = null;

            try {
                statement.setQueryTimeout(sqlQueryTimeout);
                statement.execute("alter session set current_schema="+schema);
                ResultSet resultSet = statement.executeQuery(sqlQuery);
                System.out.println("  Success");
                ResultSetMetaData metadata = resultSet.getMetaData();
                int columnCount = metadata.getColumnCount();
                ArrayList result = new ArrayList();

                while(resultSet.next()) {
                    Map<String, String> row = new TreeMap(String.CASE_INSENSITIVE_ORDER);

                    for(int i = 1; i <= columnCount; ++i) {
                        row.put(metadata.getColumnName(i), resultSet.getString(i));
                    }

                    result.add(row);
                }

                ArrayList var33 = result;
                return var33;
            } catch (Throwable var29) {
                var6 = var29;
                throw var29;
            } finally {
                if (statement != null) {
                    if (var6 != null) {
                        try {
                            statement.close();
                        } catch (Throwable var28) {
                            var6.addSuppressed(var28);
                        }
                    } else {
                        statement.close();
                    }
                }

            }
        } catch (SQLException var31) {
            System.out.println("  QUERY failed");
            var31.printStackTrace();
            this.assertQueryTimeout(var31.getMessage());
            Assert.fail("Не удалось выполнить SQL запрос!");
            var6 = null;
        } finally {
            this.closeConnection(connection);
        }
        return null;
    }

    public ArrayList<Map<String, String>> select(String sqlQuery) {
        return this.select(login, password, sqlQuery);
    }


    private Connection getConnection(String user, String password) {
        StringBuilder url=new StringBuilder();
        Database db=DatabaseManager.getDatabase(alias);
        url.append("jdbc:").append(db.getDriver()).append(":thin:@//").
                append(db.getHost()).append(":").append(db.getPort()).
                append("/").append(db.getService());
        if (user != null && !user.trim().isEmpty()) {
            if (password != null && !password.trim().isEmpty()) {
                try {
                    return DriverManager.getConnection(url.toString(), user, password);
                } catch (Exception var4) {
                    System.out.println("Attempt to get database connection failed");
                    var4.printStackTrace();
                    Assert.fail("Не удалось установить соединение с базой данных!");
                    return null;
                }
            } else {
                System.out.println("Password is not specified");
                Assert.fail("Пароль пользователя базы данных не задан!");
                return null;
            }
        } else {
            System.out.println("User is not specified");
            Assert.fail("Пользователь базы данных не задан!");
            return null;
        }
    }

    public void update(String user, String password, String sqlUpdate) {
        Connection connection = this.getConnection(user, password);
        Assert.assertNotNull(connection, "Не удалось установить соединение с базой данных!");
        System.out.println("SQL UPDATE:\n  " + sqlUpdate.replaceAll("\\n", "\n  "));
        System.out.println("Execute SQL UPDATE");

        try {
            Statement statement = connection.createStatement();
            Throwable var6 = null;

            try {
                statement.setQueryTimeout(this.sqlQueryTimeout);
                statement.execute("alter session set current_schema="+schema);
                statement.executeUpdate(sqlUpdate);
                System.out.println("  Success");
            } catch (Throwable var24) {
                var6 = var24;
                throw var24;
            } finally {
                if (statement != null) {
                    if (var6 != null) {
                        try {
                            statement.close();
                        } catch (Throwable var23) {
                            var6.addSuppressed(var23);
                        }
                    } else {
                        statement.close();
                    }
                }

            }
        } catch (SQLException var26) {
            System.out.println("  UPDATE failed");
            var26.printStackTrace();
            this.assertQueryTimeout(var26.getMessage());
            Assert.fail("Не удалось выполнить SQL UPDATE!");
        } finally {
            this.closeConnection(connection);
        }

    }

    public void update(String sqlUpdate) {
        this.update(login, password, sqlUpdate);
    }

    private void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException var3) {
            System.out.println("Close connection failed");
            var3.printStackTrace();
            Assert.fail("Не удалось закрыть соединение с базой данных!");
        }

    }
    private void assertQueryTimeout(String exceptionMessage) {
        if (exceptionMessage != null && exceptionMessage.replace("\n", "").equals("ORA-01013: user requested cancel of current operation")) {
            throw new SQLTimeoutError("Превышено максимальное время ожидания выполнения SQL запроса!");
        }
    }
    public class SQLTimeoutError extends Error {
        SQLTimeoutError(String message) {
            super(message);
        }
    }
}
