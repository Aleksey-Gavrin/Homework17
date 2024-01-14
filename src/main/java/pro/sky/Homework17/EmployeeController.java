package pro.sky.Homework17;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.Homework17.exceptions.EmployeeAlreadyAddedException;
import pro.sky.Homework17.exceptions.EmployeeNotFoundException;
import pro.sky.Homework17.exceptions.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping ("/employee")
public class EmployeeController {
    EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }
    @GetMapping
    public String welcomeUser() {
        return "Программа по управлению списком персонала. Команды: (/find ; /add ; /remove). Необходимые параметры: (name=... ; " +
                "surname=...). Также доступна команда (/printAll) параметры для нее не требуются.";
    }
    @GetMapping ("/find")
    public String findEmployee(@RequestParam ("name") String firstName, @RequestParam ("surname") String lastName) {
        try {
            return service.findEmployee(firstName, lastName).toString();
        } catch (EmployeeNotFoundException exp) {
            return "Сотрудник не найден.";
        }
    }
    @GetMapping ("/remove")
    public String removeEmployee(@RequestParam ("name") String firstName, @RequestParam ("surname") String lastName) {
        try {
            service.removeEmployee(firstName, lastName);
            return "Сотрудник: " + firstName + " " + lastName + " удален из списка сотрудников.";
        } catch (EmployeeNotFoundException exp) {
            return "Сотрудник не найден.";
        }
    }
    @GetMapping ("/add")
    public String addEmployee(@RequestParam ("name") String firstName, @RequestParam ("surname") String lastName) {
        try {
            service.addEmployee(firstName, lastName);
            return "Сотрудник: " + firstName + " " + lastName + " добавлен в список сотрудников.";
        } catch (EmployeeAlreadyAddedException exp) {
            return "Такой сотрудник уже есть в списке.";
        } catch (EmployeeStorageIsFullException exp) {
            return "Нет места в списке для добавления нового сотрудника.";
        }
    }
    @GetMapping ("/printAll")
    public List<Employee> printAllEmployees() {
        return new ArrayList<>(service.employeeList);
    }
}
