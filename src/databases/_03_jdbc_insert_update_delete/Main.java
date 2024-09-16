package databases._03_jdbc_insert_update_delete;

import java.sql.*;

public class Main {

    public static void main(String[] args) {
        try {
            String url = "jdbc:sqlite:C:\\JMC17\\Java-Masterclass-11\\testjava.db";

            Connection conn = DriverManager.getConnection(url);
           // conn.setAutoCommit(false);
            Statement statement = conn.createStatement();

            statement.execute("CREATE TABLE IF NOT EXISTS contacts " +
                                    "(name TEXT , phone INTEGER , email TEXT)");

            // INSERTS
           /* statement.execute("INSERT INTO contacts (name,phone,email) " +
                    "VALUES('Joe',45632,'joe@anywhere.com')");

            statement.execute("INSERT INTO contacts (name,phone,email) " +
                    "VALUES('Jane',482984,'jane@somewhere.com')");

            statement.execute("INSERT INTO contacts (name,phone,email) " +
                    "VALUES('Fido',9038,'dog@email.com')"); */

            // UPDATES
            //statement.execute("UPDATE contacts SET phone = 5566789 WHERE name = 'Jane' ");

            // DELETE
            //statement.execute("DELETE FROM contacts WHERE name='Joe' ");

            // SELECT
            statement.execute("SELECT * FROM contacts");

            ResultSet rs = statement.getResultSet();

            while (rs.next()){
                System.out.println(rs.getString("name")+ " "
                        +rs.getInt("phone")+ " "
                        +rs.getString("email"));
            }

            rs.close();
            statement.close();
            conn.close();
        } catch ( SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }

    }
}
