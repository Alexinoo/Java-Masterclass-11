package databases._01_jdbc_sqlite_GUI_browser;

/*
 * JDBC and SQLite GUI Browser
 * ............................
 *
 * //////////
 * Overview
 * //////////
 *
 * We've seen how to work with an sqlite database from the command line
 * We'll learn how to use a database from a Java application
 * We do so using the JDBC API
 * JDBC stands for Java Database Connectivity
 * Using JDBC, we can not only work with databases, but also spreadsheets and flat files
 *
 * Think of JDBC as a middleman between a Java application and a data source.
 * To use a particular data source from an application , we need the JDBC driver for the data source
 * For example, to access an sqlite database from an application, we need an sqlite JDBC driver
 *
 * The driver is simply a Java library containing classes that implement the JDBC API
 * Because all JDBC drivers have to implement the same interfaces, it's not difficult to change the data source an application uses
 * For example:
 *  If an application uses an sqlite database, and then we decide later that we want to use a MySQL databases, all we have to do is
 *   use the MySQL JDBC driver, instead of the sqlite one (in addition to migrating the data to a MySQL DB, of course)
 *
 * In reality, it's not that simple.
 * Nothing is ever 100% portable. But if we write our JDBC code with the thought in mind that we may change the data source later, we
 *  can make our lives easier by avoiding the use of database-specific SQL and behaviors wherever possible
 *
 * If we wanted to work with a spreadsheet or a flat file, that's fine as long as there's a JDBC driver that works with the spreadsheet
 *  or file format
 * We can always write the JDBC driver ourselves, if necessary
 *
 * The JDBC driver has to be written in Java, but it can consist of a thin Java layer that calls code written in other languages
 * The current version of the JDBC API is JDBC 4.2
 * For backwards compatibility, it contains all the methods that were in previous JDBC versions
 *
 * There have been changes to the way JDBC works, so make sure you're working with a driver that supports JDBC 4.0
 * If you use the one we'll download in this lecture, you'll be fine
 *
 * JDBC consists of 2 packages:
 *     1. java.sql (core JDBC)
 *     2. javax.sql (optional JDBC)
 * The APIs in the javax.sql are required when working with database servers and we'll talk about those later
 *
 * All the popular databases provide JDBC drivers.
 * The JDK ships with a database called derby, which can be used for desktop applications, or when prototyping
 * The derby JDBC driver is also included in the JDK
 *
 * We're going to start by using a sqlite database, so we'll use an sqlite JDBC driver
 * The driver we'll use comes packaged with sqlite, which means we don't have to install the database separately.
 * We won't use the sqlite that we installed in previous video
 *
 *
 *
 * ///// Download SQLite Driver /////
 *
 * Since we want to add the driver to the projects we create, when you download it, make sure to save it somewhere that you'll
 *  remember
 * Follow the link to download the driver and chose the latest driver:
 *
 *      https://sourceforge.net/projects/sqlite-jdbc-driver.mirror/
 *
 * There are other SQLite JDBC drivers.
 * But we're using this one because it contains the SQLite database and doesn't require any configuration
 * Because we'll add the jar files to any projects there won't be any conflicts with the SQLite we installed to use from the command
 *  line
 * So our application will use the version that's in the jar file
 * We'll use the DB browser for SQLite application, which is basically an application that's got a GUI interface that we can access
 *  SQLite databases for
 *
 *
 *
 * /////// Install DB Browser ///////////////
 *
 * Let's install DB Browser on Windows OS and then create a Java project using the SQLite JDBC drivers to access databases from within
 *  java code
 * Install SQLite browser for Windows
 *
 * Link:
 *
 *      https://sqlitebrowser.org/dl/
 *
 * Select below installer
 *      "DB Browser for SQLite - Standard installer for 64-bit Windows"
 *
 * Run the executable and continue with the installation
 *      - Proceed with defaults
 *
 * Open the DB Browser for SQLite utility that we're going to be using in future videos
 */
