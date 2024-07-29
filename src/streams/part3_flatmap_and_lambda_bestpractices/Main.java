package streams.part3_flatmap_and_lambda_bestpractices;

import java.util.ArrayList;
import java.util.List;

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
 *
 *
 */
public class Main {
    public static void main(String[] args) {
        Employee john = new Employee("John Doe",30);
        Employee jack = new Employee("Jack Hill",30);
        Employee jane = new Employee("Jane Deer",30);
        Employee snow = new Employee("Snow White",30);

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

        //streams
        departments
                .stream()
                .map(department -> department.getEmployees().stream())
                .forEach(System.out::println);


    }
}
