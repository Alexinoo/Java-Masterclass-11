package interfaces_inner_and_abstract_classes.part10_abstract_classes_vs_interfaces;

import java.util.ArrayList;
import java.util.List;

/*
 * Abstract class vs an Interface
 * ..............................
 *
 * - Let's discuss the main differences between an Abstract class and an interface and when to use either of them
 * - This should hopefully give you an overall understanding of both
 *
 * Abstract Class
 * - Abstract classes are similar to an interfaces
 * - You cannot instantiate them, and they may contain a mix of methods declared with or without an implementation
 * - You can declare fields that are not static and final, and define public, protected and private concrete method(s)
 * - An abstract class can only extend 1 parent class, but it can implement multiple interfaces
 * - When an abstract class is subclassed, the subclass provides implementations for all of the abstract methods(s) in
 *   it's parent class
 * - If, it doesn't, then the subclass must also be declared abstract
 *
 * Use an Abstract class when...
 *  - You want to share code among several closely related classes (Animal - with fields name, age...)
 *  - You expect classes that extend your abstract class to have many common methods or fields or required access
 *    modifiers other than public (protected , private)
 *  - You want to declare non-static or non-final fields (for example name, age), this enables you to define method(s)
 *    that can access and modify the state of an object (getName , setName)
 *  - You have a requirement for your base class to provide a default implementation of certain methods but other
 *    method(s) should be open to being overridden by child classes
 * Summary
 *  - The purpose of an abstract class is to provide a common definition of a base class that multiple derived classes
 *    can share
 *
 *
 * Interfaces
 *  - An interface is just the declaration of methods of a Class, with no implementations
 *  - An interface define what kind of operation an object can perform
 *      - The operations are defined by the classes that implements them
 *  - Interfaces form a contract between the class and the outside world, and this contract is enforced at build time by
 *    the Java compiler
 *  - You cannot instantiate them
 *  - Interfaces can contain a mix of method(s) declared with or without an implementation
 *      - All method(s) in interfaces are automatically public and abstract
 *  - An interface can extend another interface
 *  - Interface are more flexible and can deal with a lot more stress on the design of your program that the
 *    implementation
 *  - By introducing interfaces into your program, you're really introducing points of variation at which you plug in
 *    different implementations for that interface
 *      - An interface primary purpose is abstraction, decoupling the "what" from the "how"
 *
 * N/B
 *  - Since Java 8, interfaces can contain default method(s)
 *      - In other words, methods with implementation
 *      - Keyword default, is used (mostly for backwards compatibility), and static method(s) as well
 *      - Before Java 8, that was not possible
 *  - Since Java 9, an interface can also contain private method(s) ( commonly used when 2 default methods
 *    in an interface share common code)
 *
 * Use an interface when...
 *  - You expect that unrelated classes will implement your interface
 *  - For example:
 *      - the interfaces Comparable and Cloneable are implemented by many unrelated classes
 *  - You want to specify the behavior of a particular data type, but you are not concerned about who implements its
 *    behavior
 *  - You want to separate different behavior
 *
 * Examples:
 *  - The Collections API is an excellent example
 *      - We have the List Interface,
 *          - with ArrayList and LinkedList implementing the method(s) from List interface to name a few
 *
 *  - The JDBC API is another excellent example
 *      - It exists of almost only interfaces
 *      - The concrete implementations are provided as "JDBC drivers"
 *      - This enables you to write all the JDBC code independent of the database (DB) vendor
 */
public class Main {

    public static void main(String[] args) {
        List<String> myList = new ArrayList<>();
    }
}
