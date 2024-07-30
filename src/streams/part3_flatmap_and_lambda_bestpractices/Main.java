package streams.part3_flatmap_and_lambda_bestpractices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * Streams - Flatmap and Lambda Best Practices
 *
 * flatMap()
 *
 * map()
 * - We've seen map() which transforms 1 obj.
 * - It takes Function<T,R> as argument which takes 1 object and returns a value
 *
 * flatMap()
 *  - We may want to map a single object to more than 1 obj, and we can use flatMap() to achieve that
 *  - flatMap() accepts a function that returns a String value,
 *  - we can pass an object as the function argument and return a String containing several objects which means that we're effectively mapping 1
 *    obj to many
 *
 * Example
 *  - Create Employee class
 *
 *  - Create Department class
 *      Fields :
 *       name : String
 *       employees : List<Employee>

 *      Constructor
 *       Department(String name)
 *          - initialize employees ArrayList
 *
 *      Method
 *      addEmployee(Employee employee) : void
 *
 *      Getter
 *      getEmployees : List<Employee>

 * Next
 *  - Create 4 employees
 *
 *  - Create 2 departments - HR & Accounts
 *
 *  - Add jack, jane and snow to "HR"
 *  - Add john to "Accounts"
 *
 * - Suppose we want to print ou all the employees that work for the company
 *      - We can create a List<Department> called departments and add bot of our departments to it
 *
 * - We could add getEmployees or printEmployees() to the Department class, and then iterate over this list and print employees
 *    but since we're working on streams, let's use streams and see how flatMap() might help us
 *
 *       departments
                .stream()
                .flatMap(department -> department.getEmployees().stream())
                .forEach(System.out::println);

 * - The flatMap() wants a Function<Employee, Stream> that returns a stream
 *   - Each department in the source stream becomes the argument to the function
 *      e.g. Function<Department,Stream<Employee>>
     - For each department, we call department.getEmployees() which returns a list  "List<Employee>" and then we
 *     call stream() on that list to return a stream of employees "Stream<Employee>"
 *   - The items in the stream are added to the stream that will be returned from the flatMap()
 *   - In case, of HR Dept, we've gone from 1 department obj to 3 Employee obj(s)
 *      - The method is called flatMap because it's often used to flatten nested lists
 *
 *   - In this case, there are List<Employee> nested within the Department list
 *   - flatMap() is the method to use when we want to perform operations on the list, but the list isn't the source
 *   - An obj containing the list as a source, in this scenario, and we use to create a Stream of all the obj(s) in
 *     those list
 *
 * collect()
 *  - is a terminal operation
 *  - collects the elements in a stream into a different type of result
 *  - For example:
 *      - We can add all the items in the stream to list/set
 *  - After all, the purpose of the chain is to produce a list of items to print, but you want to save the results
 *    in some way
 *  - You might use streams to selectively reduce and sort a large list, as you may want to use a reduced list in
 *    subsequent code
 *  - So we'll use the collect() to create the reduced list
 *
 * Example:
 *  - Let's say we wanted to save the result of the first example, where we uppercase, filtered and sorted bingo
 *    numbers
 *  - Instead of printing the list, we could call collect() and store the contents of the stream at the end of the
 *    chain into a list , and then print the items in the list
 *
 *       List<String> sortedGNumbers = someBingoNumbers
                .stream()
                .map(String::toUpperCase)
                .filter(myString -> myString.startsWith("G"))
                .sorted()
                .collect(Collectors.toList());
 *
 * - print the list using enhanced for loop or forEach is up to you
 *  - we get the same results, but here we're using .collect() and now we have a list that we can work with if we
 *    wanted to
 *
 * There are 2 versions of collect()
 *  - 1 accepts a collector which is an interface to the java.util.stream package
 *      - under the hood, this version of the method maps the collector to the arguments required by the second
 *        version of the method, which accepts/expects rather 3 arguments
 *          - a Supplier
 *          - a BiConsumer accumulator
 *          - a BiConsumer combiner
 *      - these arguments lets us be specific about how we want the items to be added to the result of collect()
 *
 * Example
 *  - Suppose , we wanted to end up with an ArrayList, rather than a List, we can use the more specific collect()
 *    version of the method to do this
 *
 *      List<String> sortedGNumbersArrayList = someBingoNumbers
                .stream()
                .map(String::toUpperCase)
                .filter(myString -> myString.startsWith("G"))
                .sorted()
                .collect(ArrayList::new,ArrayList::add,ArrayList::addAll);
 *
 *  - Where
 *      - Array::new is the first argument to collect which is the Supplier
 *          - If you recall, a Supplier creates obj(s) and since we want an ArrayList, we pass ArrayList::new as the
 *            supplier, which will construct a new ArrayList for us
 *          - When passing method references, we pass the name of the class in this case ArrayList::new when we
 *            want to pass the constructor
 *      - ArrayList::add is the second argument which is the add()
 *          - That's how we'll add the items to the ArrayList
 *      - ArrayList::addAll is the combiner
 *  - The accumulator is used when the runtime needs to add a single version or a single item into the list
 *  - The accumulator is used when the runtime needs to add a single item into a result
 *
 *  - The combiner is sometimes used to improve the efficiency of the operation, even if and when to do this is
 *    really up to the Java runtime
 *
 *  - There are many API's that we can map to the supplier , accumulator and the combiner, which allows us to
 *    do all sorts of things with collect()
 *
 *  - For example:
 *      - If we have a lot of employees, and there are groups of employees, with the same age, we can create a map
 *        of lists based on age using the Collectors.groupingBy()
 *
 *              Map<Integer,List<Employee>> groupedByAge = departments
                    .stream()
                    .flatMap(fetchEmployees)
                    .collect(Collectors.groupingBy(employee -> employee.getAge()));
 *
 * - The above will allow us to do that
 *
 *
 * reduce()
 *  - combines all the items in a string into a single result
 *  - there are 3 versions of this obj
 *  - we will take a look at the simple one which produces a stream to one of the elements in the stream
 *
 * For example:
 *  - Suppose we wanted to use a Stream to find the youngest employee in the company, we can do something like
 *    this
 *
 *       departments
                .stream()
                .flatMap(fetchEmployees)
                .reduce((e1,e2)-> e1.getAge() < e2.getAge() ? e1 : e2)
                .ifPresent(System.out::println);
 *
 *  - We're using reduce() that accepts a BiFunction<T,U,R> , which accepts 2 arguments and returns a result
 *      - The function compares the ages of 2 employees and return the younger employee
 *      - The result of the reduced method will be the youngest employee in the stream
 *      - We use ifPresent() - to print the result because this form of the reduce() has an optional result
 *      - The reduce() is a terminal operation and the call to ifPresent() isn't part of the stream operation
 *         chain as such
 *      - Here we're chaining a non stream method call onto the stream chain result
 *      - Prints "Snow White" as the youngest employee
 *
 * Few Notes on Stream
 *  - We can't re-use them once we've called on terminal operation on a stream, will receive illegalStateException
 *    if we try to operate on that stream again
 *  - Operations in a stream are lazily evaluated - means intermediate operations are not performed, until there's
 *    a terminal operation
 *  - We can work with more specific stream interfaces when working with lists of numbers
 *  - For example
 *      - IntStream
 *      - LongStream
 *      - DoubleStream
 *  - These interfaces have additional methods like sum, min, max and a few others that are useful when working
 *    with numbers
 *
 * Parallel Streams
 *  - We use them when we want to increase performance by executing streams in parallel
 *  - largely dependent on your application
 *
 *
 * Streams are lazily evaluated
 *  - Let's look at an example on this
 *      - Create a Stream<String> and filter them on the stream
 *
 *       Stream.of("ABC","AC","BAA","CCCC","XY","ST")
                .filter(s -> {
                    System.out.println(s);
                    return s.length() == 3;
                });
 *
 *
 */
public class Main {
    public static void main(String[] args) {
        Employee john = new Employee("John Doe",30);
        Employee jack = new Employee("Jack Hill",25);
        Employee jane = new Employee("Jane Deer",40);
        Employee snow = new Employee("Snow White",22);

        Department hr = new Department("Human Resource");
        hr.addEmployee(jack);
        hr.addEmployee(jane);
        hr.addEmployee(snow);

        Department accounts = new Department("Accounts");
        accounts.addEmployee(john);


        //departments
        List<Department> departments = new ArrayList<>();
        departments.add(hr);
        departments.add(accounts);

        //Function-wise
        Function<Department, Stream<Employee>> fetchEmployees = department -> department.getEmployees().stream();

        //streams : flatMap()
        departments
                .stream()
                .flatMap(fetchEmployees)
                .forEach(System.out::println);

        List<String> someBingoNumbers = Arrays.asList(
                "N40","N36",
                "B12","B6",
                "G53","G49","G60","G50","g64",
                "I26","I17","I29",
                "O71");



        System.out.println("Collect to a List<String>\n"+
                "-----------------------------------------");

        List<String> sortedGNumbersList = someBingoNumbers
                .stream()
                .map(String::toUpperCase)
                .filter(myString -> myString.startsWith("G"))
                .sorted()
                .collect(Collectors.toList());


        sortedGNumbersList.forEach(System.out::println);


        System.out.println("Collect to an ArrayList<String>\n"+
                "-----------------------------------------");
        List<String> sortedGNumbersArrayList = someBingoNumbers
                .stream()
                .map(String::toUpperCase)
                .filter(myString -> myString.startsWith("G"))
                .sorted()
                .collect(ArrayList::new,ArrayList::add,ArrayList::addAll);


        sortedGNumbersArrayList.forEach(System.out::println);


        System.out.println("Collectors.groupingBy count and group by age <String>\n"+
                "-----------------------------------------");
        Map<Integer,List<Employee>> groupedByAge = departments
                .stream()
                .flatMap(fetchEmployees)
                .collect(Collectors.groupingBy(employee -> employee.getAge()));

        System.out.println("reduce - youngest employee in the company\n"+
                "-----------------------------------------");

        departments
                .stream()
                .flatMap(fetchEmployees)
                .reduce((e1,e2)-> e1.getAge() < e2.getAge() ? e1 : e2)
                .ifPresent(System.out::println);

        System.out.println("Streams are Lazily evaluated \n"+
                "-----------------------------------------");
        Stream.of("ABC","AC","BAA","CCCC","XY","ST")
                .filter(s -> {
                    System.out.println(s);
                    return s.length() == 3;
                });




    }
}
