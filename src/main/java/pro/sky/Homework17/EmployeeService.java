package pro.sky.Homework17;

import org.springframework.stereotype.Service;
import pro.sky.Homework17.exceptions.EmployeeAlreadyAddedException;
import pro.sky.Homework17.exceptions.EmployeeNotFoundException;
import pro.sky.Homework17.exceptions.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.List;
@Service
public class EmployeeService {
    public List<Employee> employeeList = new ArrayList<>(List.of(
            new Employee("Мирон", "Леонов" ),
            new Employee("Иван", "Маслов" ),
            new Employee("Максим", "Смирнов" ),
            new Employee("Милана", "Елисеева" ),
            new Employee("Елизавета", "Ушакова" ),
            new Employee("Иван", "Колосов" ),
            new Employee("Артемий", "Лапшин" ),
            new Employee("Даниил", "Титов" ),
            new Employee("Давид", "Соловьев" ),
            new Employee("Анастасия", "Анисимова" )
    ));
    private final int MAX_EMPLOYEES = 15;

    public Employee findEmployee(String firstName, String lastName) {
        Employee empl = new Employee(firstName, lastName);
        for (Employee e : employeeList) {
            if (empl.equals(e)) {
                return e;
            }
        }
        throw new EmployeeNotFoundException();
    }
    public void removeEmployee(String firstName, String lastName) {
        Employee empl = findEmployee(firstName, lastName);
        employeeList.remove(empl);
    }
    public void addEmployee(String firstName, String lastName) {
        if (employeeList.size() == MAX_EMPLOYEES) {
            throw new EmployeeStorageIsFullException();
        }
        Employee empl = new Employee(firstName, lastName);
        for (Employee e : employeeList) {
            if (empl.equals(e)) {
                throw new EmployeeAlreadyAddedException();
            }
        }
        employeeList.add(empl);
    }


}
