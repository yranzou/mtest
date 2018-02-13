import com.mtest.dao.EmployeeDao;
import com.mtest.model.Employee;
import com.mtest.server.EmployeeService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.List;

/**
 *  Created by yuri on 04.01.18.
 */
public class TestCRUDEmployee {
    interface Create { /* category marker */ }
    interface Read { /* category marker */ }
    interface Update { /* category marker */ }
    interface Destroy { /* category marker */ }

    private EmployeeService employeeService;
    private int testId;
    private Employee employee;
    private Employee emp2;

    private List<Employee> employees;
    @Before
    public void init()
    {
        employeeService = new EmployeeService();
        employee = new Employee();
        employee.setName("Motoko");
        employee.setSurname("Kusanagi");
        employee.setPhone("9163337733");
        emp2= new Employee();

    }
    public TestCRUDEmployee() {
    }

    @Test
    @Category(Create.class)
    public void createEmployee() {


        employeeService.create(employee);

        employees = employeeDao.getAll();
        for (Employee emp:employees
                ) {
            System.out.println(emp.getSurname());
            if (emp.getSurname().equals("Kusanagi"))
                emp2 = emp;

        }

        Assert.assertEquals("Expected Motoko", "Motoko", emp2.getName());
        Assert.assertEquals("Expected Kusanagi", "Kusanagi", emp2.getSurname());

    }


    @Test
    @Category(Read.class)
    public void selectBySurname() {


        Employee emp3 = new Employee();
        List<Employee> employees;
        employees = employeeDao.getAll();
        for (Employee emp:employees
             ) {
            System.out.println(emp.getSurname());
            if (emp.getSurname().equals("ThOrENS"))
                emp3 = emp;

        }
        Assert.assertEquals("Expected NINA", "NINA", emp3.getName());
        Assert.assertEquals("Expected ThOrENS", "ThOrENS", emp3.getSurname());
    }

    @Test
    @Category(Update.class)
    public void updateBySurname() {
        Employee emp4 = new Employee();

        employees = employeeDao.getAll();
        for (Employee emp:employees
                ) {
            System.out.println(emp.getSurname());
            if (emp.getSurname().equals("Tsundere")) {
                emp4 = emp;
                testId = emp4.getId();
            }
        }
        System.out.println("before update: " + emp4);
        emp4.setName("Mega");
        emp4.setSurname("Man");
        emp4.setPhone("7000000000");
        System.out.println("class changed: " + emp4);
        employeeDao.update(emp4);
        employee = employeeDao.get(testId);
        Assert.assertEquals("Expected Mega", "Mega", employee.getName());
        Assert.assertEquals("Expected Man", "Man", employee.getSurname());
    }

    @Test
    @Category(Destroy.class)
    public void delete() {
        int id_c = 1;
        employeeDao.delete(employeeDao.get(id_c));
        Assert.assertEquals("Expected Null", null, employeeDao.get(id_c));
    }


    @Test
    @Category(Destroy.class)
    public void deleteById() {
        Assert.assertEquals("Expected Employee.class", Employee.class, employeeDao.get(40).getClass());
        employeeDao.delete(40);
        Assert.assertEquals("Expected Null", null, employeeDao.get(40));
    }


}
