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
        Collections.sort(employees , (Employee e1 ,Employee e2) -> e1.getName().compareTo(e2.getName()));

        for (Employee employee: employees ) {
            System.out.println(employee.getName());
        }
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