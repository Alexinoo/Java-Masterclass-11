package databases._00_3install_sqlite_windows;

/*
 * Install and Setup SQLite for Windows
 * ....................................
 *
 * On windows , the SQLite command-line shell is an executable file called SQLite3.exe
 *
 * Let's see how we'll download that executable and add it to the System's Path so that we can use it
 *
 *
 * /////////////////////////////////////////
 * ////// Download SQLite /////////////////
 * ///////////////////////////////////////
 *
 * Visit SQLite website : https://sqlite.org/
 *
 *  - Click on Download tab
 *
 *  - There isn't an installer for SQLite - It's packaged into a number of zip files
 *
 *  - Navigate to: Precompiled Binaries for Windows
 *
 *  - Download sqlite-tools-win-x64-3460100.zip (4.80 MiB)
 *
 *  - The file we want is this one below:
 *
 *      - A bundle of command-line tools for managing SQLite database files, including the command-line shell program,
 *        the sqldiff.exe program, and the sqlite3_analyzer.exe program. 64-bit.
 *
 *  - The description for this file includes links to the 3 executables
 *      - command-line shell
 *      - sqldiff.exe
 *      - sqlite3_analyzer.exe
 *
 *  - The tool we need at the moment is the command-line-shell and therefore we need to extract the contents of the zip file to
 *     a suitable directory and then add the PATH to that directory to the System Path
 *
 *  - We can put this anywhere, in the Program Files , Directory or somewhere else , it really doesn't matter
 *
 *  - In my case: added it to below path:
 *
 *      - C:\Users\alex.wakanyi.STL-HORIZON\sqlite
 *
 *
 * /////////////////////////////////////////////////////////////
 * //// Copy Path and Add it To Environmental Variables ///////
 * ///////////////////////////////////////////////////////////
 *
 *  - Copy the Path above and add it to computer's path
 *
 *      - Control Panel > System > Advanced System Settings
 *
 *
 *
 * /////////////////////////////////////////////////////////
 * //// Launch SQLite From Command Prompt /////////////////
 * ///////////////////////////////////////////////////////
 *
 *  - Open cmd
 *      - cd C:\Users\alex.wakanyi.STL-HORIZON\sqlite
 *
 *  - Type sqlite3 and press enter
 *
 *      - C:\Users\alex.wakanyi.STL-HORIZON\sqlite\sqlite3
 *
 *  - Type .quit to get out of sqlite3
 *
 *  - Type exit to quit from the cmd
 *
 *
 */













