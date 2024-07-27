package lambda_expressions.part7_more_on_predicate_and_suppliers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;

/*
 * More on Predicates and Suppliers
 *
 * - As you can see, we have created Predicate arguments and passed them to printEmployeesByAge()
 * - The Predicate arguments are just lambda expressions that match the interface Predicate<T>

 * - They accept 1 parameter and return a boolean value
 *
 * - This is really powerful because we can pass all sorts of different conditions & we don't have to write a
 *   different if condition to filter the employees based on different age criteria
 *      - All we need is a new lambda expression that can map to the interface Predicate<T>

 * - The instructor mentioned in previous video, that we don't have to use lambda expressions, we can always use an
 *   anonymous class, which is also true in this case
 *      - We can pass an anonymous predicate to the printEmployeesByAge()
 *
 * - Let's do it that to print Employees that are younger than 25
 *
 *       printEmployeesByAge(employees, "Employees under 25 ", new Predicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getAge() <= 25;
            }
        });
 *
 * - Yes we can use an anonymous class here, but when working with interfaces java.util.function package, it is
 *   common and more convenient to use lambda
 *
 * - However, if we don't want to use them, we can always use anonymous class as we did above
 *
 * - The interface Consumer<T> and interface Predicate<T> are general interfaces that don't care about the type of
 *   parameter being passed to the lambda expression
 *      - They're using Generics to infer the type
 * - There are other consumer and predicate interfaces that expect a specific type of parameter
 *
 * - In addition to Consumer , we have
 *  - For example:
 *     DoubleConsumer<T> expects a lambda expression that has a double argument
 *     IntConsumer<T> expects a lambda expression that has an int argument
 *     LongConsumer<T> expects a lambda expression that has a long argument
 *
 * - In addition to Predicate, we have
 *  - For example:
 *     DoublePredicate<T> expects a lambda expression that has a double argument
 *     IntPredicate<T> expects a lambda expression that has an int argument
 *     LongPredicate<T> expects a lambda expression that has a long argument
 *
 * - Where possible, you probably should use the more specific type of lambda to improve readability
 *
 * Example:
 *  - Add an IntPredicate that tests whether an integer > 15
 *
 * Solution
 *      IntPredicate greaterThan15 = myNumber -> myNumber > 15;
 *
 * - The above predicate doesn't do anything, same as an anonymous class instance that we assign to a variable
 * - And so, we need to use it in some way
 *
 * - Let's print the value of the predicate when the value is 10
 *
 * - Remember that we have to call the method that the lambda maps to which is test(int value) in this case and
 *   pass 10
 *      - Note that in this case, the test() require us to pass an int value : test(int value)
 *
 * - Note that we can also pass any integer to test including a mathematical expression that returns an int variable
 *   or a method that returns an int
 * - For example:
 *
 *      int a = 20;
        System.out.println(a+5 +" is greater than 15: " +greaterThan15.test(a + 5));
 *
 * - Another cool thing with Predicates is that we can chain them together
 *
 * - Suppose we wanted to test whether a number is greater than 15 and less than 100
 *     - We could update our existing Predicate to include the 2nd part of this condition
 *     - But let's say we don't want to do that & we want to use our existing predicate, perhaps elsewhere
 *
 * - Therefore, we can create a 2nd Predicate and chain it to the first one
 *
 * - Let's create the 2nd Predicate
 *      IntPredicate lessThan100 = myNumber -> myNumber < 100;
 *
 * - We join both predicates by using .and() as below
 *      greaterThan15.and(lessThan100).test(50); // true
 *
 * - The test(int value) will return true if both predicates return true when evaluated and false if one of the
 *   predicates returns false
 *      greaterThan15.and(lessThan100).test(101); // false (one of the predicate returns false)
 *
 * - You have noted that we have used "myNumber" as the parameter
 * - Remember that each lambda expression is like a nested block - imagine it as each line of code surrounded
 *   by curly braces
 *
 * - Working as below
 *
 *  {
 *      int myNumber;
 *      return myNumber > 15;
 *  }
 *
 *  {
 *      int myNumber;
 *      return myNumber < 100 ;
 *  }
 *
 * - That's essentially what is happening here
 * - Each variable is within the scope of a single lambda, and we can say both of them are independent of each other
 *
 * Next
 *  - The interface Predicate<T> also contains .or() or equals() methods
 *
 * - The more specific predicate interfaces contain or negate, but they don't contain isEquals, and they don't extend
 *   predicate
 * - The and() ,or(), negate methods can be chained together
 *
 * - This chaining is useful, if there's a situation where we need to build a condition on the fly, or when we want
 *   to use predicates in different areas of our application
 *
 *
 *
 * Supplier Interface
 *  - The interface Supplier<T> doesn't accept any arguments but it does return a value
 *      - print between 0 - 1000
 *
 *
 * Example:
 *  - Suppose we want to print 10 random numbers between 1 and 1000 - use the Random class
 *  - Using a normal for loop, we would do something like this
 *
 *       for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt(1000));
        }
 *
 * - However, using a Supplier, we would do something
 * - But because a supplier always returns a value, we have to include the expected return type when we declare
 *   it
 * - We use get() to get an object from the supplier
 *
 *      Supplier<Integer> randomSupplier = ()-> random.nextInt(1000);
        for (int i = 0; i < 10; i++) {
            System.out.println(randomSupplier.get());
        }
 *
 * - Notice, the empty argument list below
 *      ()-> random.nextInt(1000);
 *
 * - We can pass suppliers to methods
 * - We can have a situation where we want to supply an obj of type A, and other times an obj of type B , where
 *   B is a subclass of A
 * - We may have a case where a supplier is providing an IO stream or is used as a counter
 * - We can also use supplier when testing an application
 * - A supplier could instantiate objects and perhaps populate them with random values
 *
 * - Just with predicates, there's more specific flavors of suppliers
 *      - BooleanSupplier
 *      - DoubleSupplier
 *      - IntSupplier
 *      - LongSupplier
 *
 * - We'll look at a function lambda next which uses the Function Interface to call lambdas in a different way
 *
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
        System.out.println("Using Predicate with lambda : over 30\n"+
                "===================================================================================");
        printEmployeesByAge(employees , "Employees over 30 ",employee -> employee.getAge() > 30);

        System.out.println();
        printEmployeesByAge(employees , "Employees 30 and younger ",employee -> employee.getAge() <= 30);



        System.out.println("Using Predicate with anonymous class : over 30\n"+
                "===================================================================================");
        printEmployeesByAge(employees, "Employees under 25 ", new Predicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getAge() <= 25;
            }
        });

        // First - IntPredicate
        System.out.println("IntPredicate\n"+
                "===============");
        IntPredicate greaterThan15 = myNumber -> myNumber > 15;
        System.out.println("10 is greater than 15: " +greaterThan15.test(10));
        System.out.println("20 is greater than 15: " +greaterThan15.test(20));

        int a = 20;
        System.out.println(a+5 +" is greater than 15: " +greaterThan15.test(a + 5));

        // Second - IntPredicate
        IntPredicate lessThan100 = myNumber -> myNumber < 100;

        System.out.println("50 is > 15 and < than 100 " +greaterThan15.and(lessThan100).test(50));
        System.out.println("101 is > 15 and < than 100 " +greaterThan15.and(lessThan100).test(101));


        // Print Random numbers - 0 -1000
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt(1000) + " ");
        }

        // change above to use Supplier interface
        System.out.println("Using Supplier Interface\n"+
                "===============");
        Supplier<Integer> randomSupplier = ()-> random.nextInt(1000);
        for (int i = 0; i < 10; i++) {
            System.out.println(randomSupplier.get());
        }

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


