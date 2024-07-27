package lambda_expressions.part8_function_lambda;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;

/*
 * Function Lambda
 *
 * - In the last section, we said there's more specific flavors of interface Supplier<T>
 *      - BooleanSupplier
 *      - DoubleSupplier
 *      - IntSupplier
 *      - LongSupplier
 *
 * - Let's look at an example
 * - Suppose we want to print the last name of all the employees in the list
 *
 * - Let's look at how we can use forEach first
 *
 *      employees.forEach(employee -> {
            int startIndex = employee.getName().indexOf(' ')+ 1; // get index of the space , start from + 1
            System.out.println(employee.getName().substring(startIndex));
        });
 *
 * - If this is something we wanted to do frequently, we would probably add a getLastName() to the Employee class
 *    but let's pretend we don't want to do that for some reason
 *
 * - Let's pretend there are several places in our application, where we need to get the last name of a single
 *   employee
 * - We want to use a lambda expression that maps to one of the java.util.function interfaces
 *
 * - However, none of the interfaces we've looked at so far matches what we want to do
 *      - interface Consumer<T> does not return a value
 *      - interface Supplier<T> does not accept any parameters
 *      - interface Predicate<T> only returns true/false
 *
 * - We actually want to pass a String & we then expect a String to be returned
 * - This sounds like a job for the interface Function<T,R>
 *
 * Function<T,R> Interface
 *  - Represents a function that accepts one argument and produces a result.
 *  - Method : R apply(T t)
 *
 *  - It takes 1 parameter and returns a value
 *  - The functional method that we use with lambdas is the apply(T t)
 *
 * First
 *  - Let's create the lambda that we're going to use and assign it into a variable
 *
 *      Function<Employee , String> getLastName = employee -> {
            int startIndex = employee.getName().indexOf(' ')+ 1;
            return employee.getName().substring(startIndex);
        };
 *
 * A couple of things to note here
 *  - We've used generics with Function
 *      - The first argument type, in this case would be of type: Employee
 *      - The second type is the return type, which will be of type: String
 *  - We need the curly braces around the return statement, even though, there's only 1 statement
 *
 * - Right now the function above doesn't do anything,
 *      - To get the last name of an employee, we have to call the apply(T t) and pass it to the Employee obj
 *
 *         for (Employee employee: employees ) {
                System.out.println(getLastName.apply(employee));
            }
 * - IF we wanted to get for a single employee
 *      String lastName = getLastName.apply(employee.get(1));
 *      System.out.println(lastName);
 *
 * - We could have just created a regular old method called getLastName()
 * - Also, as with other lambda expressions , we could also use an anonymous class
 *
 * - But by using Function<T,R> we can pass code that accepts and returns a value to a method in the form of a lambda
 *   expression and then run that code without having to create an interface and a class that implements the interface
 *
 * - So, we can change what a method does based on the function we pass in
 *
 * - Let's say sometimes we want the First name of an employee and other times we want the Last name
 * - We could write separate getFirstName() and getLastName() methods , but let's do it with 1 method instead
 *
 * - First
 *      - Create a method for getting the First name
 *
 *       Function<Employee,String> getFirstName = employee -> {
            int lastIndex = employee.getName().indexOf(' ');
            return employee.getName().substring(0,lastIndex);
        };
 *
 * Next,
 *  - Add a method that accepts a Function<Employee, String> and Employee obj as an argument
 *
 *      private static String getAName(Function<Employee,String> getName , Employee employee){
             return getName.apply(employee);
         }
 *
 * Next,
 *  - call getAName() from the main() that gets the first/last name of an employee
 *  - print randomly
 *      - if random.nextBoolean() is true, print first name
 *      - otherwise, print last name
 *
 * Next,
 *  - In a more complex situations, there may be as well cases where we want to run similar code for a set of
 *    situations
 *  - We may have say 30 lines of code we want to run, and only 2 lines of code will vary depending on the situation
 *  - Rather than using interfaces with methods that are called at appropriate points, or writing different method(s)
 *     for every situation, we can actually pass in functions for the parts that vary
 *  - That way the code will be more concise and easier to follow
 *  - Another situations, where functions are useful, is when we want to use callbacks
 *
 * - Suppose we're fetching data from a background thread, and when the data is ready, we want to massage it in
 *   some way on the background thread and the specifics of what we want to do can vary
 * - For example:
 *      - We want to be able to specify what code to run when the data has been fetched
 *
 * - There are a number of ways we could do this
 *      - Java provides ways to notify threads and run the code when a background task is completed, but we can also
 *        use functions
 *
 * - Suppose we're fetching a bunch of images from the internet, and when we have them, we want to resize them all
 *   - The thread that started the background thread is responsible for providing the code that will do the resizing
 *   - We could do it the following way:
 *
 * Example:
 *      public interface ResizeImage() {
 *          Image resizeImage(Image image);
 *      }
 *
 *      public List<Image> runWhenDone(){
 *
 *          for(Image image  :images){
 *              resizedImages.add(resizer.resizeImage(image));
 *          }
 *      }
 *
 * Discussion
 * - The thread that wants images fetched in the background, would have to provide an instance of a class that
 *   implements resize image
 *
 * - When the background thread has the images, it would call runWhenDone() and use a provided instance, to resize
 *   the images
 * - There are different resizing algorithms and the one we want to use may change depending on the current load of
 *   the system
 * - So the requirements quality vs file size , other factors
 * - We have to create a resize image implementation for every algorithm
 *
 * OR
 * - we could use Function<T,R> and do the following
 *
 *      Function<Image, Image> resize1 = (Image image) -> { resize using algorithm 1}
 *      Function<Image, Image> resize2 = (Image image) -> { resize using algorithm 2 }
 *
 *      public List<Image> runWhenDone( Function<Image , Image> resizer){
 *          for(Image image : images)
 *              resizedImages.add(resizer.apply(image));
 *      }
 *
 * - now that we've written this way, we don't need to write an interface, and a bunch of classes that then implement
 *   that interface
 * - we can create a function for each algorithm we may want to use
 *
 * - Again, using lambdas reduces the amount of code we need to write, and in this particular case, will make the code
 *   easier to follow
 *
 * - Rather than having a bunch of classes , each with a resizeImage() , we can actually define all the functions in
 *   1 place
 * - Anytime we need to use a callback, meaning we want to run a code when a specific non user interface event occurs
 *   using a function is 1 option to consider
 *
 * - Hopefully, now you can see the power of using lambdas with functions
 *
 * - Imagine that you want to transform an obj in some way, and the transformation requires 4 steps
 *      - For each step, there are several different algorithms you may want to run
 *      - And which 1 to run will depend on the state of the application at the time or on the object that will be
 *          transformed
 *      - Suppose that there are
 *          - 3 possible algorithms for step-1,
 *          - 2 for step-2
 *          - 5 for step-3
 *          - 3 for step-4
 *      - That's a possible 3 * 2 * 5 * 3 = 90 variations
 *
 * - How would we do this using interfaces in a way that wouldn't be unwieldy and requires us to write lots of
 *   implementing classes
 *      - Using Functions instead would result in less code and the code would also be easier to read
 *          - We'd need to write a method that accepts 4 functions, & then write the 13 functions we may need to use
 *          - When we want to transform an obj, we pass in the appropriate function for each step
 *      - That sounds easier and a lot more readable which is really the power and convenience of using lambda
 *        expressions
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

        employees.forEach(employee -> {
            // get index of the space , add 1 to the index of first space to get the index of the 1st last name char
            int startIndex = employee.getName().indexOf(' ')+ 1;
            System.out.println("Last name is: "+employee.getName().substring(startIndex));
        });

        // returns last name
        Function<Employee , String> getLastName = employee -> {
            int startIndex = employee.getName().indexOf(' ')+ 1;
            return employee.getName().substring(startIndex);
        };

        System.out.println("Print Last name Using Function<T,R> interface\n"+
                "=============================");
        for (Employee employee: employees ) {
            System.out.println(getLastName.apply(employee));
        }

        // returns first name
        Function<Employee,String> getFirstName = employee -> {
            int lastIndex = employee.getName().indexOf(' ');
            return employee.getName().substring(0,lastIndex);
        };

        System.out.println("Print First name Using Function<T,R> interface\n"+
                "=============================");
        for (Employee employee: employees ) {
            System.out.println(getFirstName.apply(employee));
        }

        System.out.println("Using getAName(Function<Employee,String> getName , Employee employee) : get first/last\n"+
                "==========================================================================");
        Random random = new Random();
        for (Employee employee : employees) {
            if (random.nextBoolean())
                System.out.println(getAName(getFirstName , employee));
            else
                System.out.println(getAName(getLastName,employee));
        }

    }

    private static String getAName(Function<Employee,String> getName , Employee employee){
        return getName.apply(employee);
    }
}


