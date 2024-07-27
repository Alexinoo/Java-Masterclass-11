package lambda_expressions.part6_functional_interfaces_and_predicates;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/*
 * Functional Interfaces and Predicates
 *
 * - We'll dive into java.util.function package, added in Java 8
 * - This package contains functional interfaces that are meant to be used with lambda expressions
 * - The interfaces aren't meant to do anything specific, they represent the structure of lambda expressions that are
 *   commonly used in Java
 *
 * forEach
 * - we've used the forEach() to iterate over the employees list
 *
 * - forEach() is a method from java.lang.Iterable class and if we control + click, we can see that it's actually
 *   looking for a parameter of type java.util.function.Consumer<? super T> action
 *
 * - so let's take a look at the Consumer interface and see what that is all about
 *
 * Interface Consumer<T>
 * - This is a functional interface and can be used as a target for lambda expressions
 *      - has a functional method
 *           accept(Object obj) : void
 *
 * - It also has an additional method called andThen(), but with a default implementation
 *
 * - This means Consumer Interface has 1 method that needs to be implemented, and so it's a functional interface
 *   and therefore lambda expressions can map to it
 *
 * - The accept() , performs this operation on the given argument
 * - But we're not calling accept though, we're calling forEach,
 *      - If you take Ctrl + click on forEach, we can see that forEach(Consumer<? super T> action) calls accept
 *        and that it returns void
 *
 *      default void forEach(Consumer<? super T> action) {
            Objects.requireNonNull(action);
            for (T t : this) {
                action.accept(t);
            }
        }
 *
 * So what does all this actually mean ?
 *  - We pass forEach a lambda expression that meets the requirements of the Consumer interface
 *  - A consumer accepts 1 argument and performs an action that doesn't return a value
 *
 * - It's called a consumer, because it's object in , nothing out
 *      - This is perfect when we just want to iterate over the list and print the contents
 * - And we can also write an old style for loop or an enhanced for loop to do the work, though with forEach,
 *   it's concise and clear
 *
 * - In the case of the forEach, the lambda we're using takes an employee obj, and it doesn't return anything and so
 *   it matches what the forEach() expects a consumer
 * - When we call forEach, it iterates over the list
 *      - On each iteration, it calls the consumers accept(T t) passing the employee obj as the parameter
 *      - the accept(T t) evaluates the lambda expression, using the passed employee obj as the argument
 *      - employee name and age are printed to the console and nothing is returned
 *      - the employee is consumed, and the iterator moves to the next employee in the list
 *
 *  - As we said previously, there's a lot going on with forEach, but essentially, it all boils down to the iterator
 *    calling the Consumers interface  "void accept(T t)" for each object in the list
 *      - The accept(T t) evaluates the lambda expression passed to by forEach(), using the passed object as the argument
 *
 *       default void forEach(Consumer<? super T> action) {
            Objects.requireNonNull(action);
            for (T t : this) {
                action.accept(t);
            }
        }
 *
 * - We've seen an example of how a Consumer Interface is used.
 * - java.util.function package contains over 30 Functional Interfaces
 *
 * Next,
 *  - Note that if we click on the lambda symbol in the gutter, it takes us to the accept(T t) in the Consumer interface
 *    just as we expected
 *  - Ultimately, it's the Consumer which was passed as a lambda expression that does the work
 *
 * So how else are the interfaces in the java.util.function package used ?
 *  - Well suppose we want to print all employees older than 30 in the console
 *  - add a couple of more employees to make this more interesting
 *
 * Next
 *  - Use an Enhanced for loop
 *      - use an if statement and check age > 30
 *      - print name and age
 *
 *  - Use a lambda expression
 *      - call forEach on employees arrayList
 *      - pass a lambda and opens a code block
 *      - check if age > 30
 *      - print out name and age
 *
 *  - Again, we've passed forEach() , a lambda expression that meets the requirements of interface Consumer<T>

 * Next
 *  - Print Employees 30 and younger
 *      - check age <= 30
 *
 * - While this work, we're actually repeating ourselves
 *  - the only difference between the first and the 2nd lambda expression is the if condition
 *
 * Solution
 *  - We could move the code to a method and pass a boolean parameter that tells the method whether we want the
 *    younger or older employees
 *  - For example:
 *      - pass a true, if we want > 30
 *      - pass a false, if we want <= 30
 *  - It would work, but feels like a hack and perhaps there is a better way
 *
 * - And this looks like a great place to use a Predicate interface
 *
 * Predicate Interface
 * - A Predicate Interface is built into java.util.function package with a test() as the functional method
 * - The test(Predicate<> super T> action) accepts a Predicate that returns a boolean value
 * - We can replace the if conditions we're using in our forEach calls with a Predicate
 *
 * Next
 *  - Create a method printByAge
 *
 *      printByAge(List<Employee> employees) : void
 *  - We'll then pass "Employee over" as a parameter
 *  - Then we'll need to pass the condition we'll test the employee's age against and that's where the Predicate comes
 *    in
 *  - We can't use forEach loop because it expects a Consumer Interface, and instead we need to go back and use an
 *    enhanced for loop
 *
 *  - Therefore, in general, we'll pass the following parameters
 *      printEmployeesByAge(List<Employee> employees , String ageText, Predicate<Employee> ageCondition)
 *
 *  - Use enhanced for loop as below
 *       for (Employee employee: employees  ) {
            if (ageCondition.test(employee)){
                System.out.println(employee.getName() + " : "+ employee.getAge());
            }
        };
 *
 *  - The if statement, is now using the Predicate parameter, and fully passes each employee to the
 *     interface Predicate<T>, boolean test(T t)
 *
 *  - The boolean test(T t) then uses the Employee obj as a parameter for the lambda expression that
 *    maps to the predicate
 *
 * - It's quite similar to foreach that uses the interface Consumer<T>

 *
 * interface Predicate<T>
 *  - If the test(T t) returns true, the employee will be printed
 *
 * Next
 *  - call printEmployeesByAge() to the main() and pass the required params
 *      - print employee > 30
 *
 *          printEmployeesByAge(employees , "Employees over 30 ",employee -> employee.getAge() > 30);
 *
 *      - print employee <= 30
 *
 *          printEmployeesByAge(employees , "Employees under 30 ",employee -> employee.getAge() <= 30);
 *
 *  - We can see that the Predicate<Employee> condition arguments are just lambda expressions that match the
 *     interface Predicate<T>
 */
public class Main {
    public static void main(String[] args) {

        Employee john = new Employee("John Doe",30);
        Employee tim = new Employee("Tim Buchalka",21);
        Employee jack = new Employee("Jack Hill",40);
        Employee snow = new Employee("Snow White",22);
        Employee red = new Employee("Red RidingHood",35);
        Employee charming = new Employee("Prince Charming",31);

        List<Employee> employees = new ArrayList<>();
        employees.add(john);
        employees.add(tim);
        employees.add(jack);
        employees.add(snow);
        employees.add(red);
        employees.add(charming);

        // Using Iterable.forEach(Consumer<? super T> action)
        employees.forEach( employee -> System.out.println(employee.getName() + " : "+ employee.getAge()));

        //use enhanced for loop
        System.out.println("Using Enhanced For loop : Employees Over 30 \n"+
        "===========================================");
        for (Employee employee: employees) {
            if (employee.getAge() > 30)
                System.out.println(employee.getName() + " : "+ employee.getAge());
        }

        // using a lambda expression
        System.out.println("Using lambda expression : Employees Over 30 \n"+
                "===========================================");
        employees.forEach(employee -> {
            if (employee.getAge() > 30){
                System.out.println(employee.getName() + " : "+ employee.getAge());
            }
        });

        // using a lambda expression
        System.out.println("Using lambda expression : Employees under 30 and below \n"+
                "===========================================");
        employees.forEach(employee -> {
            if (employee.getAge() <= 30){
                System.out.println(employee.getName() + " : "+ employee.getAge());
            }
        });

        // using printEmployeesByAge method
        System.out.println("Using Predicate : over 30\n"+
                "===================================================================================");
        printEmployeesByAge(employees , "Employees over 30 ",employee -> employee.getAge() > 30);

        System.out.println();
        printEmployeesByAge(employees , "Employees 30 and younger ",employee -> employee.getAge() <= 30);

    }

    public static void printEmployeesByAge(List<Employee> employees, String ageText,
                                           Predicate<Employee> ageCondition){
        System.out.println(ageText+": \n"+
                "===========================================");
        for (Employee employee: employees  ) {
            if (ageCondition.test(employee)){
                System.out.println(employee.getName() + " : "+ employee.getAge());
            }
        }
    }
}


