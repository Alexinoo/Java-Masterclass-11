package databases._00_9_wildcards_in_queries;

/*
 * Wildcards in Queries and Views
 * ..............................
 *
 * Suppose we got a particular song that we'd like to hear in the collection, but we can't actually remember exactly what it was called
 *  nor who it's by
 *
 * The only thing we know is that it's got the word doctor in the title though
 *
 * So, how do we actually go about retrieving that information using SQL code ?
 *
 *       sqlite> SELECT artists.name ,albums.name,songs.track , songs.title FROM songs
 *             ...>  INNER JOIN albums ON songs.album = albums._id
 *             ...>  INNER JOIN artists ON albums.artist = artists._id
 *             ...>  WHERE albums.name = "Doolittle"
 *             ...>  ORDER BY albums.name, songs.track;
 *
 * We can edit the WHERE clause and use "%doctor%" with the LIKE keyword
 *
 *       sqlite> SELECT artists.name ,albums.name,songs.track , songs.title FROM songs
 *             ...>  INNER JOIN albums ON songs.album = albums._id
 *             ...>  INNER JOIN artists ON albums.artist = artists._id
 *             ...>  WHERE songs.title LIKE "%doctor%"
 *             ...>  ORDER BY albums.name, songs.track;
 *
 * - The above lists all the songs containing the word doctor in them
 *
 * - 3 things to note about the command
 *      1. We use the keyword "LIKE" instead of "=" symbol
 *          - We want to match names that are like the text that we typed
 *          - If we had used equals (=) instead of LIKE, we'd only have got:
 *
 *              Wishbone Ash | Wishbone Four | 5 |Doctor
 *
 *      2. The wild card character in SQL is the % percent character
 *          - You may be used to using a question mark to match single characters or an asterisk to match any sequence, but in SQL
 *             you use the question mark instead of an asterisk to match a sequence of 0 or more characters
 *
 *      3. Unlike equals which performs a case sensitive search, LIKE is not case sensitive
 *          - You can use LIKE without a wildcard if you want to perform a search without worrying about the case
 *          - SO that WHERE clause matches any rows that have the word "doctor" in the song's title column
 *
 *
 * ////////////////////////
 *
 * Bands sometimes change their names "Thinks of Prince" or the "Sensational Alex Harvey" band, and this collection contains at least 1
 *  album by just a "Jefferson Airplane", which later became "Jefferson Starship" which looks like another good use for a wildcard search
 *
 * We need to change the songs.title to artists.name , but this time leave the % from the start
 *
 *      sqlite> SELECT artists.name ,albums.name,songs.track , songs.title FROM songs
 *             ...>  INNER JOIN albums ON songs.album = albums._id
 *             ...>  INNER JOIN artists ON albums.artist = artists._id
 *             ...>  WHERE artists.name LIKE "jefferson%"
 *             ...>  ORDER BY albums.name, songs.track;
 *
 *
 *      - returns results for names starting with the word "Jefferson"
 *
 * SQL also allows an underscore to match a single character if you need to do that
 *
 * ////////
 *
 * The major client server databases have stored procedures, which are a way to store SQL queries amongst other things and execute
 *   them when you want often with parameters for things like the text to search for
 * They operate like functions or methods that are stored in the database and can be reused when you want
 *
 * Unfortunately, though SQLite doesn't have stored procedures
 * And there is actually a very good reason for this, and it's as a result of the fact that SQL is intended to be embedded in programs
 * So normal client service SQL databases have the database server running on a remote machine that you connect to in order to access the
 *  data
 *
 * A stored procedure runs on the server and so it's far more efficient than trying to work with a large data set on a remote machine
 * But SQLite is not client server and everything is running on the same machine anyway, the advantages of using stored procedures don't
 *  apply
 * In addition, we don't generally use SQLite interactively like we're doing here - we're doing this because we need develop applications
 *  and need to try things out and get queries working
 * But ordinary users wouldn't normally interact with a SQL database in this way and it would all be done by the application itself
 * The point here is the absence of stored procedures isn't really a drawback considering the way SQLite is intended to be used
 *
 * One thing it does have in common with the client server database systems is views
 *
 * /////////
 * Views
 * ////////
 *
 * You can think of a view as a virtual table
 * You can't modify data using a view at least not in SQLite
 * We can't update, delete or insert , but we can query them just as if they were a table - make sense if we see one in action
 *
 *
 * /////////////////
 * Creating a View
 * ////////////////
 *
 * Create one based on the query we've been using:
 *
 * We create a view using CREATE VIEW command
 *
 *      sqlite> CREATE VIEW IF NOT EXISTS artist_list AS
 *          ...>  SELECT artists.name ,albums.name,songs.track,songs.title FROM songs
 *          ...>  INNER JOIN albums ON songs.album = albums._id
 *          ...>  INNER JOIN artists ON albums.artist = artists._id
 *          ...>  ORDER BY artists.name ,albums.name, songs.track;
 *
 *      - This creates a VIEW, and we can use .schema command to confirm that which show artist_list and the commands to actually
 *          reproduce that
 *
 * We work with a VIEW same way like we would work with a table
 *
 *      sqlite> SELECT * FROM artists_list;
 *
 *      - And we can actually filter it the same way we'd do with a table
 *
 *      sqlite> SELECT * FROM artists_list
 *            ...> WHERE name LIKE 'jefferson%';
 *
 * Basically we now have another table called artist_list that contains the data from 3 related tables
 * This is incredibly cool and views are very very useful things to have
 *
 * ////////////////////////////
 *
 * Why Views Might Be Useful
 * ////////////////////////////
 *
 * You can also create views on a single table , perhaps to restrict the columns that are returned or to show the records in a specified order
 *  without having to use the ORDER BY clause every time
 *
 * This can be a good way to include security in your application
 *
 * The Marketing Department of a bank for example may need to know the contact details of customers so they can send out emails but they
 *  shouldn't have access to customer security questions or account details
 *
 * A View could be used to provide them with the details they need while hiding the details that shouldn't be made commonly available
 * You also probably wouldn't want ordinary users seeing the link columns in our tables , they're interesting to us as developers but the
 *  numbers at the end might be confusing to other people
 *
 * And also the Primary Key field is also confusing and so we can create a view that just returns the album names
 *
 *       sqlite> CREATE VIEW album_list AS
 *            ...> SELECT name FROM albums
 *            ...> ORDER BY name;
 *
 *      sqlite> SELECT * FROM album_list;
 *
 *      - and at this point it's only going to return the names
 *
 *
 * //////////
 *  Drop View
 * //////////
 *
 * We would have done a case-insensitive ordering there because we once again got a "whip Jamboree" and "heavens to betsy" out of order
 *  as far as most humans would be concerned
 * Because a VIEW doesn't actually exist in a way a table does, we can actually DELETE the view and RECREATE it with the ORDER clause
 *  corrected
 * The command to drop the view would be
 *
 *      sqlite> DROP VIEW album_list;
 *
 *      - The view is deleted and deleting a view doesn't affect the data in the database but of course deleting a table obviously will
 *
 * Let's now proceed to recreate it
 *
 *      sqlite> CREATE VIEW album_list AS
 *            ...> SELECT name FROM albums
 *            ...> ORDER BY name COLLATE NOCASE;
 *
 * And now if we query the data
 *
 *      sqlite> SELECT * FROM album_list;
 *
 *      - this time we get things sorted in the right order
 *
 * ////////////
 * NOTE - Name crash
 * ////////////
 *
 * Note that when creating a view we should use column names that are unique
 * If we look at the script that we used to create our VIEW with
 *
 *      sqlite> CREATE VIEW IF NOT EXISTS artist_list AS
 *          ...>  SELECT artists.name ,albums.name,songs.track,songs.title FROM songs
 *          ...>  INNER JOIN albums ON songs.album = albums._id
 *          ...>  INNER JOIN artists ON albums.artist = artists._id
 *          ...>  ORDER BY artists.name ,albums.name, songs.track;
 *
 * Because there were 2 named fields in the SELECT statement, SQLite has renamed one of them so that the column names are unique
 * And .schema will remind us of the command we used to create the view
 * So because of the crash, SQLite automatically renamed the name columns from the albums table to name:1
 *
 * Not all database systems do this and it's a good idea to explicitly name the columns when you create the view if there's going to be
 *  a name clash or potential name clash
 *
 * So we need to drop the album_list view and recreate it and this time giving the 2 name columns a unique name
 *
 *       sqlite> DROP VIEW album_list;
 *
 *       sqlite> CREATE VIEW IF NOT EXISTS artist_list AS
 *          ...>  SELECT artists.name AS artist ,albums.name AS album,songs.track,songs.title FROM songs
 *          ...>  INNER JOIN albums ON songs.album = albums._id
 *          ...>  INNER JOIN artists ON albums.artist = artists._id
 *          ...>  ORDER BY artists.name ,albums.name, songs.track;
 *
 * And incidentally , we've used AS after the artists and album column names to provide a new name that the columns will be known as in
 *  the view
 *
 */
