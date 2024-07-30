package naming_conventions_packages.part1_naming_conventions;
/*
 * Java naming Convention
 *
 * - Adopting the accepted conventions for naming variables , methods and object(s) in Java makes your code easier for others to read
 * - "Others" here also includes yourself when you come to look at your code weeks of months after you wrote it
 *
 * - The things you will name in Java are:-
 *      - Packages
 *      - Classes
 *      - Interfaces
 *      - Methods
 *      - Constants
 *      - Variables
 *      - Type Parameters
 *
 * Packages
 *  - Always lower case
 *  - package names should be unique
 *  - Use your internet domain name, reversed, as a prefix for the package name
 *
 *  - Invalid domain name components
 *      - Replace invalid characters (-) i.e. - in domain name with an underscore
 *      - Domain name components starting with a number should instead start with an underscore_
 *      - Domain name components that are Java keywords should have that component start with an underscore_
 *
 *  - Invalid domain name components
 *      - Examples with replacements
 *
 *          Switch.supplier.com     -  com.supplier._switch   (switch is a java keyword)
 *          1world.com     -  com._1world                      ( 1 is a number)
 *          Experts-exchange.com     -  com.experts_exchange    (replace hyphen with an underscore)
 *  - Example package names
 *          java.lang
 *          java.io
 *          org.xml.sax.helpers
 *          com.timbuchalka.autoboxing
 *
 *
 * Class Names
 *  - CamelCase -  capitalize first letter of each word
 *  - Class names should be nouns - (they should rep things)
 *  - Should start with a capital letter
 *  - Each word in the name should also start with a capital (e.g. LinkedList)
 *  - Examples:
 *          ArrayList
 *          LinkedList
 *          String
 *          TopSong
 *          GearBox
 *          Main
 *
 * Interface Names
 *  - Capitalized like class names (CamelCase)
 *  - Consider what objects implementing the interface will become of what they will be able to do
 *  - Examples
 *          List
 *          Comparable
 *          Serializable
 *
 * Method Names
 *  - should be mixedCase
 *  - Often verbs - reflect the function performed or the result returned
 *  - Example
 *          size()
 *          getName()
 *          addPlayer()
 *
 * Constants
 *  - All UPPERCASE
 *  - Separate words with underscore _
 *  - Should be declared using the final keyword (tells Java that the value will not be going to change)
 *  - Examples
 *          static final int MAX_INT
 *          static final short SEVERITY_ERROR
 *          static final double PI = 3.141592653
 *
 *
 * Variable Names
 *  - mixedCase
 *  - names should be meaningful and indicative
 *  - start with lowercase letter
 *  - don't use underscores for variable names
 *  - Examples
 *          i
 *          league
 *          sydneySwans
 *          boxLength
 *
 * Type Parameters
 *  - Single Character, capital letters
 *  - Examples
 *          E - Element (Used extensively by the Java Collections Framework)
 *          K - Key
 *          V - Value
 *          T - Type
 *          S, U , V - 2nd , 3rd , 4th - types
 */
public class Main {
}
