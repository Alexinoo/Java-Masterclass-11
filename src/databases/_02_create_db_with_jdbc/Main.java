package databases._02_create_db_with_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Creating Databases with JDBC in Java
 * ....................................
 *
 * - Add SQLite JDBC Driver from this location C:\Users\ALEX\Documents\lib\sqlite-jdbc-3.46.1.0.jar
 *
 * - Project Structure > Libraries > Click (+) > Select Java > Navigate to the location where "sqlite-jdbc-3.46.1.0.jar"
 *   is located
 * - Click OK to proceed
 */
public class Main {

    public static void main(String[] args) {
        try {
            String url = "jdbc:sqlite:C:\\JMC17\\Java-Masterclass-11\\testjava.db";

            Connection conn = DriverManager.getConnection(url);
            Statement statement = conn.createStatement();

            statement.execute("CREATE TABLE contacts (name TEXT , phone INTEGER , email TEXT)");

            statement.close();
            conn.close();
        } catch ( SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }

    }
}
