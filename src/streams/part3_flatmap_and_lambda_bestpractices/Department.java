package streams.part3_flatmap_and_lambda_bestpractices;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private String name;
    private List<Employee> employees;

    public Department(String name) {
        this.name = name;
        this.employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee){
        this.employees.add(employee);
    }

    public List<Employee> getEmployees() {
        return this.employees;
    }
}
