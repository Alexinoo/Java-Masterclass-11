package lambda_expressions.part3_lambda_expression_continued;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/*
 * Lambda Expressions Continued
 *
 * - Sort the names
 *      - Use Collections.sort(List<T> list , Comparator<? super T> c)
 *
 * - Use lambda instead with sort rather than the anonymous class
 *
 *  Collections.sort(employees , (Employee e1 ,Employee e2) -> e1.getName().compareTo(e2.getName()));
 *
 * - The lambda is being passed as the 2nd parameter, instead of an anonymous Comparator
 *
 * - The 3 parts that the lambda consists of are:
 *      - argument list (Employee e1 ,Employee e2)
 *      - arrow token ->
 *      - body e1.getName().compareTo(e2.getName())
 *
 * - but how does this actually work
 *   - when the sort() needs to compare 2 obj(s) in the List, instead of calling the compare() of our comparator and
 *     passing in the 2 objects, it will instead evaluate the lambda expression using the 2 obj(s) it wants to compare
 *     as the parameters to the lambda
 *   - we can even simplify this lambda expression even further
 *
 *      Collections.sort(employees , (e1 ,e2) -> e1.getName().compareTo(e2.getName()));
 *
 *      - the compiler can infer the parameter types, and we don't actually need to include the types in our lambda
 *      - the compiler can infer from the first parameter, "employees" that the obj(s) to be compared will be both
 *        of type employee
 *      - and because of that we can actually leave the types out
 *
 *   - the parenthesis are optional, if we only have 1 parameter in the arguments list
 *
 *  - look at the common use of lambda with a JavaFX application
 *      - add a button to the GridPane
 *      - write an event/action handler in the controller
 *
 *          @FXML
 *          private Button clickMeButton;
 *
 *          public void initialize(){
 *              clickMeButton.setOnAction(new EventHandler<ActionEvent>(){
 *                    @Override
 *                     public void handle(ActionEvent event){
 *                          System.out.println("You clicked me");
 *                     }
 *              });
 *          }
 *      - using an anonymous class with event handler is very common with UI programming especially in Java's old
 *        toolkit Swing - uses code and not like fxml
 *
 *      - but now that we know of lambda expression, whenever we see an anonymous definition that only overrides 1
 *        method, we can consider using a lambda
 *      - let's change the code to do that now
 *
 *          clickMeButton.setOnAction( event -> System.out.println("You clicked me"));
 *
 *      - we don't have to use parenthesis when there's only 1 parameter, we only have to use them where there are 0
 *       or more than 1 parameter
 *      - we map a method to a lambda expression as follows:
 *          - the method parameter becomes the argument list
 *          - the arrow token is mandatory
 *          - the body of the method becomes the body of a lambda expression
 *
 *  - But what about interfaces with a single method that returns a value ?
 *      - we've already used 1 in the Comparator example, but the sort() handled the returned value
 *
 *  - Let's take a closer look with a different example
 *
 *      - Add an interface "UpperAndConcat"
 *          - add an abstract method upperAndConcat()
 *              String upperAndConcat(String s1 , String s2);
 *      - This interface contains an abstract method that uppercase's 2 strings and concatenates them (joins the
 *        second one to the first one)
 *
 * - Next,
 *  - Let's add a static method to the main class that uses an "UpperAndConcat" instance to create a new string
 *
 *      public final static String doStringStuff(UpperAndConcat uc , String s1, String s2){
                 return uc.upperAndConcat(s1,s2);
          }
 *
 *  - This method accepts an obj that implements "UpperAndConcat" interface and the 2 strings that we want to be
 *    converted to uppercase and concatenated
 *  - Then it calls the "upperAndConcat()" from the interface to do the work
 *
 *  - Now inside the main(), what we need to do is to create a new method
 *      - Then we'll pass an anonymous class as the 1st parameter
 *      - Then the first 2 employees from the list as the 2nd and 3rd parameters
 *
 *      String sillyString = doStringStuff(new UpperAndConcat() {
            @Override
            public String upperAndConcat(String s1, String s2) {
                return s1.toUpperCase() + s2.toUpperCase();
            }
        },employees.get(0).getName() , employees.get(1).getName());
 *
 *  - Then print out the sillyString // JACK HILLJOHN DOE
 *
 *  - Well so hopefully at this point you're thinking well, we're using an anonymous class that overrides the only
 *    method in an interface
 *
 *  - Let's use a lambda expression instead
 *
 *  - Now we still have to define the interface, we can't get away from doing that when we're using lambda's but we
 *    can definitely simplify the rest of the code by passing a lambda, instead of an anonymous class
 *
 *  - To start with, let's define the lambda on a separate line and assign it into a variable of type UpperAndConcat
 *
 *      UpperAndConcat us = (String s1,String s2) -> s1.toUpperCase() + s2.toUpperCase();
 *
 *      - Notice here that we haven't included the return keyword, and if we try to add it, we will get a
 *        compiler error
 *      - When the lambda body consists of a single statement that evaluates to a value , the return keyword is
 *        assumed, and the return value is inferred to be the type of the evaluated value which is a String here
 *      - The compiler also infer the type of the parameters and we don't need to include that
 *
 *       UpperAndConcat us = (s1,s2) -> s1.toUpperCase() + s2.toUpperCase();
 *
 *      - And we can delete those, to make our lambda, more concise
 *
 *  - And now that we have the lambda, we can use it as the first parameter in our call to doStringStuff
 *
 *      String sillyString = doStringStuff(uc,employees.get(0).getName() , employees.get(1).getName());
 *      System.out.println(sillyString); // JACK HILLJOHN DOE
 *
 *  - And print out the sillyString , which gives us the same results as before
 *
 *
 *
 *
 */
public class Main {
    public static void main(String[] args) {
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

        //sort
        Collections.sort(employees , (e1 ,e2) -> e1.getName().compareTo(e2.getName()));

        for (Employee employee: employees ) {
            System.out.println(employee.getName());
        }

        // Using an anonymous class
       // String sillyString = doStringStuff(new UpperAndConcat() {
       //     @Override
       //     public String upperAndConcat(String s1, String s2) {
       //         return s1.toUpperCase() + s2.toUpperCase();
       //     }
       // },employees.get(0).getName() , employees.get(1).getName());
       //  System.out.println(sillyString); // JACK HILLJOHN DOE

        // using lambda
        UpperAndConcat uc = (s1, s2) -> s1.toUpperCase() + s2.toUpperCase();
        String sillyString = doStringStuff(uc,employees.get(0).getName() , employees.get(1).getName());
        System.out.println(sillyString); // JACK HILLJOHN DOE
    }

    public final static String doStringStuff(UpperAndConcat uc , String s1, String s2){
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