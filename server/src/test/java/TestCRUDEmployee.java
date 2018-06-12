import com.mtest.dao.exceptions.DaoException;
import com.mtest.dao.utils.ConverterDate;
import com.mtest.model.Employee;
import com.mtest.server.common.EmployeeService;
import com.mtest.server.exception.ServerException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *  Created by yuri on 04.01.18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class TestCRUDEmployee {
    interface Create { /* category marker */ }
    interface Read { /* category marker */ }
    interface Update { /* category marker */ }
    interface Destroy { /* category marker */ }

    @Autowired
    private EmployeeService employeeService;
    private int testId;
    private Employee employee;
    private Employee emp2;

    private List<Employee> employees;
    @Before
    public void init()
    {
//        employeeService = new EmployeeService();
        employee = new Employee();
        employee.setName("Motoko");
        employee.setSurname("Kusanagi");
        employee.setPhone("9163337733");
        try {
            employee.setBirthday(ConverterDate.toUtilDate("2000-01-01"));
        } catch (DaoException e) {
            e.printStackTrace();
        }
        emp2= new Employee();

    }
    public TestCRUDEmployee() {
    }

    @Test
    @Transactional("txManagerDatasource")
    @Category(Create.class)
    public void createEmployee() throws ServerException {
        employeeService.create(employee);
        employees = employeeService.getAll();
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
    public void selectBySurname() throws ServerException {


        Employee emp3 = new Employee();
        List<Employee> employees;
        employees = employeeService.getAll();
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
    @Category(Read.class)
    public void search() throws ServerException {


        Employee emp3 = new Employee();
        List<Employee> employees;
        employees = employeeService.search("NAME","%ina%");
//        employees = new EmployeeService().search("NAME","%ina%");
        for (Employee emp:employees
                ) {
            System.out.println(emp.getSurname() + " --- " + emp.getName());
            if (emp.getSurname().equals("ThOrENS"))
                emp3 = emp;

        }
//        Assert.assertEquals("Expected NINA", "NINA", emp3.getName());
//        Assert.assertEquals("Expected ThOrENS", "ThOrENS", emp3.getSurname());
    }

    @Test
    @Transactional("txManagerDatasource")
    @Category(Update.class)
    public void updateBySurname() throws ServerException {
        Employee emp4 = new Employee();
        employees = employeeService.getAll();
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
        employeeService.update(emp4);
        employee = employeeService.get(testId);
        Assert.assertEquals("Expected Mega", "Mega", employee.getName());
        Assert.assertEquals("Expected Man", "Man", employee.getSurname());
    }

    @Test(expected = ServerException.class)
    @Transactional("txManagerDatasource")
    @Category(Destroy.class)
    public void delete() throws ServerException {
        int id_c = 50;
        Employee employee = employeeService.get(id_c);
        employeeService.delete(employee);
        Assert.assertEquals("Expected Null", null, employeeService.get(id_c));
    }

}
