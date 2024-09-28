package databases._00_5more_on_sqlite;

/*
 * More on SQLite
 * ..............
 *
 * We've seen that we can put any kind of data into any column in SQLite which is a bit strange
 *
 * Let's now add an insert with any kind of data into these columns
 *
 *  sqlite> INSERT INTO contacts VALUES("Avril", "+61 (0)87654321", "avril@email.com.au");
 *
 * We can see that it works yet we've put a String data into an integer column which is actually believe it or not fine in
 *  SQLite
 * We've entered a String where a number would ordinarily have been placed
 *
 * I am actually horrified by what i've just seen and in fact doing things like that can cause problems , when you try to get out
 *  the data back from a program
 * If our code tries to put that phone number into an integer variable, then it is going to crash
 * If you switch databases and try to to use the same code in say Microsoft SQL server, then it won't work either because the main client
 *  server SQL databases do actually check the type of data that goes into columns
 * So make sure you use an appropriate type for the columns when you create your tables
 *
 * /// ALTER TABLE COMMAND ///
 * - One thing that SQLite lacks is ALTER TABLE command for changing things like the type of the columns
 * - There's actually ways around that creating a new table and moving the data from the old table into it for example, but it's really best
 *   to get it right the first time
 *
 *
 *
 * /////// ////////
 * UPDATE command
 * ////////////////
 *
 * We can also update the data that's in there
 *
 * Firstly, we'll use .backup command to back up the table and we'll see why in a minute
 *
 * The command name is .backup then you tell it which database to back up and then the file name we
 *  want to back up to
 *
 * If you don't tell it the database that you want to back up, then does the current one which is fine and this makes this command very
 *  easy to use
 *
 *      sqlite> .backup testbackup
 *
 *  - Note that this is a SQLite command and not a SQL statement and therefore there's no need to end it with a semicolon
 *  - If it starts with a dot, it's a SQLite command , . first or semicolon last but not both
 *
 * - Let's say we now have Steve email address, and we want to update his record in the table
 * - We do that using the UPDATE statement
 *
 *      sqlite> UPDATE contacts SET email="steve@hisemail.com";
 *
 * - Need to be very careful with this command because at the moment if we haven't told it which row to update, so it's going to update
 *   every row in the table
 * - IF we press Enter and retrieve the columns again, you'll find that everyone has the same email address  which is almost certainly not
 *    what we want to happen
 * - The UPDATE command is a very powerful command and a single SQL statement can update hundreds of thousands of rows in a database
 * - You may want to be very careful when using the UPDATE command especially in an interactive session like this
 *
 * - But luckily this time we've backed up the database first and we can get it back and do the UPDATE properly
 *
 *      sqlite> .restore testbackup
 *
 * - And now we got our data back with the original entries before we did the update
 *
 * - So how do we do update just Steve's record ?
 * - Well to do that what we need to do is we still need to use the UPDATE command but we need to add a WHERE clause
 *
 *      sqlite> UPDATE contacts SET email="steve@hisemail.com" WHERE name="Steve";
 *
 *  - And now only Steve record has been updated
 *  - SO that's how to use a WHERE clause , it's just a word WHERE followed by a condition that identifies a row or set of rows to be updated
 *
 *  - That's why backups are also very important
 *
 *
 * //////////////
 * WHERE CLAUSE
 * //////////////
 *
 * - WHERE clause can be used with many SQL statements, and you could display a subset of the data by using a WHERE clause with the SELECT
 *   statement
 * - We can also use a WHERE clause in a SELECT statement
 *
 *      sqlite> SELECT * FROM contacts WHERE name = "Brian";
 *
 *      - And this returns only 1 entry related to Brian
 *
 * - Perhaps more useful though, if we already know the name, there's no point retrieving data that we don't need and so we could do something
 *   like
 *      sqlite> SELECT phone,email FROM contacts WHERE name = "Brian";
 *
 *      - And this just returns the phone number and the email address of Brian
 *
 * ///////////////
 *  DELETE CLAUSE
 * ///////////////
 *
 * - We can also DELETE records using a DELETE SQL statement
 *
 *      sqlite> DELETE FROM contacts;
 *
 * - Once again we have to be very careful, without a WHERE clause to specify which rows should be deleted, the command will apply to the
 *   entire set of rows in the database
 * - So put in a WHERE clause here
 *
 *      sqlite> DELETE FROM contacts WHERE phone = 1234;
 *
 * - And we know the phone no, 1234 belongs to Brian
 * - And if we now do the SELECT statement, you'll see that Brian is now missing from that list, because we've successfully deleted his
 *   record using the DELETE statement and using a WHERE clause which specified his phone no
 *
 *
 * /////////////////
 * SQLite commands
 * /////////////////
 *
 * - We can use some other SQLite commands once everything's setup
 *
 * .tables
 *      - list all the tables in the database which can be handy when you have a lot of them and forget what you've called one
 *
 * .schema
 *      - prints out the schema of our tables
 *      - we only have 1 table in this database but we can see how it shows the SQL command that was used to create it
 *      - You may want to copy that command and paste it into code when you want to create tables in code
 *
 * .dump
 *      - gives the sql statement for creating table and also the inserts necessary to populate it with the data that's in it
 *      - it wraps the whole thing in what's called a transaction
 *          - with BEGIN TRANSACTION keyword at the start and COMMIT keyword at the end
 *      - Again we can copy and paste the output from dump into our code
 *
 * .quit
 *      - exits the SQLite shell and take you back to your command prompt or terminal session
 *
 * //////////
 *
 * The SQLite shell is useful when you need to design your database and it's generally easier to use some sort of frontend to the db
 *  when setting things up
 * Just to make sure you've got all the tables created correctly with the right columns and so on
 *
 * You can also test the queries that you'll be using in your code before ypu get around to writing the code, so you know the SQL side
 *  of things has been setup correctly and is working
 *
 * We're going to work with a database that already has some data in it , so we can practice querying data a bit more and also look at how
 *  to join related tables together
 *
 *
 */
