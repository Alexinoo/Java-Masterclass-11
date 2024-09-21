package databases._04_executeQuery_using_constants;

import java.sql.*;

/*
 * executeQuery() and Using CONSTANTS
 * .....................................
 *
 * - Uses less code than execute()
 *
 * - Using column indices rather than column names to retrieve values from the record
 * - Using column indices is much faster and preferred way to do it
 *      - won't change here, but we'll use that in future videos when we start working with the music db
 *
 * - update our code to use CONSTANTS
 *
 * - Drop table and recreate the table again
 *
 * //////
 * - Use a method to insert records instead of doing the duplication of insert statements
 */

public class Main {

    public static final String DB_NAME = "testjava.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\JMC17\\Java-Masterclass-11\\"+DB_NAME;
    public static final String TABLE_CONTACTS = "contacts";

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL= "email";
    public static void main(String[] args){
        Connection conn = null;
        Statement statement = null;
        ResultSet results = null;
        try{
            conn = DriverManager.getConnection(CONNECTION_STRING);
            statement = conn.createStatement();

            //Drop table if exists
            statement.execute("DROP TABLE IF EXISTS "+ TABLE_CONTACTS);

            //recreate afresh and populate
            statement.execute("CREATE TABLE IF NOT EXISTS "+ TABLE_CONTACTS+
                    " ("+COLUMN_NAME+" text , "+
                        COLUMN_PHONE+" integer, "+
                        COLUMN_EMAIL+" text)");

            //insert the 3 records - manually
            /*statement.execute("INSERT INTO "+ TABLE_CONTACTS+
                    " ("+COLUMN_NAME+","+COLUMN_PHONE+","+COLUMN_EMAIL+ ")"+
                    "VALUES('Tim',654678,'tim@email.com')");

            statement.execute("INSERT INTO "+ TABLE_CONTACTS+
                    " ("+COLUMN_NAME+","+COLUMN_PHONE+","+COLUMN_EMAIL+ ")"+
                    "VALUES('Joe',45632,'joe@anywhere.com')");

            statement.execute("INSERT INTO "+ TABLE_CONTACTS+
                    " ("+COLUMN_NAME+","+COLUMN_PHONE+","+COLUMN_EMAIL+ ")"+
                    "VALUES('Jane',4829484,'jane@somewhere.com')");

            statement.execute("INSERT INTO "+ TABLE_CONTACTS+
                    " ("+COLUMN_NAME+","+COLUMN_PHONE+","+COLUMN_EMAIL+ ")"+
                    "VALUES('Fido',9038,'dog@email.com')"); */

            // Inserting using the insertContact()
            insertContact(statement ,"Tim",654678,"tim@email.com");
            insertContact(statement ,"Joe",45632,"joe@anywhere.com");
            insertContact(statement ,"Jane",4829484,"jane@somewhere.com");
            insertContact(statement ,"Fido",9038,"dog@email.com");

            // update Jane's phone number
            statement.execute("UPDATE "+TABLE_CONTACTS+ " SET "+
                    COLUMN_PHONE+ "=5566789" +
                    " WHERE "+ COLUMN_NAME+ " = 'Jane'");

            // Delete Joe record
            statement.execute("DELETE FROM "+TABLE_CONTACTS+
                    " WHERE "+ COLUMN_NAME +" = 'Joe'");

            // query from the table contacts
            results = statement.executeQuery("SELECT * FROM "+ TABLE_CONTACTS);
            while (results.next()){
                System.out.println(results.getString(COLUMN_NAME)+ " "+
                        results.getInt(COLUMN_PHONE)+" "+
                        results.getString(COLUMN_EMAIL));
            }

        }catch(SQLException exc){
            exc.printStackTrace();
        }finally {
            if (conn != null && results != null){
                try {
                    results.close();
                    statement.close();
                    conn.close();
                }catch (SQLException exc){
                    exc.printStackTrace();
                }
            }
        }
    }

    private static void insertContact(Statement statement , String name, int phone , String email) throws SQLException{
        statement.execute("INSERT INTO "+ TABLE_CONTACTS+
                " ("+COLUMN_NAME+","+COLUMN_PHONE+","+COLUMN_EMAIL+ ")"+
                "VALUES('"+name+"',"+phone+",'"+email+"')");
    }
}
