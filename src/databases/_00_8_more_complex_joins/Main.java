package databases._00_8_more_complex_joins;

/*
 * More Complex Joins
 * ..................
 *
 * So we can change our query a little bit
 *
 *      sqlite> SELECT track, title, name FROM songs
 *             ...>  INNER JOIN albums ON songs.album = albums._id
 *             ...>  ORDER BY albums.name, songs.track;
 *
 * It might actually look neater the other way around, if we list the album name before the song title
 *
 *      sqlite> SELECT albums.name songs.track, songs.title FROM songs
 *         ...>  INNER JOIN albums ON songs.album = albums._id
 *         ...>  ORDER BY albums.name, songs.track;
 *
 *      - And we can see this one looks a little bit neater
 * We can return any columns that we want in any order and we don't have to keep them in the same order that they appear in the table nor
 *  in the order the actual tables are joined either
 *
 * /////////////////////
 * //  Mini Challenge //
 * ////////////////////
 *
 * Produce a list of all artists, with their albums , in alphabetical order of artist name
 *
 *       sqlite> SELECT artists.name, albums.name FROM albums
 *             ...>  INNER JOIN artists ON albums.artist = artists._id
 *             ...>  ORDER BY artists.name;
 *
 * But what if we wanted to find out which artist produced a song though
 * The songs table doesn't have any direct links to artists
 * If we take a look at the relationships between the tables again, it will help us see how we can get the artist for a particular song
 * Although we can't go directly to an artist from a song record, we can find out which album contains the song and from there , it should
 *  be easy to find out who the artist is
 * And we do that by joining songs to albums and then albums to artists
 *
 *       sqlite> SELECT artists.name ,albums.name,songs.track , songs.title FROM songs
 *             ...>  INNER JOIN albums ON songs.album = albums._id
 *             ...>  INNER JOIN artists ON album.artist = artists._id
 *             ...>  ORDER BY albums.name, songs.track;
 *
 * So we have chained INNER JOINS together so that we have songs INNER JOIN albums INNER JOIN artists
 * And of course we have to specify which columns to JOIN ON
 * And now we get the results that we're looking for , the actual artist that produced the song
 *
 * The SELECT statement is pretty flexible and we can include as many columns as we need , joining tables as we need them and then sort as
 *  many columns as we need to produce decent output
 *
 * And you can also nest SELECT inside another SELECT statement but this is a more advanced SQL , which is beyond this intro
 *
 * Just keep in mind that SQL language is very powerful and really quite simple considering what you can do with it
 *
 *
 *
 * /////////////////
 * ORDERING CLAUSE
 * /////////////////
 *
 * One thing tha we need to keep in mind is that Ordering of the clause is very important
 * We can go putting the ORDER BY clause before the JOINS
 * The ORDER is strict in that regard and the way we've been doing it is actually the correct way to do it
 * If we want to include a WHERE clause, it has to go BEFORE the ORDER BY clause
 * So let's restrict that previous query to just the album "Doolittle" which has the id: 19
 *
 *       sqlite> SELECT artists.name ,albums.name,songs.track , songs.title FROM songs
 *             ...>  INNER JOIN albums ON songs.album = albums._id
 *             ...>  INNER JOIN artists ON album.artist = artists._id
 *             ...>  WHERE albums.id = 19
 *             ...>  ORDER BY albums.name, songs.track;
 *
 *      - And now we're getting the rows back for the album "Doolittle" which again had the id of 19
 *
 * Splitting the command over several lines like this does make it easier to understand but it does make calling the command back a little
 *  tricky
 * We have to do it line by line
 *
 * So the structure of the SELECT statement is quite straightforward,
 *      - You specify the columns that you're in
 *      - You JOIN any other tables that are needed
 *      - Filter the Selection using a WHERE clause
 *      - And finally, ORDER the Results
 *
 *
 *
 *
 * /////////////
 * Sometimes, you have a rough idea of what you want to find but you don't know it exactly
 * Or perhaps you're interested in several rows that have similar but not identical names
 * The SQL WHERE clause can use wild cards to match on partial Strings to cope with these situations
 *
 *       sqlite> SELECT artists.name ,albums.name,songs.track , songs.title FROM songs
 *             ...>  INNER JOIN albums ON songs.album = albums._id
 *             ...>  INNER JOIN artists ON album.artist = artists._id
 *             ...>  WHERE albums.name = "Doolittle"
 *             ...>  ORDER BY albums.name, songs.track;
 *
 * We'll take a look at the wild card character next and how it can be useful in matching partial strings
 */