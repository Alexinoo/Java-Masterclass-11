package naming_conventions_packages.part2_packages;
/*
 * Packages
 *
 * - Over 9 million Java developers worldwide
 * - Class or Interface name conflicts are inevitable
 * - A mechanism was needed to fully specify class
 *      - Allow use of classes with the same name in the same project (or,even, the same class)
 *
 * Example with the code below
 *     import javafx.scene.Node;
 *
 *     Node node = null;
 *  - Pulls a list of 9 options to import Node object - there's actually a ton of different options , that Java is recommending with the word Node
 *    in it
 *  - think of a package as a way of grouping related classes and interfaces together
 *  - the actual package mechanism provides a way to manage the namespace of object types and it also extends access protection beyond the traditional
 *    access modifiers such as public, private and protected
 *  - the option selected is added as an import above the class file
 *
 *  - We can also specify the package that specifies the Node class
 *
 *      javafx.scene.Node node = null;
 *
 *      - in this case the package is not imported , and therefore every time you want to reference the Node class, you'll actually have to type
 *         the full package
 *      - can really get tiresome very quickly
 *  - Also we can't import 2 packages referencing the same node such as :
 *      import javafx.scene.Node;
 *      import org.w3c.dom.Node;
 *
 *  - And then create some nodes from the main
 *
 *      javafx.scene.Node node = null;
 *      org.w3c.dom.Node anotherNode = null;
 *
 *  - This will actually throw an error
 *
 *  - IntelliJ is unsure of what imports you're trying to use
 *
 *
 * Reasons to use Packages:
 *
 *  - Programmers can easily determine that the classes are related
 *
 *  - Easy to know where to find the classes and interfaces that can provide functions provided by the package
 *
 *  - Because the package creates a new namespace, class and interface name conflicts are avoided as a result of that - because they're in separate
 *    area which effectively what namespace is all about as it's a different area that all these classes & interfaces are sort of put together
 *
 *  - Classes within the package can have unrestricted access to one another while still restricting access for classes outside the package
 *
 *  We've been using packages throughout this course to date
 *   - For example :
 *      java.util.Scanner - provides the ability to accept user input
 *      java.util.ArrayList - use array lists in our programs
 *
 *  - Basically, it's impossible to create a java program without using at least 1 package
 *
 *  - And java.lang for example, contains the class Object that is used to support our classes as well as Integer, Double etc and all the fundamental
 *    building blocks of a Java program
 *      - Happens automatically when you create even the most basic Java programs
 *  - You'll actually not see an import for java.lang because it's automatically imported for us
 *      - might be removed automatically because of optimization settings from intelliJ
 *  - You could do that for this package, java.lang package they're automatically imported anyway
 *
 *  - Check the ones that are included when you install JDK
 *
 *      e.g. External Libraries > java.base > java
 *
 *  Creating our Java package
 *
 *
 *
 *
 *
 *  -
 */

public class Main {
    public static void main(String[] args) {

        //Node node = null;
        Integer myInt = 10;
        System.out.println(myInt.intValue());
    }
}
