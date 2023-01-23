package Person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TestUtil {
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("Database");
    public static final String URL = RESOURCE_BUNDLE.getString("URL");
    private static Connection connection;

//    public static void getConnection() throws SQLException {
//        connection = DriverManager.getConnection(URL);
//        connection.setAutoCommit(true);
//    }

    public static void closeConnection() throws SQLException {
        connection.close();
    }


    public static void createPersonTable() throws SQLException {
        connection = DriverManager.getConnection(URL);
        connection.setAutoCommit(false);
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE Person (" +
                    "id int NOT NULL PRIMARY KEY AUTO_INCREMENT ," +
                    "name varchar(25) null," +
                    "surname varchar(25) null)");

            preparedStatement.executeQuery();
            preparedStatement.close();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        }
    }
}
