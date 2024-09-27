package databases._04_executeQuery_using_constants;

import java.sql.*;

/*
 * executeQuery() and Using CONSTANTS
 * ..................................
 *
 * We performed a simple SELECT query and got the results to the console
 * There's actually an easier way to perform queries and to get the results
 * We can use the executeQuery() instead of the execute() which actually returns a ResultSet and we can update to use that instead
 *
 * Replace below
 *
 *      statement.execute("SELECT * FROM contacts");
 *      ResultSet resultSet = statement.getResultSet();
 *
 * With below 1 liner
 *
 *      ResultSet resultSet = statement.executeQuery("SELECT * FROM contacts");
 *
 * And if we run this, we get the same results and one of the advantage here is that we're using less code than when we're working
 *  with execute() , where we had to use 2 statements to achieve that
 *
 * ///////////////////////////////
 * Retrieving values from records
 * ///////////////////////////////
 *
 * We can also retrieve the values from a record using column indices rather than column names
 * Using column indices is much faster and preferred way to do it
 *      - won't change here, but we'll use them in future videos when we start working with the music db
 *
 * Since we now know how to do the basic CRUD operations , using JDBC , but quite frankly the code we've written can be improved quite a bit
 *
 *
 *
 * ///////////////////////////
 * Using Constants and Methods
 * ///////////////////////////
 *
 * When working with the command line, it was okay to hard code everything and, we had to since each SQL statement was executed in isolation
 * And we didn't have to worry about commenting outlines of code because only the SQL statement we just typed was executed
 *
 * Working with a database within an application though means that our statements aren't run in isolation
 * So we shouldn't have to type in the table and the column names every single time and in fact it's a bad practice to do so
 *
 * Just imagine we have completed the application that works with the contacts table in the testjava.db and then our manager or client
 *  tells us that we've decided to rename the column to "first_name"
 *
 * Since we have hardcoded everything, we'd have to find every instance of the name and update it to a first_name
 * This is trivial in our tiny application, but there's still the possibility for typos or for accidentally updating the wrong column name
 *  in a statement
 * Using hard-coded strings for our SQL statements also makes our application vulnerable to SQL Injection Attack
 *
 * So what do we usually do when we're using a hard-coded string in more than one place in our application code ?
 * Well we create CONSTANTS for the Strings and use them
 * If we want to rename something, then we'd only have to change the name in 1 place
 *
 * We can also improve code in another way, when we were adding records to the database, we repeated the same code several times
 * Repetitive code should always provoke a thought that we should write and use a method instead
 * Let's go ahead and update our code to use CONSTANTS and a method for insertions
 * We could create a separate class for the Strings constants we'll define so that any class in the application will be able to access
 *  them
 * But just to keep them Simple, we'll just go ahead and define them within the main class
 *
 * We'll use a Separate class when we're working with the music database later in this course
 *
 *
 * /////////////////
 * ADDING CONSTANTS
 * /////////////////
 *
 * So we'll add CONSTANTS for the
 *      - database name
 *
 *          public static final String DB_NAME = "testjava.db";
 *
 *      - connection string

 *          public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\JMC17\\Java-Masterclass-11\\"+DB_NAME;
 *
 *      - table name
 *
 *           public static final String TABLE_CONTACTS = "contacts";
 *
 *      - column names
 *
 *          public static final String COLUMN_NAME = "name";
            public static final String COLUMN_PHONE = "phone";
            public static final String COLUMN_EMAIL= "email";

 * And also update the code to use them
 *
 * We'll also drop the table when the application runs and un-comment all the code
 * So every time the application runs, we'll recreate the table from scratch and then perform the Insert, Update, Delete and Select
 *  statements
 * At the end of it all, we should see 3 rows printed to the console for Tim, Jane and Fido
 * Notice that we'll use IF EXISTS when we drop the table or we'll be using that to avoid an exception being thrown if the table doesn't
 *  exist
 * Let's proceed and make all these changes
 *
 *
 * ///////
 * Steps
 * //////
 *
 * Create a Connection obj
 *
 * Create a Statement object from the Connection instance
 *
 *      - Call execute() on Statement instance and pass DROP TABLE SQL statement for dropping contacts table if it exists
 *
 *      - Call execute() on Statement instance and pass CREATE TABLE SQL statement with the required columns
 *
 *      - call execute() on Statement instance and pass the INSERT SQL statement for the 3 records
 *
 *      - call execute() on Statement instance and pass the UPDATE SQL statement to update phone no for 'Jane'
 *
 *      - call execute() on Statement instance and pass the DELETE SQL statement to delete 'Joe' record
 *
 *      - call executeQuery() on Statement instance and pass the SELECT SQL statement to retrieve all the records and store that in a
 *          ResultSet
 *
 *          - loop through the resultSet and print out the CONSTANTS representing the respective names in the getString() and getInt()
 *
 * If we run this we now get the 3 records printed which indicates that the 3 records were inserted and we can confirm that from the DB Browser
 *
 *
 * ///////////////
 *  INSERT METHOD
 * ///////////////
 *
 * At the moment though, we have duplicated code for the 4 inserts statements
 * We'll look at the obvious way to do it but it won't be the best way and we'll revisit running the same SQL statements but with different
 *  values
 * Let's use a static method insertContact() because we're calling this from the main()
 *  - This method needs to throw SQL exception raised by the Statement obj and we need to catch the exception in the method that ultimately
 *     is going to be calling this
 *
 *  - Takes a Statement obj , name, phone and email as the parameters
 *
 *  - call execute() on the Statement obj and pass the INSERT SQL statement with the mapped column names
 *
 *
 * Then call insertContact(statement,name,phone,email) from the main and pass the 4 contacts
 *
 *      insertContact(statement ,"Tim",654678,"tim@email.com");
        insertContact(statement ,"Joe",45632,"joe@anywhere.com");
        insertContact(statement ,"Jane",4829484,"jane@somewhere.com");
        insertContact(statement ,"Fido",9038,"dog@email.com");
 *
 * And now if we run this, we get the same results
 *
 * As mentioned earlier, this is not the best way to do this and Tim will cover a better way
 *
 * Though at least for now we don't have the repetitive code and if we did have to make a change to say a column name , we can just do
 *  that from 1 place and the change would be reflected throughout the rest of the code without us having to change anything else
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

            // Inserting using the insertContact(Statement statement, String name , int phone , String email)
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
