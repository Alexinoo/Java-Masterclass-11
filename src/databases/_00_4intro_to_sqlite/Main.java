package databases._00_4intro_to_sqlite;

/*
 * Introduction to SQLite
 * ......................
 *
 * Open cmd
 *  - cd C:\Users\alex.wakanyi.STL-HORIZON\sqlite
 *
 * Type sqlite3
 *  - we can specify the name of the database on the command line
 *  - e.g.
 *      sqlite3 test.db
 *  - There's also a command to open a database file if you forget to put the name on the command line and we'll look into that later
 *
 * SQLite program is a fairly minimal interface that just really tells us the version of SQLite that we're using and we can use .help
 *  to get the instructions
 * We can also enter SQL statements and with some versions, there's also a helpful reminder, that SQL statements must be terminated with
 *  a semicolon
 *
 *
 * Type .help
 *  - They're a quite a number of different command options available for us that we can work with
 *  - But there is a bigger chance we won't remember all of them and so .help is a useful way to remind yourself of them if you never need
 *    to go back and see what a particular command is all about
 *  - Before creating a new table in the database, we may want to type
 *
 * .headers on
 *  - this shows the column names at the start of the data which is a handy reminder of what we called columns
 *
 * - These are one of the list of the commands that SQLite recognizes but when creating and querying tables, we just use SQL statements
 *
 * //////////////////////
 * CREATE TABLE CONTACTS
 * //////////////////////
 *
 * - Create a Simple contacts table
 *
 *     sqlite> CREATE TABLE contacts (
 *          name text,
 *          phone integer,
 *          email text
 *      );
 * - Note that when you press enter, nothing happens nad that means a table has been created
 *
 * - With SQLite, if you do something wrong , it will let you know, however if you do something right, it just keeps quiet which means that
 *   the command worked just fine
 *
 * - Note that we probably shouldn't be storing the phone number in an integer column , but a phone number is really best stored as a text
 *    field
 *
 * - SQLite doesn't have types for it's fields and it's quite strange in that respect
 * - Although you specify a type when defining the columns , that's really just uh what you intend to put into them
 * - And because it's a SQLite implements standard SQL, it has to use that standard form for creating tables where the columns have a data
 *    type
 * - But you can put any kind of data into any column
 *
 * /////////////////////
 * INSERT DATA
 * /////////////////////
 *
 * Add some data into contacts table
 *
 *     sqlite> INSERT INTO contacts(name,phone,email) VALUES('Tim',23456,'tim@email.com');
 *
 * Again we get no confirmation that it worked but in this case no news is good news
 * We used single quotes here but we can also use double quotes
 * What we need to remember is if we're embedding SQL commands in Java, it makes sense to use double quotes for the Strings and single quotes
 *  around the SQL statements
 * We'll see Tim do that when it comes to creating programs that work on our databases
 *
 *
 * /////////////////
 *  QUERY DATA
 * ////////////////
 *
 *     sqlite> SELECT * FROM contacts;
 *
 * THe above statement returns the record which we've just inserted
 *
 * We use SELECT statement and is very useful in SQL, Sequel and it specifies how we query the data in a table
 * It's a very simple command but at it's simplest , you can just tell it what columns you want and the name of the table to get it from
 * We'll write SQL reserved words in UPPERCASE and it's actually useful to do that especially in programs and scripts but to
 *  SQL itself doesn't care
 * People generally just do it to make it obvious, which are SQL reserved words and which are things like tables and column names
 *
 * The * wildcard/asterisk character means all columns , but we can also be explicit and specify the column names as follows
 *
 *     sqlite> SELECT name,phone,email FROM contacts;
 *
 * And this will give us the same results
 *
 * But if we wanted email addresses , for example, we could just do
 *
 *     sqlite> SELECT name FROM contacts;
 *
 * And this will just give us the email
 *
 * /////////////////////////////////////////////
 * Terminating SQL statements with a semicolon
 * /////////////////////////////////////////////
 *
 * We can type the same command again but this time we simulate to forget to add a semicolon at the end
 * Nothing is printed and SQLite has put another prompt up waiting for more input
 *
 *      ...>
 *
 * Which is different from the one that starts with a greater sign that normally starts the command prompt when we're about to type a
 *  command in
 * We can add other clauses after SELECT and it's nice to be able to split them onto different lines to make it more readable
 * So SQLite will let you keep on typing an SQL command and won't try to execute it until you type a semicolon
 * If we type a semi-colon, the statement executes and we get the email address for our 1 record
 *
 * So when we forget the semi-colon, just type it on the next line , and generally, everything will work fine
 *
 *
 * ///////////
 * - Add a couple of additional records , and use double quotes for the first one to show us that it still works fine
 *
 *      sqlite> INSERT INTO contacts VALUES("Brian",23456,"brian@myemail.com");
 *
 * - Notice that this is a slightly form of the INSERT statement and because we're providing values for all the fields and giving them
 *   in the order that the fields were defined in the table, there's no need in this case to specify the list of fields
 *
 * But if we try
 *
 *      sqlite> INSERT INTO contacts VALUES("Steve",87654);
 *
 *      - we'll actually get an error because we've only specified 2 values but the table contacts has got 3 columns
 *      - we can fix that by adding another value but if we don't know the email address then that wouldn't be an option
 *      - Instead we can specify which columns we want to add data for and just provide the value to insert into those columns
 *
 *      - That is
 *
 *      sqlite> INSERT INTO(name,phone) contacts VALUES("Steve",87654);
 *
 *      - and we don't get an error in this case and we can now check again what is in the table
 *
 *      sqlite> SELECT * FROM contacts;
 *
 *      - and we gte 3 entries returned
 *
 * Note that Tim and Brian have got email addresses but Steve doesn't
 *
 *
 */
