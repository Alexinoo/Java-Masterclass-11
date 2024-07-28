package lambda_expressions.part9_chaining_functions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;

/*
 * Chaining java.util.function Functions
 *
 * - Just as Consumer , Supplier and Predicate, there are more specific Function<T,R> interfaces that include the
 *   type of the argument and return value
 * - For example:
 *      LongToIntFunction<Long,Int> accepts long argument and returns an int value
 *      IntDoubleFunction<Integer,Double> accepts an int argument and returns a double value
 * - We can also chain Functions together, just as we could with interface Predicate<T>
 *
 * - To chain functions together, we use the andThen() in the interface Function<T,R>

 * andThen()
 *  - Takes a Function<T,R> as a parameter
 *
 * Let's look at an example -
 *   - Uppercase Employee in 1 function
 *   - Uppercase first name in another function
 *
 *      Function<Employee,String> upperCase = employee -> employee.getName().toUpperCase();
 *
 * But what about the second one ?
 *  - we want to get a substring of the uppercase name
 *  - we want to operate on the value returned from the: Function<Employee,String> upperCase function
 *  - this is exactly how the .andThen() works
 *
 * - When you chain functions together, the return value of each function is operated on by the next function in
 *   the chain
 *      Function<String,String> firstName = name -> name.substring(0,name.indexOf(' '));
 *
 * - Next,
 *   - Let's now chain them together and print the result
 *
 *      Function chainedFunction = upperCase.andThen(firstName);
 *
 * - Function<Employee,String> upperCase is getting the entire employee name converting it into uppercase
 *
 * - While  Function<String,String> firstName is accepting that as a parameter, or will be passing in as a
 *   parameter and that is just returning the first name
 *
 * - chaining functions together requires 2 steps
 *      - Create a composed function
 *      - The Function<T,R> that calls the andThen() will run first and then the Function passes the argument to
 *         andThen() which will operate on the result
 * - we can actually chain as many functions together as we want
 *
 * - Each function on the chain will operate on the last produced result
 * - And as with any Function<T,R>, we call the apply(T t) to run the composed function
 *
 * - Run the function
 *      System.out.println(chainedFunction.apply(employees.get(0))); // JOHN
 *
 * - The Function<T,R> interface also contains a composed method that works in the opposite direction to the
 *  andThen()
 *
 * - The function that calls a method is run second, the function is passed as the argument runs first
 *
 * Let's change the example we just coded,
 *  - Instead of printing the first name, let's say suppose we want to concatenate an employee's name and age
 *    and print the result
 *  - Let's change the second function to do that
 *
 * - But hang on, wait a minute, we'll need the employee obj in the 2nd step, so that we can get the age & we
 *   also need the uppercase from the first step
 *
 * - So, we actually need 2 arguments, but Function<T,R> only accept 1
 *
 * - This is where the BiFunction<T,U,R> comes in
 *
 * - For all the interfaces we have discussed, except Supplier, there are versions of the interfaces that accepts
 *   2 arguments
 * - So, we want to use the BiFunction<T,U,R> interface instead of just Function<T,R>

 * Next
 *  - Let's create function for the 2nd step
 *       BiFunction<String,Employee,String> concatAge = (name , employee) -> {
            return name.concat(" "+employee.getAge());
        };
 *
 * Can we chain this function to the Function<Employee,String> upperCase function ?
 *  - Well, no we can't
 *  - When we chain functions, the result of 1 function becomes the argument for the next function
 *  - But a BiFunction<T,U,R> has 2 arguments , and therefore can't be the 2nd or subsequent function in the chain
 *
 * However, if the BiFunction<T,U,R> was the first step, then we could do it using both functions andThen()
 *  - For the same reason that BiFunction<T,U,R> has to be the first Function in the chain, the BiFunction<T,U,R>
      doesn't have a composed method
 *
 * So, instead of chaining, we have to uppercase and concatenate the age in 2 steps
 *  i.e.
 *      String fullNames = upperCase.apply(employees.get(0)); JOHN DOE
 *      System.out.println(concatAge.apply(fullNames,employees.get(0))); // JOHN DOE : 30
 *
 * - There are also Consumer and Predicate interfaces that accept 2 arguments but none for Supplier because the
 *   supplier doesn't accept any arguments
 *
 *
 * UnaryOperator Interface
 *  - This is a more specific type of Function that accepts a single argument and returns a value of the same type
 *    as the argument
 *  - For example:
 *      LongUnaryOperator accepts a long and returns a long
 *  - All the unary invariants extend the Function interface & therefore they can be chained together just like
 *    functions can
 *  - Let's actually create an IntUnaryOperator that always increments its argument by 5
 *
 *      IntUnaryOperator incBy5 = myNumber -> myNumber + 5;
        System.out.println(incBy5.applyAsInt(10));
 *
 *  - Notice that rather than calling apply(int value), we called applyAsInt(int value) when using the
 *    IntUnaryOperator
 *
 * - So, we've now been through the 5 groups of interfaces in the java.util.function package
 *
 * - These interfaces rep structure for lambda expressions
 * - when we want to use a lambda expression that tests a value, and returns true/false,
 *      - we can use a Predicate<T>
 * - when we want to use a lambda expression in place of a method that accepts an argument and returns a value
 *      - we can use Function<T,R>

 *  - The interfaces don't dictate what the lambda must do,
 *  - They're not like interfaces such as Runnable, which we know rep an obj with a block of executable code or an
 *    event handler which we know contains code that will run when the user interacts with a UI component
 *  - java.util.function interfaces describe the arguments and return value, but not what the implementations must
 *    do
 *
 * Summary of the 5 groups of interfaces in the java.util.function package
 *
 *  Interface       Functional Method No of Arguments   Returns a Value         Can be Chained
 *
 *  Consumer        accept()          1 or 2 (Bi)       No                      Yes
 *
 *  Supplier        get()             0                 Yes                     No
 *
 *  Predicate       test(T t)         1 or 2 (Bi)       Yes - boolean           Yes
 *
 *  Function<T,R>   apply(T t)        1 or 2 (Bi)       Yes                     Yes
 *
 *  UnaryOperator   dependsOnType     1                 Yes same type as arg    Yes
 *
 * - Can be chained column is "Yes" for consumer, though we didn't cover this, but we can chain consumers together
 *   with the consumer .andThen()
 *
 * - Consumers<T> don't return the value, so how does that actually work ?
 *      - well, rather than working on the result of the previous consumer, each consumer in the chain runs using
 *         the argument that's passed to the accept()
 *
 * - Let's look at an example on this
 *      Consumer<String> c1 = s -> s.toUpperCase();
        Consumer<String> c2 = s -> System.out.println(s);
        c1.andThen(c2).accept("Hello World!");
 *
 * So, remember that Consumer<T> interface don't return anything, and so the result of the upper case calling
 *  Consumer<String> c1 , is lost
 *  - Each consumer in the chain is evaluated independently of the others
 *  - Therefore, there's no really advantage in this case to chain them together, though it can be done
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

        Function<Employee,String> fullNamesUpperCase = employee -> employee.getName().toUpperCase();

        Function<String,String> firstName = name -> name.substring(0,name.indexOf(' '));

        Function chainedFunction = fullNamesUpperCase.andThen(firstName);

        System.out.println(chainedFunction.apply(employees.get(0))); // JOHN

        BiFunction<String,Employee,String> concatAge = (name , employee) -> {
            return name.concat(" : "+employee.getAge());
        };

        String fullNames = fullNamesUpperCase.apply(employees.get(0));
        System.out.println(concatAge.apply(fullNames , employees.get(0))); // JOHN DOE : 30

        IntUnaryOperator incBy5 = myNumber -> myNumber + 5;
        System.out.println(incBy5.applyAsInt(10));

        Consumer<String> c1 = s -> s.toUpperCase();
        Consumer<String> c2 = s -> System.out.println(s);
        c1.andThen(c2).accept("Hello World!");
    }

}


