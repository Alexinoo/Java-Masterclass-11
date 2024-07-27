package lambda_expressions.part5_scope_functional_programming;

import java.util.ArrayList;
import java.util.List;

/*
 * Scope and Functional Programming
 *
 * - We've seen that lambda expressions are treated like nested blocks
 *      - So they're within the enclosing block scope
 * - This means we're able to use the local variable i
 *      - Let's print the value of i within the lambda to see whether that's the case
 *      - And the code works fine by printing the value of i, though i is not declared as final
 *
 * - Let's change the value of i and see what happens
 *      - we now get an error that local variables reference from a lambda expression must be final or effectively
 *        final
 *      - for the same reason, that they have to be declared final, if we want to use them within anonymous classes
 * - By effectively final, the variable has to be declared final, or it's value MUST never change
 *
 * What about variables that we define inside the lambda expression ?
 *  - The parameters s1 and s2 are a good example of this
 *  - Can we use them outside the lambda enclosing block ?
 *      - And no, we can't work with them outside lambda
 *  - So when working with lambda, just imagine the code is enclosed within curly braces {} , sort of similar to
 *    below
 *
 *      int j = 0;
        {
            String s1 = "String1";
            String s2 = "String2";
            System.out.println("The lambda expression's class is "+getClass().getSimpleName());
            System.out.println("i in the lambda expression = "+j);
            String result = s1.toUpperCase() + s2.toUpperCase();
            return result;
        }
 *
 *  - So, this is essentially the scoping
 *  - Though it's not entirely accurate, the 2 parameters aren't local variables and we don't want to return from
 *    the entire method
 *  - The return statement is returning the result of the lambda expression
 *  - But seeing the lambda this way will help us with understanding the scope better , and it's clear that s1 and s2
 *    won't be accessible outside the lambda block
 *
 * - So when working with lambda's , if we want to use the local variables declared in the enclosing block, they
 *   have to be effectively final
 *      - So that the runtime will know what values to use when the lambda expression is evaluated
 *
 * Let's add another method printValue(), and see the above too in action
 *  printValue() : void
 *      - define int variable
 *      - define a runnable that prints the value of the number after 5 seconds
 * - We're using a lambda for a runnable
 *  - when the code runs, the thread will sleep for 5 seconds, and then print the value of the "number" variable
 *  - when the thread is sleeping, the print value will continue to execute and will exit
 *  - this means , by the time the thread gets around to printing the number, the local variable will no longer exist
 *
 * So, what will the thread print ?
 *  - since any variables in the lambda are set when the lambda is processed by the runtime, and those values of those
 *    variables won't change because they're effectively final, the runtime knows it can use the value of 25
 *  - This is because, that was the value of the number when it processed the lambda
 *
 *  - Hopefully, now we get the point that because lambdas may not be immediately evaluated, any variables that we
 *    use within them from outside the lambda, must be final
 *  - We can change variables declared inside the lambda, because they're within the lambda scope, and can't be changed
 *    outside the lambda
 *  - Also, they won't go away because the execution of the enclosing scope has completed as in the case of our
 *    printValue() where the method will finish executing before the lambda runs
 *
 * Next
 *  - Let's add a lambda expression to the enhanced for loop in the main()
 *  - Prints the employee name and start a thread that uses a lambda to print out their age
 *
 * - This works and intelliJ doesn't complain, and we can run the code successfully, and the age is printed at the
 *   console , though in sporadic times and not necessarily in the same order
 *      - we get different ordering each time we're running, because the thread is going to run at different times
 *
 * - But wait a minute, the employee variable is declared outside the lambda, and it's value does change
 * - Doesn't it have to be effectively final to be used withing the lambda ?
 *      - And Yes it does
 *      - In this case, the employee variable is effectively final
 * - When using an enhanced for loop, a new local variable is created for each iteration of the loop, which means
 *   that the variable is effectively final for each iteration
 *
 * Next
 *  - Let's use the traditional for loop to see this more clearly
 *  - This works, but this time, it's clear that on each iteration, a new employee variable is created, and as a result
 *    it's effectively final
 *      - So, it's assigned once and then never changes
 *  - If we move the declaration of the employee variable outside the for loop, then it won't be effectively final
 *    and we won't be able to use it
 *  - For example
 *
 *      Employee employee;
 *      for (int i = 0; i < employees.size(); i++) {
            employee = employees.get(i);
            System.out.println(employee.getName());
            new Thread(()-> System.out.println(employee.getAge())).start();
        }
 *
 *      - Notice, that we automatically get an error from the runnable, which means that local variable "employee"
 *          will change but needs to be final or effectively final
 *
 *  - Comment out the code , that is using the traditional for loop
 *
 * Next
 *  - using lambda expressions, we can do better than this
 *  - we're printing the age in another thread to demonstrate a point, let's just print the ages in the current thread
 *    now
 *      //  new Thread(()-> System.out.println(employee.getAge())).start();
 *
 *  - comment out on the line above
 *
 * Next,
 *  - When lambda were introduced in Java 8, a bunch of other features were also introduced that made use of lambdas
 *      - cover later in upcoming videos
 *  - Let's look at a new method in the iterable interface when we iterate
 *
 *  - Note that, whe we are iterating over a collection, as we're doing here, we can use a new iterable .forEach()
 *
 * forEach()
 *  - takes a lambda expression and evaluates it for each item in the iterable collection
 *      - comment on the enhanced for loop and use forEach iterable instead
 *  - and this works as well, by printing name and ages as we did with the for loops
 *
 * - However, there's a lot going on under the hood when the forEach statement is executed, and we're going to take
 *   a closer look at what is going on in the upcoming videos on the java.util.function package introduced in Java 8
 *
 * - java.util.function package contains a bunch of functional interfaces meant to be used with lambda expressions
 *
 * - Well, but how has the lambda expression enhanced Java ?
 *      - some developers see lambda sa nothing more than syntactic sugar
 *      - or a more convenient and concise way of writing anonymous classes
 *      - they don't actually add anything to the language that wasn't already there
 *
 * - Other people say that lambda is Java's first step into functional programming, which is a programming paradigm
 *  that focuses on computing and returning results
 *
 * - Reference a wikipedia
 *      - link in the resources section
 * - Make up your mind on how significant you think lambda expressions really are
 *
 * - It's also important to note, that we don't have to use lambda expressions, we can always use anonymous class,
 *   and some developers prefer to do so
 *
 *
 * Drawback
 *  - Some lambda expressions can be difficult to read and understand, especially, if you didn't write them yourself
 *
 * However, even if you don't intend to use them, it's important to know about them and how they work because you
 *  might have to work on someone else's code at some point
 *  - And they may have used lambda expressions
 *
 * - And also, it's possible that future enhancements to Java might bring features that require the use of lambda
 *   expressions
 *
 * - But for now, whether to use them or not is up to each developer or an employer's coding standards
 *
 *
 */
public class Main {
    public static void main(String[] args) {

        Employee john = new Employee("John Doe",30);
        Employee tim = new Employee("Tim Buchalka",21);
        Employee jack = new Employee("Jack Hill",40);
        Employee snow = new Employee("Snow White",22);

        List<Employee> employees = new ArrayList<>();
        employees.add(john);
        employees.add(tim);
        employees.add(jack);
        employees.add(snow);

        /*
        for (Employee employee: employees) {
            System.out.println(employee.getName() +" : "+ employee.getAge());
          //  new Thread(()-> System.out.println(employee.getAge())).start();
        } */


       /* System.out.println("**********************");
        Employee employee;
        for (int i = 0; i < employees.size(); i++) {
            employee = employees.get(i);
            System.out.println(employee.getName());
            new Thread(()-> System.out.println(employee.getAge())).start();
        } */

        // Using Collections.forEach(Consumer<? super T> action)
        employees.forEach( employee -> System.out.println(employee.getName() + " : "+ employee.getAge()));
    }

    public static String doStringStuff(UpperAndConcat uc , String s1 , String s2){
        return uc.upperAndConcat(s1,s2);
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

interface UpperAndConcat {
    String upperAndConcat(String s1, String s2);
}
class AnotherClass {
    public String doSomething(){
        int i = 0;
        //i++;
        UpperAndConcat uc = (s1 , s2) -> {
            System.out.println("The lambda expression's class is "+getClass().getSimpleName());
            System.out.println("i in the lambda expression = "+i);
            return s1.toUpperCase() + s2.toUpperCase();
        };

        System.out.println("The AnotherClass class's name is: "+getClass().getSimpleName());
        System.out.println("i = "+i);
        return Main.doStringStuff(uc,"String1","String2");
    }

    public void printValue(){
        int number = 25;
        Runnable r = () -> {
            try{
                Thread.sleep(5000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("The value is "+number);
        };
        new Thread(r).start();
    }
}
