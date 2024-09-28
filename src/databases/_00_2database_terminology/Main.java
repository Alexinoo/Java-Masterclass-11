package databases._00_2database_terminology;

/*
 * Database Terminology
 * ....................
 *
 * Database
 *  - is the container for all the data that you store
 *  - the term database refers to the entire data as well as the structure , it's actually stored in, and in addition , any
 *    queries and views on that data
 *
 * SQLite
 *  - the entire database is stored in a single file
 *  - though not always true for most large database systems
 *
 * Database Dictionary
 *  - provides a comprehensive list of the structures and types of data that are used in recording the data
 *  - it describes all the tables and fields withing the database
 *
 *  - In SQLite, there is a table in each database called sql_lite_master that has this information in it
 *  - You can query this table, but there are commands that do it for you, so you don't have to understand the structure
 *     of the master table
 *
 * Table
 *  - A collection of related data held in the database
 *  - Think of a Contacts table that stores the name , address and perhaps the phone numbers of your customers
 *  - Or even an Invoice table, that records , invoiceNo, and other details of the invoice
 *
 *  - So, here we have 2 tables: contacts and invoices that are used to store information about contacts and invoice details
 *
 * /////
 * With SQLite databases such as SQLite , or Microsoft SQL server, they're ideal for storing structured data that can be organized neatly
 *  into rows and columns
 * With all the interest in big data, there are now databases such as NoSQL or Hadoop, that can cope with data that doesn't have
 *  such an obvious structure , but we'll be restricting our use of database to structured data
 *
 * Field
 *  - the basic unit of data in a table
 *  - a field can be thought of probably similar to what a variable is
 *  - Just like a variable, a database field has a name and a type
 *  - The type restricts what kind of data can be stored in a field
 *      - could be a String
 *      - could be a number
 *  - Many databases also allows date fields, large text fields , and also fields where you can store things like photographs or audio
 *      - these types are often referred to as BLOB which stands for Binary Large Object
 *
 * Column
 *  - is another name for the field
 *  - fields are often referred to as columns in databases
 *
 * Row
 *  - A row or record is a single set of data containing all the columns in the table
 *  - The invoice table has 4 rows - rows are also called records
 *  - You can use either of the terms row or record to identify it, but the correct relational database terminology is actually row
 *
 * Flat File Database
 *  - stores all data in a single table
 *  - This results in a lot of duplication
 *  - As we can see from this example table, trying to store all the data in a single table results in duplicate data
 *
 * Relational Database
 *
 *  - Using a Relational database, data can be related to other tables which is very useful
 *  - Continuing on with the invoice example, we can split the data out into a customer table which contains standard customer data
 *    such as :-
 *      - customerName
 *      - address
 *      - credit_limit
 *      - balance
 *
 *  - Then we can have another table called invoices containing all the customer's purchase
 *      - invoiceNo
 *      - date
 *      - description
 *      - amount
 *      - customerName
 *
 *  - The customerName column in the customer's table is related to the customerName column in the invoices table
 *  - In relational database terms, this is called a Join
 *
 *  - In this example, we have 1 to many Join because there can and there probably will be many invoice rows for each customer
 *
 *  - Using a relational model, updating a customer's credit limit involves changing the data in just a single row
 *
 *  - There is a mechanism to join these 2 tables to link the individual records in each table to each other
 *
 *  - You'll often see designs where a 3rd table is used to provide the link
 *
 *  - It is also very common to use a linking table to relate the data to 2 other tables
 *
 *  - Such as Customer_invoices table below with the following columns :-
 *      - CustomerName
 *      - InvoiceNo
 *
 *  - Here, when an invoice is stored, a new record is created in the Customer_invoices table to link for example InvoiceNo:004 to
 *    Customer:ISP
 *
 *  - One advantage of this is that the invoice table only contains data relating to invoices
 *
 *  - The rows are not cluttered up with customer information of any kind , not even the customerName
 *
 *
 * /////////////////
 *
 * Normalization
 *
 *  - Splitting the data like this is known as normalization
 *
 *  - Database normalization is basically the process of removing redundant , duplicated, and irrelevant data from the tables and the more
 *     that this is done, the higher the level of normalization
 *
 *  - If you look into normalization, you'll find that you can go up to level 6 , 6th Normal Form , but in most practical applications , it's
 *     rare to go beyond the 3rd level
 *
 *  - Our example isn't quite as normal as it should be, because we've used the customerName as the link between the customer and invoices
 *     tables
 *
 *  - If one of our customers changed its name which is quite a common thing to happen, then we'll have to update each of the relevant entries
 *     in the Customer_invoices table as well
 *
 *  - The usual way to deal with this is to use a unique ID field, that stays the same for each customer once it's allocated
 *  - We shouldn't store the customer balances in the table as that's best calculated when needed
 *
 *
 * /////////
 *  View
 * ////////
 *
 *  - A View is a selection of rows and columns , possible from more than 1 table
 *  - A View is a way of looking at the data , in a format similar to a table , but bringing data together from more than 1 joined table
 *  - In this example, the view contains columns from the customer table and the invoices table
 *  - In SQLite, the data in a view can't be updated , which means you can't add a new row to a view and have it place the new data into
 *     the relevant tables
 *  - Some databases such as Microsoft SQLServer do allow this, but in that case, the views do have restrictions on the columns that can
 *     and must contain if they're used in this way
 *
 *
 * ////////////
 * SQLite is designed to be embedded in applications and is actually a library that is called from our application code
 * It does ship with a shell program , that you can use to create databases and interrogate them and we'll start using that to explore the
 *  commands available in SQLite
 * But before we can use that shell program, we need to make sure that it's available on our Computer System's path
 *
 *
 */
