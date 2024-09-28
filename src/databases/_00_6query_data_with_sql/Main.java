package databases._00_6query_data_with_sql;

/*
 * Querying Data with SQL
 * ......................
 *
 * We've seen basic SQL query using the SELECT statement and it's time to look into that in more detail
 * Once we've used some more queries, we're going to look at how to store commonly used queries in what's called a View
 * THe idea of Views is common in most relational databases
 *
 * We'll also look at the SQL Join clause and see how it's used to link tables together which can result in quite complicated
 *  queries and so also we'll also use views as a way to store a query so that we can reuse it whenever we need to
 *
 *
 * music.zip file
 * ..............
 *
 * Tim has created a database containing details of a music collection that we need to download from the resources section
 * Extract the music.zip file and save it to a suitable location on your computer's hard drive
 *  - Preferably inside the sqlite folder
 *
 * Connect to it by typing
 *
 *      sqlite> sqlite3 music.db
 *
 * Incidentally, Tim has given this file a .db extension but SQLite doesn't actually care how you name the database file.
 * It's usual to use something like .db or .sqlite but it really doesn't matter
 * It is a good idea though to avoid using .sql as it's used to indicate that a file contains a sql script
 *
 * //////////
 *
 * Let's start by reviewing the structure of the database
 *
 * ///////////////////////////
 * ///// Mini Challenge //////
 * ///////////////////////////
 *
 *
 * Use the appropriate SQLite command to display the structure of the database
 *
 * The command to type is .schema
 *
 *  sqlite> .schema
 *  - More Explicit
 *  - Gives a list of all the tables in the music.db database and the source code that was used to create them
 *
 * We can also use .tables to see a list of tables in the music.db database
 *  sqlite> .tables
 *          albums
 *          artists
 *          songs
 *
 * ////////////
 *
 * If we're not too familiar with command lines, you can repeat previous commands by using the up and down arrow keys to recall them
 * though might work differently with different versions like Mac
 *  - Works on both Linux and Windows
 *
 * //////
 * So looking at the schema command, the music.db database contains 3 tables : songs, albums and artists
 * Each table contains an id column named as : _id
 * We don't have to call it that but some of the Java classes to handle databases actually require an id column called _id
 * It's probably a good habit to get into to actually do that
 *
 *  _id :
 *      - is just an integer field & we do have to update it manually, but we'll be changing that a little bit later
 *      - holds a number that uniquely identifies the rows in the table
 *
 *      - Query the artists table:
 *
 *          SELECT * FROM artists;
 *
 *      - We can see the number to the left of the Artist name is uniquely identifying each one
 *      - and the same is true for the albums table as well
 *
 *          SELECT * FROM albums;
 *
 *      - We've got a total of 439 records with also an id that is unique for each album
 *      - The 3rd column in the albums table is the id of the artist, and so the last album that was created , was created by an artist
 *          133 (Black Keys)
 *
 * And finally we have the songs table
 *
 *     SELECT * FROM songs;
 *
 *      - prints 5350 songs
 *      - once again each song has a unique id
 *      - the second number is the position of the song on the album : track no
 *      - the 3rd number is the id of the album
 *
 *      - So, Permanent Vacation is the 10th track in album 367
 *
 * //////////////////////
 * /// Mini challenge ///
 * //////////////////////
 *
 * - Find the title of album 367
 *
 *      SELECT name FROM albums WHERE _id = 367;
 *
 *      - prints "Permanent Vacation"
 *      - so the album is also called Permanent Vacation
 *      - we could have also used * as well, and we'd still get the same results
 *
 *      SELECT * FROM albums WHERE _id = 367;
 *
 *      - we get the same results but now we get the extra fields
 *
 * /////////
 *
 * So the id field can be used to relate the songs and albums tables so we can easily see which album the songs belong to
 * Having to perform 2 queries to do that is a bit tedious, we can take a look at the structure of the tables and do some more
 *  querying before we deep dive into how we join the tables together
 *
 *  - The _id column is set to be the Primary Key
 *      - A Key in a table is an index, which provides a way to speed up searches and joins on a column
 *      - When columns are indexed , they can be searched much faster than if they arent
 *      - Index columns are sorted so that they can be searched through much faster
 *
 *
 * /////////////////////
 * Relational Databases
 * ////////////////////
 *
 * Primary Key
 * ...........
 *
 * One thing about relational databases is that the ordering of the rows is undefined
 * In that respect, they're similar to Java Maps or Sets
 * In fact, relational database theory is heavily based on Set theory
 * So by defining a key, what we're saying is that the data should be ordered on that column or group of columns and searches work far more
 *  efficiently by doing so
 * There can be lots of keys on a table but there can only be 1 Primary Key and usually this is the id column
 * But if you don't have an id in your table, you can choose another column to be the Primary Key if you want
 * However, 1 important thing about the Primary Key, is that it MUST be UNIQUE
 *
 * /////
 * Do an insert with an id of 201, which we know already exist
 *
 *  sqlite> INSERT INTO artists VALUES(201,"Beyonce");
 *
 *  - We get Error: UNIQUE constraint failed: artist._id
 *  - We're not able to add this record, and it failed because there's already a record with the value 201 for it's Primary Key
 *  - We get an error if we try to use that id again
 *
 *
 * NOT NULL
 * .........
 *
 * - The name column of the artists and album tables is marked as NOT NULL , and the title column of songs is also NOT NULL
 * - This means that the columns MUST contain a value
 * - If you try to leave them blank when inserting a new record, you'll actually get an error
 *
 * - And if you think about it, it really doesn't make sense to store an artist without a name and the same for an album
 * - So creating those columns with NOT NULL property ensures that all albums and artists have got a name and the same goes for songs
 *   titles
 *
 *
 * NULL
 * ....
 *
 * - Sometimes, having a NULL column value make sense,
 * - A middle name column in a contact table might often be null in that situation
 *
 * But when designing your tables, think about the data and if it would make sense to have a NULL value, then use NOT NULL when creating
 *  the column
 * The Primary Key in our tables is automatically NOT NULL because integer primary key columns in SQLite are treated in a special way
 * And we saw that when we tried to insert an artist:201,"Beyonce"
 *
 * However, if we try to insert just the name
 *
 *  sqlite> INSERT INTO artists(name) VALUES("Beyonce");
 *
 *  - This works and this time we're not providing an id and as a result, we must explicitly specify the name column so that SQLite knows
 *     which column we want to have the value "Beyonce"
 *  - And now, if we query our data again, we'll find that the artist Beyonce has been added at the table right at the bottom with an id of 202
 *
 * An integer Primary Key column can't contain null values and SQLite automatically generates a UNIQUE number for the column if one aint provided
 * This is slightly different from the behavior of other SQL databases such as Microsoft SQL Server where you have to specify AUTO INCREMENT when
 *  creating the column, if you want the values to be automatically generated
 *
 *
 *
 *
 */