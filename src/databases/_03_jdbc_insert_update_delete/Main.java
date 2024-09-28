package databases._03_jdbc_insert_update_delete;
import java.sql.*;

/*
 * JDBC Insert, Update and Delete
 *
 *
 * The first time the user run this app, we want to create a table but running it the second or subsequent time , we don't want to
 *  create one and we'll actually get an SQL Error: table already exist
 *
 * So how do we handle this when our application needs to create the database and tables
 * We don't want to drop the table everytime the application runs because the user will loose whatever data they've added to that
 *  table
 * We can tell SQLite to only create the table if it doesn't already exist by adding IF NOT EXIST to the CREATE statement
 * SQLite will then try to add a table if the table doesn't already exist in the database
 *      - Add IF NOT EXISTS clause to CREATE TABLE statement
 * And now if we run this, we don't get any errors and the program is exiting normally as it should with no error
 *
 *
 * /////////////
 *  INSERT DATA
 * ////////////
 *
 * We'll need to use the INSERT statement, similar to the one we used in the command line
 * Once again in Java, we need to use a Statement obj
 *
 * We can re-use the statement that we've got because we are able to do that
 * An example of an INSERT statement would look like this
 *
 *      statement.execute("INSERT INTO contacts (name,phone,email) " +
                    "VALUES('Tim',45632,'joe@anywhere.com')");
 *
 * This works and if we switch to DB Browser, we can see now a record has been added to the database
 *
 *
 * /////////////
 *  Auto Commit
 * /////////////
 *
 * It's important to note that the JDBC Connection class commits any changes we make to the database for us immediately after a
 *  statement is executed
 * It's good to understand that depending on which database you're working with and what type of connection you use , sometimes you have
 *  to explicitly commit any changes you make to the database for those changes to persist
 * And if you don't commit them, closing the connection will also mean that we lose any changes we've made
 *  - In other words, they're rolled back
 * For example, if the JDBC Connection didn't commit changes for us, then after we added the row to the database, and closed the connection
 *  we wouldn't see the row added to the data table in the DB Browser
 * The Default behavior of the JDBC Connection objects is to auto-commit all changes
 *
 * We can set the connection not to auto commit changes for us
 * After the Connection instance is created, we can add the following line that turns auto commit off
 *
 *       conn.setAutoCommit(false);
 *
 * Add now if we try to add another record, and run the program, we don't get a record being added
 * And that's because we turned off auto commit by calling the above method and the Connection is no longer automatically committing changes
 *  to the database for us
 *
 * We can also call a method to commit those changes even with set auto commit off, but later in a future video
 * For now, we can comment out on the line above and work with the default auto commit behavior
 *
 * Add more rows with INSERT statement
 *  - Add contacts for Joe
 *  - Add contacts for Jane
 *  - Add contacts for Fido
 *
 * Confirm with the DB Browser that the 3 records have been added successfully
 *
 *
 * ///////////////
 * Update Command
 * ///////////////
 *
 * Let's now update Jane's phone number using the UPDATE SQL command
 * Comment out the inserts so that they don't get added the 2nd time
 *
 *      statement.execute("UPDATE contacts SET phone = 5566789 WHERE name = 'Jane' ");
 *
 * Again we're using single quotes for Strings so that we don't have to escape them and complicate this further
 * If we run this , we can confirm that the phone number for Jane was updated successfully
 *
 *
 * ///// Please Note
 * It's vital that we use a WHERE clause when we want to UPDATE or DELETE , otherwise all the rows in our table will be updated or
 *  deleted which can be very disastrous
 *
 *
 * ///////////////
 * Delete Command
 * ///////////////
 *
 * Let's now delete Joe from our contacts table using the DELETE SQL command
 * And we can do that using below statement
 *
 *      statement.execute("DELETE FROM contacts WHERE name='Joe'");
 *
 * And if we run this again, we can confirm that Joe's record has been deleted
 *
 *
 * ////////////
 *  Query Data
 * ////////////
 *
 * We've seen how to add, update and delete data from the contacts table
 * JDBC wouldn't be very useful if we couldn't query the data, and of course we can do that using a SELECT statement
 *
 * But how do we process the Query Result ?
 * Let's retrieve all the data from the contacts table using the Select * FROM contacts statement
 * The statement.execute() returns a boolean that will be
 *      - true, if the statement we executed returned an instance of the ResultSet class
 *      - false, if it returned an update count or no results
 *
 * When we execute an UPDATE statement, the execute() will return false because the return of an UPDATE statement is the number of rows
 *  that were updated
 * When we query the database, the method returns the records that match the query as a ResultSet instance
 * We can get the result set by calling the statement.getResultSet() and once we get that loop through the results
 *
 * Let's go ahead and retrieve some records but we need to comment out on the UPDATE and DELETE statements first
 *
        statement.execute("SELECT * FROM contacts");
 *      ResultSet resultSet = statement.getResultSet();
 *
 * Then we need to loop through the result set and get each record and print out name, phone and email
 *
 *      while(resultSet.getNext()){
 *           System.out.println(rs.getString("name")+ " "+rs.getInt("phone")+ " "+rs.getString("email"));
 *      }
 *
 * We get the value of the column by calling the appropriate method for the values type : String or int depending on which column
 *  we're working with as represented in the database
 *  - We call getInt() on an INTEGER field
 *  - We call getString() on a String field
 *
 * Then finally after the while loop call close() on the resultSet
 *
 *      resultSet.close();
 *
 * If we run this we get 3 records returned which means that this has worked fine
 *
 *
 * ///////////
 *  ResultSet
 * ///////////
 *
 * Every result has a cursor
 * This cursor isn't the same as a database cursor
 * We could have several result set objects and each one will have a cursor
 * However, and this is quite important, if we re-use a statement object to do a query , then any result set associated with that statement
 *  object is closed and a new one is created for a new query
 * So if we want to work with several query results at the same time then it's imperative to use a different Statement instance for each query
 *
 * Reusing the Statement instance is OK, when we're just doing INSERTIONS, UPDATES and DELETES because we weren't using or checking the result
 * We can reuse the Statement obj to query but only if we finish processing the results of one query before we execute the next query
 *
 * But keep in mind that a statement object can only have 1 active result set associated with it
 *
 * /////////////////
 * ResultSet Cursor
 * /////////////////
 *
 * When a ResultSet is created, it's cursor is positioned before the first record
 * So the first time we call resultSet.next() , the cursor will be moved to the first record
 * Then when we call it again, the cursor will be moved to the second record in the result set
 * When there's no more record, the next() will return false
 *
 * And when we call one of the resultSet get(), the values returned are those from the record at the result set's cursor's current position
 * Not that a resultSet is a resource, and we have to close it
 * It's associated with the Statement instance and we have to close it before we close the Statement instance
 *
 * We're closing all our resources at the end of the try block
 * If an exception is thrown, it's possible that the close() won't be executed even though some or all other resources have been created
 * For our small application, it doesn't matter
 * When the database objects are destroyed, they'll do the appropriate cleanup
 * The close() forces the closing of the database resource immediately rather than waiting for them to be automatically closed
 * Because of that, we don't have to close them ourselves but it's actually a good practice to do so
 *
 * If our code belong to a large application, where there are lots of open connections, the result sets and statements , we'd have to a bit more
 *  diligent and make sure the resources were closed as soon as we no longer need them
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

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
