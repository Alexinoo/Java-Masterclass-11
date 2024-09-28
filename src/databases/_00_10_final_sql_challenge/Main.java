package databases._00_10_final_sql_challenge;

/*
 * Functions and Final SQL Challenge
 * ....................................
 *
 * We can also include functions in the SELECT statement and one of the useful ones is count
 *
 *      sqlite> SELECT COUNT(*) FROM songs;
 *
 *      - returns the number of songs in the songs table
 *
 *      sqlite> SELECT COUNT(*) FROM albums;
 *
 *      - returns the number of records in the albums table
 *
 *      sqlite> SELECT COUNT(*) FROM artists;
 *
 *      - returns the number of records in the artists table
 *
 * ///////////
 * Challenge
 * //////////
 *
 * 1. Select the titles of all the songs on the album "Forbidden"
 *
 *      sqlite> SELECT songs.title FROM songs
 *            ...> INNER JOIN albums ON songs.album = albums._id
 *            ...> WHERE albums.name = 'Forbidden';
 *
 * 2. Repeat the same query, but this time display the songs in track order. You may want to include the track number in the
 *    output to verify that it worked OK
 *
 *       sqlite> SELECT songs.track,songs.title FROM songs
 *            ...> INNER JOIN albums ON songs.album = albums._id
 *            ...> WHERE albums.name = 'Forbidden'
 *            ...> ORDER BY songs.track;
 *
 * 3. Display all songs for the band "Deep Purple"
 *
 *      sqlite> SELECT artists.name,albums.name,songs.track,songs.title FROM songs
 *           ...> INNER JOIN albums ON songs.album = albums._id
 *           ...> INNER JOIN artists ON albums.artist = artists._id
 *           ...> WHERE artists.name = 'Deep Purple';
 *
 * 4. Rename the band "Mehitabel" to "One Kitten". Note that this is an exception to the advice to always fully qualify your
 *    column names. SET artists.name won't work, you just need to specify name
 *
 *      sqlite> UPDATE artists
 *          ...> SET name = 'One Kitten'
 *          ...> WHERE name = 'Mehitabel';
 *
 * 5. Check that the record was correctly renamed
 *
 *      sqlite> SELECT name FROM artists
 *          ...> WHERE name = 'One Kitten';
 *
 * 6. Select the titles of all the songs by Aerosmith in alphabetical order. Include only the title in the output
 *
 *      sqlite> SELECT songs.title FROM songs
 *          ...> INNER JOIN albums ON songs.album = albums._id
 *          ...> INNER JOIN artists ON albums.artist = artists._id
 *          ...> WHERE artists.name = 'Aerosmith'
 *          ...> ORDER BY songs.title COLLATE NOCASE ASC;
 *
 * 7. Replace the column that you used in the previous answer with count(title) to get just a count of the number of songs
 *
 *
 *      sqlite> SELECT count(songs.title) FROM songs
 *          ...> INNER JOIN albums ON songs.album = albums._id
 *          ...> INNER JOIN artists ON albums.artist = artists._id
 *          ...> WHERE artists.name = 'Aerosmith';
 *
 *      - Returns 151
 *
 * 8. Search the internet to find out how to get a list of the songs from step 6 without any duplicates
 *
 *      sqlite> SELECT DISTINCT songs.title FROM songs
 *          ...> INNER JOIN albums ON songs.album = albums._id
 *          ...> INNER JOIN artists ON albums.artist = artists._id
 *          ...> WHERE artists.name = 'Aerosmith'
 *          ...> ORDER BY songs.title COLLATE NOCASE ASC;
 *
 * 9. Search the internet again to find out how to get a count of the songs without duplicates.
 *    Hint: It uses the same keyword as step 8 but the syntax may not be obvious
 *
 *
 *      sqlite> SELECT COUNT(DISTINCT songs.title) FROM songs
 *          ...> INNER JOIN albums ON songs.album = albums._id
 *          ...> INNER JOIN artists ON albums.artist = artists._id
 *          ...> WHERE artists.name = 'Aerosmith';
 *
 *      - Returns 128
 *
 * 10. Repeat the previous query to find the number of artists (which obviously should be 1) and the number of albums
 *
 *      sqlite> SELECT COUNT(DISTINCT artists.name),COUNT(DISTINCT albums.name)  FROM songs
 *          ...> INNER JOIN albums ON songs.album = albums._id
 *          ...> INNER JOIN artists ON albums.artist = artists._id
 *          ...> WHERE artists.name = 'Aerosmith';
 *
 *      - returns: 1 | 13
 *
 *
 */