package lambda_expressions.part4_lambda_expression_nested_blocks;
/*
 * Lambda Expressions - Nested Blocks
 *
 * - In the last example "part3" , we got the same result as we did with the other commented out code
 * - But we wrote less code to accomplish what we wanted to do
 * - And instead of passing an anonymous instance of our UpperAndConcat object, we passed a lambda instead
 *
 * - Now we could have done it , in only 1 line of code, if we passed the lambda expression directly to doStringStuff()
 *   rather than saving it into a variable first
 * - But the instructor wanted to show us that we can assign a lambda to variables and use them later
 *
 * - And if we wanted to use the same lambda in more than 1 place, we only have to define it once and then we can use it
 *   wherever we need it
 *
 * - Now if our lambda contained more than 1 statement, then we would have to use the return keyword
 *
 * - So let's change the result of our lambda, so that it stores the result of the uppercasing and concatenation into
 *   a new string variable
 *      - need to add a code block to our lambda
 *      - define result variable of type String
 *      - return the String variable as shown below
 *
 *       UpperAndConcat uc = (s1, s2) -> {
            String result = s1.toUpperCase() + s2.toUpperCase();
            return result;
        };
 *
 *      - we've used curly braces, because the body has more than 1 statement
 *      - we also need semicolons after each statement in the body
 *      - then , once we add the curly braces, the return keyword is required even if there's only 1 statement in the
 *        body
 *      - And we still get the same results
 *
 * Next,
 *  - Let's actually create a class that isn't static, so that we can take a look at lambda expression and scope
 *
 * class : AnotherClass
 *  methods:
 *      doSomething : void
 *          - returns Main.doStringStuff(UpperAndConcat instance, "String1","String2");
 *
 */


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        Collections.sort(employees , (e1 , e2) -> e1.getName().compareTo(e2.getName()));

        for (Employee employee: employees ) {
            System.out.println(employee.getName());
        }

        // using lambda
        UpperAndConcat uc = (s1, s2) -> {
            String result = s1.toUpperCase() + s2.toUpperCase();
            return result;
        };

        String sillyString = doStringStuff(uc,employees.get(0).getName() , employees.get(1).getName());
        System.out.println(sillyString); // JACK HILLJOHN DOE

    }

    private static String doStringStuff(UpperAndConcat uc , String s1 , String s2){
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