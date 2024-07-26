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
 *          - call Main.doStringStuff()
 *              - and pass UpperAndConcat interface instance
 *              - pass "String1" and "String2"
 *              - return the result;
 *
 * Next - in the main class
 *  - Construct an instance of AnotherClass and call doSomething()
 *
 * Next
 *  - Print out the class name of the Another class before we return the result of Main.doStringStuff()
 *  - Print out the class name of the Anonymous class before we return the result of Main.doStringStuff()
 *
 * Result:
 *  - The AnotherClass class's name is: AnotherClass
 *  - The anonymous class's name is:
 *
 * - This is correct because of course the anonymous class doesn't have a name, which makes sense,
 *
 * - But let's actually now use a lambda expression instead of an anonymous class
 *
 *        UpperAndConcat uc = (s1,s2)-> {
            System.out.println("The lambda expression class is: "+getClass().getSimpleName());
            return s1.toUpperCase() + s2.toUpperCase();
        };
 *
 * - And also print inside AnotherClass
 *
 *      System.out.println("The AnotherClass class's name is: "+getClass().getSimpleName());
 *
 * Result:
 *  - The AnotherClass class's name is: AnotherClass
 *  - The lambda expression class is: AnotherClass
 *
 * So what is this telling us ?
 *  - Well, the lambda or that a lambda expression isn't a class,
 *  - When the code runs, an anonymous instance isn't created
 *  - instead the lambda is treated as a nested block of code - sames as the code below
 *       {
 *           System.out.println("The nested code class is: "+getClass().getSimpleName());
 *       }
 *  - and has the same scope as a nested block above
 *
 *
 * - Let's look at other example so that this is much clear
 *      - Let's look at a nested block that isn't part of a lambda
 *      - Instead of using a lambda in the doSomething(), we'll do it the long way, by using anj anonymous class
 *      - We'll also put all the code inside a nested block
 *
 * - By putting the code inside a nested block, what that means is that we've enclosed it with another set of curly braces
 *
 */



public class Main {
    public static void main(String[] args) {
        AnotherClass anotherClass = new AnotherClass();
        String result = anotherClass.doSomething();
        System.out.println(result);
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

    // Using anonymous class
    /*public String doSomething(){
        System.out.println("The AnotherClass class's name is: "+getClass().getSimpleName());
        return Main.doStringStuff(new UpperAndConcat() {
            @Override
            public String upperAndConcat(String s1, String s2) {
                System.out.println("The anonymous class's name is: "+getClass().getSimpleName());
                return s1.toUpperCase() + s2.toUpperCase();
            }
        },"String1","String2");
    } */

    // Using lambda
   /*  public String doSomething(){

        {
            System.out.println("The nested code class is: "+getClass().getSimpleName());
        }

        UpperAndConcat uc = (s1,s2)-> {
            System.out.println("The lambda expression class is: "+getClass().getSimpleName());
            return s1.toUpperCase() + s2.toUpperCase();
        };

        System.out.println("The AnotherClass class's name is: "+getClass().getSimpleName());
        return Main.doStringStuff(uc,"String1","String2");
    } */

    // nested block
    public String doSomething() {
        {
            UpperAndConcat uc = new UpperAndConcat() {
                @Override
                public String upperAndConcat(String s1, String s2) {
                    return s1.toUpperCase() + s2.toUpperCase();
                }
            };

            System.out.println("The AnotherClass class's name is: "+getClass().getSimpleName());
            return Main.doStringStuff(uc,"String1","String2");
      }
    }

}