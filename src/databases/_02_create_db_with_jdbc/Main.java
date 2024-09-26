package databases._02_create_db_with_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Creating Databases with JDBC in Java
 * ....................................
 *
 * One thing to note about is that if you make changes to the database, it'll actually lock the database file and you'll no longer
 *  be able to access the database from a Java application
 * If that happens, we can go back to DB Browser for SQLite and go to File > Close Database and that will release the lock so that
 *  you'll be able to use it again
 *
 *
 * //// Create testjava.db /////
 *
 * Let's write some Java code and we'll start by creating the same test database we created from the command line to see how to
 *  connect to a database using JDBC
 *
 * /// Add SQLite JDBC Driver
 *
 * Locate the place where the SQLite JDBC Driver from this location C:\Users\ALEX\Documents\lib\sqlite-jdbc-3.46.1.0.jar
 *
 * Navigate to
 *
 *      File > Project Structure > Libraries > Click (+) > Select Java > Navigate to the location where "sqlite-jdbc-3.46.1.0.jar" was
 *      downloaded to
 *
 * Click OK to successfully add it to the project
 *
 *
 * //////////////////
 * //// Did not work in my case - Needed to add slf4j-api-1.7.36 to my class path //////
 *
 * I also downloaded the "slf4j-api-1.7.36" file
 *
 * Go To
 *      Run > Edit Configurations > Click Application > Create a new configuration
 *
 * Go To
 *      Modify options > Modify classpath > Include > Navigate to the location where the file you want to add is located
 *
 * ///////////////
 * Create Database
 * ///////////////
 *
 * The first thing we want to do is to create the database
 * In a real-world application, often a database will ship with the application or the database will exist on a server somewhere
 * Sometimes you may want to create the database the first time it runs or perhaps the first time a user does something
 *
 * When using SQLite, connecting to a database that doesn't exist will create the database and we don't really have to create the
 *  database in a separate step
 * We just have to try and connect to the database , and if it doesn't exist, SQLite will create it for us
 *
 * All JDBC Drivers need a Connection String that they'll use to connect to the database
 * The exact format of the connection string will vary from database to database , but one thing they'll always have in common is
 *  that it starts with "jdbc:"
 * For SQLite, we follow jdbc colon, with sqlite colon and then we specify the location of the database
 * Other databases may well require additional info , e.g. a username and password
 *
 * We can also specify database attributes with the Connection String
 * We may specify that we want a SQLite database to be stored in memory
 * To find out what is required in a Connection String for a given database, we can check the JDBC drivers documentation
 * Nothing is 100% portable and this is one area that varies from database to database
 *
 *
 * ///////// Establish the Connection ////
 *
 * Use a try block and catch SQLException
 *
 * Create a Connection obj by calling getConnection(String url) from the java.sql.DriverManager class
 *
 *      - getConnection(String url) takes the Connection String and returns a Connection obj
 *
 * This method may throw a SQLException and that's why we're trying to catch that by printing out an exception message
 * If the JDBC driver isn't on the class path, then we'll get a ClassNotFoundException
 *
 * This is the new way of connecting to the database when using JDBC 4.0 and above
 * When using earlier versions, we had to register and load JDBC drivers by calling class.forName() with the driver class name
 *
 * We'll see lots of older examples around the internet that uses forName() and that's just one matching in here now
 * We'd see something like
 *
 *      Class.forName("org.sql.JDBC");
 *
 *      - a little bit weird but we don't need to do that anymore
 *
 *
 * //////
 * There's actually 2 ways of establishing connection using JDBC 4.0
 *  1. Using DriverManager.getConnection(String url)
 *  2. Using data source objects
 *
 * The latter is sometimes the preferred way , and that's because
 *
 *  1. it allows advanced features like connection pooling and distributed transactions
 *  2. it's more portable because of the way connections are established
 *
 * We don't need these features when using SQLite & they require that a database administrator set up the database to enable use
 *  of these features
 * It's also a lot more complicated to use data source objects and they're really only needed when working with large enterprise
 *  applications particularly jee or java enterprise edition applications
 *
 * We'll stick to DriverManager and not data source objects which are majorly used in enterprise applications
 *
 * Running this creates "testjava.db" which is a database file that's being automatically created for us since it didn't exist
 *
 *
 * ///// Create Contacts Table
 *
 * We'll create contacts table that we created when we're working with the test database from the command line
 * Whenever we want to use SQL with JDBC , we use what's called Statement objects
 *
 * To create a table, we'll call connection.createStatement()
 *
 *      Statement statement = conn.createStatement();
 *
 *      - and then we'll use statement.execute() to run a SQL statement

        statement.execute("CREATE TABLE contacts (name TEXT , phone INTEGER , email TEXT)");
 *
 *      - which in this case is the CREATE TABLE sql statement
 *
 * So after getting the Connection instance we're creating the Statement instance
 * We're then calling the execute() and we're passing the SQL command that we want to execute in this case the SQL to create contacts
 *  table which is the same command that we used when creating the command line
 * Notice that we didn't have to use a semicolon at the end of the statement and that's because the driver understands that when we call
 *  execute , we've passed a complete SQL statement
 * It's also important to note that we created the Statement object by calling a method on the Connection obj/instance
 * So the statement as such is associated with the connection and can only be run against the database we connected to when we created the
 *  Connection instance
 *
 *
 * ///////
 *
 * This runs without any errors and if we swing back to DB Browser, if we open our database from the DB Browser for SQLite , you'll see now
 *  that we've got a contacts table, with all the fields that we defined
 *
 * The Collection of statement objects use database resources and so when we finish them we should actually close them and if we don't the
 *  Java Runtime will eventually close them for us
 * It's a good programming practice to close a resource as soon as we finish using it and we're sure we don't need it again
 * In trivial applications like this one, it doesn't matter , but in large applications that use multiple database connections, if you're
 *  not closing resources that you don't need, it's possible that you'll run out of resource and the app may no longer be able to work with
 *  the database or perhaps the performance of the application will degrade
 *
 * One of the thing we can do is to use the newest style of the try statement, and we could get that to close for us automatically
 * Check the commented code for reference that is using try-with-resources
 * And the advantage of this is that the resources will be closed when the try-catch block is exited
 * That's another way of doing this that Tim recommends
 *
 * But we'll explicitly close any resource that we no longer need to drive it home that it must be done this way
 * It's also good for us to get into the habit of just closing this manually
 *
 *
 * ////// Closing resources
 *
 * So basically after the execute(), we may want to do a
 *
 *      statement.close();
 *
 *   - and then
 *
 *      conn.close();
 *
 * The order in which we close the database resources is important
 * We should close any statement instances before we close the connection
 * If you think about it, that's logical, because if we close the connection first, then the database has been closed and actually
 *  what will happen is that the SQLException will be thrown with the message "SQL Error or missing database"
 * And again that's because the connection is closed
 *
 * Remember that a statement is associated with a connection , and so it's sort of hanging on and using that connection
 * So if you're closing the connection, we can't actually use any statements that were associated with it
 *
 *
 */
public class Main {

    public static void main(String[] args) {

        String url = "jdbc:sqlite:C:\\JMC17\\Java-Masterclass-11\\testjava.db";
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement statement = conn.createStatement();

            statement.execute("CREATE TABLE contacts (name TEXT , phone INTEGER , email TEXT)");

            statement.close();
            conn.close();
        } catch ( SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }

        // Using try with resources
        /* try(Connection conn = DriverManager.getConnection(url);
            Statement statement = conn.createStatement()) {

            statement.execute("CREATE TABLE contacts (name TEXT , phone INTEGER , email TEXT)");
        } catch ( SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        } */

    }
}
