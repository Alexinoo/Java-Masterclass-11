package lambda_expressions.part2_lambda_intro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * Lambda Expressions
 * ..................
 *
 * - Lambda Expressions often called lambda are a new feature introduced in Java 8
 * - Hey provide us with an easier way to work with interfaces that have got only 1 method
 * - Typically used in places where we use anonymous classes
 *
 * - Let's start by reviewing how we use a runnable obj to create and run a thread and see how this relates to lambda
 *
 * - Create CodeToRun class that implements Runnable interface
 *      - Implement run()
 *      - Kick of the runnable from the main()
 *          new Thread(new CodeToRun()).start();
 * - This obviously works and that's 1 way of doing it
 *
 * - Next
 *      - We can also use an anonymous class
 *
 * - This works, but in both cases, we had to write several lines of code to accomplish what we wanted to do, when all
 *   we really cared about is System.out.println statement
 *      - The other code is there to instantiate an object and implement the single method in the Runnable interface
 *
 * - It would be nice, if we could pass the System.out.println statement to the thread constructor and not have to
 *   write the code that isn't really doing anything
 *
 * - Using a lambda expression, we can actually do that by passing it as a method to the new Thread constructor
 *
 * - Every lambda expression has got 3 parts:
 *      - Argument list
 *      - arrow token
 *      - body
 * - Let's talk about what we have written which is noted below
 *      ()-> System.out.println("Printing from lambda")
 *
 *      - The above expression has got an empty argument list  "()"
 *      - The arrow token is just the dash character followed by greater than character "->"
 *      - The body is the code that we want to run which is "System.out.println("Printing from lambda")"
 *
 * - when a compiler sees a lambda expression, how does it know what to do ?
 *      - It knows one of the thread classes constructors accepts a Runnable parameter
 *      - In addition, it also knows that the Runnable interface, ony has 1 method "run()" which doesn't take
 *        any parameters
 *      - It's able to match the lambda expressions argument list, with no parameters , with the run()
 *      - Because the compiler needs to match the lambda expression to a method, lambda expressions can only be used
 *        with interfaces that contain only 1 method that has to be implemented
 *
 *  - These interfaces are also referred to as Functional Interfaces
 *  - By using a lambda expression, instead of creating a class that implements Runnable, or using an anonymous class,
 *    we're actually able to reduce the lines of code we have to write, and focus more on what we need to run
 *
 * - Notice that intelliJ, recognizes lambda and marks them with a lambda symbol in the editor's gutter
 *
 * - And if we click it, it actually takes us to the method that intelliJ believed the lambda expression maps to
 *      - In this case, it opens Runnable.java and points to the run() which is what we're mapping to
 *      - A handy way to check that the expression matches the method that we would have to implement, if we weren't
 *        using a lambda
 *
 * - Suppose, we wanted our lambda to do more and not just print something to the console
 * - Or say we wanted to implement multiple lines of code
 *      - We can do that as well by surrounding the body with curly braces, just as we would do for any code block
 *
 * - So let's have the thread print several lines to the console
 *      - Notice we also had to terminate each statement with a semicolon, and we didn't have to do that with when
 *         the body only had 1 statement
 *      - And in fact, we'll get a compiler error when we try to add the semicolon with 1 statement
 *
 * - Lambda expression needs to be concise, and semicolon is not needed when we only have a single statement
 *
 * Next
 *  - Create an Employee class
 *  - Fields
 *       name : String
 *       age : int
 *  - Constructor
 *      Employee(String name , int age)
 *  - Getters
 *      getName : String
 *      getAge : int
 *  - Setters
 *      setName(String name) : void
 *      setAge(String age) : void
 *
 * Next
 *  - Create some employees : john , tim , jack and snow
 *  - Create an ArrayList and store them in a List<Employee>
 *      - Add the employees to the List<Employee>

 * Next,
 *  - Sort the employee in ascending order and print the results -
 *      - use Collections.sort(List<T> list ,Comparator<? super T> c )
 *          - Use a code that doesn't use a lambda expression
 *          - pass Comparator as an anonymous class
 *
 *  - Print using for loop
 *      - use a for each
 *
 * - We have sorted our employees list by passing it to Collections.sort(List<T> list ,Comparator<? super T> c)
 * - The sort() is a static method from the Collections class
 * - It takes 2 parameters
 *      - List<T>
        - Comparator<? super T>
 *
 * - But what is Comparator<? super T> ?
 *
 * Comparator<T>
 * - It is a Functional Interface and can therefore be used as the assignment target for a lambda expression or a
 *   method reference
 * - It contains 2 methods that don't have default implementations
 *      compare(T o1 , T o2) : int
 *      equals(Object obj) : boolean
 * - We said that we can only use lambdas for interfaces that require implementation of only 1 method
 *
 * - Back to our code
 *     - We implemented the compare(), which uses String.compare() to compare the employee names
 *     - The return value of compareTo() matches, also what the compare() also wants to return
 *          - negative, when the first value is less than the second value
 *          - positive, when the first value is greater than the second value
 *          - zero, when the 2 values are equal
 *
 * Next
 *  - Let's change the code to use a lambda expression
 *  - We can use a lambda, even though there are 2 method(s) we can implement because the equals method will always
 *    have a default implementation
 *  - Remember that all classes descend from Object and Object class contains an equals()
 *      - This means that every instance that implements, comparator, will already have an implementation of the
 *          equals()
 *  - Therefore, the comparator interface, really only has a single method that always has to be implemented by
 *    classes that implement it
 *  - And for that reason, it's a Functional Interface, and we can use a lambda instead of an anonymous class
 *
 * Notice
 *  - We can also see that the new Comparator part of the sort statement is grayed out in the editor, and if we
 *    hover over it, intelliJ says that we can actually replace the anonymous class with a lambda
 *
 *
 */
public class Main {
    public static void main(String[] args) {

        new Thread(new CodeToRun()).start(); // 1-way of executing the Runnable rom a thread

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Printing from anonymous runnable");
            }
        }).start(); // 2-nd way of using anonymous Runnable

        new Thread(()-> System.out.println("Printing from lambda")).start(); // 3rd-way by using a lambda

        // multiple lines
        new Thread(() -> {
            System.out.println("Printing from lambda - multiple lines");
            System.out.println("Line 2");
            System.out.printf("This is line %d\n",3);
        }).start();

        //Create some employees
        Employee john = new Employee("John Doe",30);
        Employee tim = new Employee("Tim Buchalka",21);
        Employee jack = new Employee("Jack Hill",40);
        Employee snow = new Employee("Snow White",22);

        // Add them to a List
        List<Employee> employees = new ArrayList<>();
        employees.add(john);
        employees.add(tim);
        employees.add(jack);
        employees.add(snow);

        //sort - use Collections.sort(List<T> list ,Comparator<? super T> c )
        Collections.sort(employees, new Comparator<Employee>() {
            @Override
            public int compare(Employee employee1, Employee employee2) {
                return employee1.getName().compareTo(employee2.getName());
            }
        });

        // print using for each
        for (Employee employee: employees ) {
            System.out.println(employee.getName());
        }
    }
}

class CodeToRun implements Runnable{
    @Override
    public void run() {
        System.out.println("Printing from the Runnable");
    }
}

class Employee{
    private String name;
    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
