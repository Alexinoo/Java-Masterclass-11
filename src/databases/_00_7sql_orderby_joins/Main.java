package databases._00_7sql_orderby_joins;

/*
 * SQL Order By and Joins
 * ......................
 *
 * Let's look at how we can query the data and make sure that we get it back in a sensible order if ordering of rows is undefined in a
 *  relational database
 *
 * When we display all the artists records, they actually come out in the same order each time and that's because we have a Primary Key
 * The records will automatically be selected based on the ordering of the primary key
 * Note that the actual order of the records in the database is undefined and if we didn't have a Primary Key, they'd be coming out in
 *  an undefined order
 * We can actually specify a different order in our SELECT statement by using an ORDER BY clause
 *
 *      sqlite> SELECT * FROM artists ORDER BY name;
 *
 *      - And now this sorts the records in Alphabetical order
 *
 * And we can do exactly the same thing for the albums
 *
 *      sqlite> SELECT * FROM albums ORDER BY name;
 *
 *      - Note, that the 2 Black Beads Tea Party albums, "heavens to betsy" and "whip Jamboree" are out of order
 *      - And that's because they start with lower case letters
 *      - We can actually ignore case by using the "COLLATE NOCASE" clause
 *
 *      sqlite> SELECT * FROM albums ORDER BY name COLLATE NOCASE;
 *
 *      - and now the case is ignored when it's returning results - and now
 *
 * It is also possible to specify ascending or descending order using the keywords ASC or DESC which stands for ascending and descending
 *  order
 *
 *      sqlite> SELECT * FROM albums ORDER BY name COLLATE NOCASE DESC;
 *
 * And now the albums are sorted in descending
 * What if we want to group albums together so that all the albums by each artist appear together?
 *
 * The ORDER BY clause can actually contain more than 1 column
 *
 *      sqlite> SELECT * FROM albums ORDER BY artist ,name COLLATE NOCASE DESC;
 *
 *      - This sorts first by artist id and then by album name
 *
 *
 * /////////////////////
 * /// Mini Challenge //
 * /////////////////////
 *
 * List all the songs so that the songs from the same album appear together in track order
 *
 *      sqlite> SELECT * FROM songs ORDER BY album ,track;
 *
 *      - And so now the 11 songs from the Black Keys:  albums Attack and Release appear together and in track order within an album
 *
 *      sqlite> SELECT * FROM albums WHERE _id = 439;
 *
 *      - Prints : Attack and Release | 133
 *
 *      sqlite> SELECT * FROM artists WHERE _id = 133;
 *
 *      - prints: 133 | Black Keys
 *
 * Having to run separate queries like this is a bit grubby though
 * Let's see how to relate tables together so that we can get a list of songs that include the album they appear on as well as the artists
 *  that produced them
 *
 *
 * /////////////////////
 * /// Mini Challenge /
 * ////////////////////
 *
 * To achieve this we need to use the SQL JOIN clause that is used to join tables together
 *
 * Keeping data normalised so that tables can only contain information that relates to a single thing (song , album or artist) is a fundamental
 *  part of a relational databases and by doing that and then joining the tables back together you get a great deal of flexibility in how you
 *  can query and manipulate the data
 *
 * Remember that the songs table contains a column holding the albumId , and the albums table has an artist id field
 * These are used to provide a link between the tables
 *
 * ////////////////////
 * Example in The Slide - JOIN
 * ////////////////////
 *
 * We can see from the slide how the album column in the songs table provides a link to the album table
 *  - The first 10 songs all belong to the album whose ID is 1 "Tales of the Crown"
 *  - The next set of songs all belong to "The Masquerade Ball"
 *
 * The artist column in albums links to the artist table, so those 2 albums are by "Axel Rudi Pell"
 * The album "Crimes of Passion" is by Pat Benatar and "Nightflight" is by a band called "Budgie"
 *
 * So let's now join the tables in SQL and see how this is going to look
 *
 * Let's use a SELECT statement and add a JOIN clause to link the songs and albums
 *
 *      sqlite> SELECT songs.track, songs.title, albums.name FROM songs
 *         ...> JOIN albums ON songs.album = albums._id;
 *
 * So the first thing to note is that we've specified which tables the columns are in when selecting them
 *  -e.g.
 *      songs.track , songs.title , albums.name
 *
 *      - track and title are in the songs table, and you can notice how we use songs.track and songs.title
 *      - name comes from the albums table , and so we specify albums.name
 *
 * There's no ambiguity, and we can actually leave off the table name
 * So we can also write this as
 *
 *       sqlite> SELECT track, title, name FROM songs
 *         ...> JOIN albums ON songs.album = albums._id;
 *
 *      - We could have done this if there's no ambiguity with the names, but it's a good habit to always specify the table name
 *         especially in code
 *      - Always prefix the fields with table name in your code
 *
 * Some albums have a sort of subtitle , and so if the table was modified to include a title column , then that query will no longer
 *  work because it wouldn't know which table the title column should come from
 * And note though that we can't leave the table name off when using the id column
 *
 *       sqlite> SELECT track, title, name FROM songs JOIN albums ON songs.album = _id;
 *
 *      - And if we fail to include the table name before the _id, we'll bet an error : ambiguous column name: _id
 *      - And that's because both tables have a column of that same name _id , and SQLite doesn't know which one we mean and we need
 *         to be more specific
 *
 * ////////////////
 * TYPES OF JOINS
 * ///////////////
 *
 * There are different types of Joins
 * The most common one being INNER JOIN and join as we've used is really a shorthand of INNER JOIN
 * So we can also write this as follows :
 *
 *       sqlite> SELECT track, title, name FROM songs
 *         ...>  INNER JOIN albums ON songs.album = albums._id;
 *
 * Keep in mind that not all database systems will allow you to leave off the word INNER, so it's worth always using it
 * So instead of selecting from songs, we are doing SELECT ... FROM SONGS INNER JOIN albums
 * We then have to tell SQLite, which columns are involved in the JOIN which is what the ON part does
 * And it says to relate the rows in songs to those in albums where the songs table album column equals the album tables id column
 *
 * And if we really wanted to, we can actually tack an ORDER BY clause on the end of that if we want to sort the data
 *
 *      sqlite> SELECT track, title, name FROM songs
 *         ...>  INNER JOIN albums ON songs.album = albums._id;
 *         ...>  ORDER BY albums.name, songs.track;
 *
 *
 *
 *
 *
 *
 */
