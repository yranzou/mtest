import com.mtest.dao.EmployeeDao;
import com.mtest.dao.exceptions.DaoException;
import com.mtest.dao.utils.ConverterDate;
import com.mtest.model.Employee;
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
 * Created by yuri on 04.01.18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:dao-context.xml"})
//@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:testl16.sql")
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TestCRUDEmployee {
    interface Create { /* category marker */
    }

    interface Read { /* category marker */
    }

    interface Update { /* category marker */
    }

    interface Destroy { /* category marker */
    }

    @Autowired
    private EmployeeDao employeeDao;


    private int testId;
    private Employee employee;
    private Employee emp2;
    private String testName;
    private String testSurname;
    private String testPhone;
    private List<Employee> employees;

    @Before
    public void init() {
        testName = "testName";
        testSurname = "testSurname";
        testPhone = "9163337733";
        employee = new Employee();
        employee.setName(testName);
        employee.setSurname(testSurname);
//        employee.setPhone(testPhone);
        try {
            employee.setBirthday(ConverterDate.toUtilDate("2000-01-01"));
        } catch (DaoException e) {
            e.printStackTrace();
        }
        emp2 = new Employee();
        System.out.println("BEFORE TEST");
    }

    public TestCRUDEmployee() {
    }

    @Test
    @Transactional("txManagerDatasource")
    @Category(Create.class)
    public void createEmployee() throws DaoException {
        employeeDao.persist(employee);
        employees = employeeDao.getAll();
        for (Employee emp : employees
                ) {
            System.out.println(emp.getSurname());
            if (emp.getSurname().equals(testSurname))
                emp2 = emp;
        }
        Assert.assertEquals("Expected " + testName, testName, emp2.getName());
        Assert.assertEquals("Expected " + testSurname, testSurname, emp2.getSurname());
    }


//    @Test
//    @Category(Read.class)
//    public void selectBySurname() throws DaoException {
//        Employee emp3 = new Employee();
//        List<Employee> employees;
//        employees = employeeDao.getAll();
//        for (Employee emp : employees
//                ) {
//            System.out.println(emp.getSurname());
//            if (emp.getSurname().equals("ThOrENS"))
//                emp3 = emp;
//
//        }
//        Assert.assertEquals("Expected NINA", "NINA", emp3.getName());
//        Assert.assertEquals("Expected ThOrENS", "ThOrENS", emp3.getSurname());
//    }

    @Test
    @Category(Read.class)
    public void searchByName() throws DaoException {
        Employee emp3 = new Employee();
        List<Employee> employees;
        employees = employeeDao.search("NAME", "na");
        for (Employee emp : employees
                ) {
            System.out.println(emp.getSurname());
            if (emp.getSurname().equals("ThOrENS"))
                emp3 = emp;

        }
        Assert.assertEquals("Expected NINA", "NINA", emp3.getName());
        Assert.assertEquals("Expected ThOrENS", "ThOrENS", emp3.getSurname());
    }

    @Test
    @Transactional("txManagerDatasource")
    @Category(Update.class)
    public void updateBySurname() throws DaoException {
        Employee emp2 = new Employee();
        employees = employeeDao.getAll();
        for (Employee emp : employees
                ) {
            System.out.println(emp.getSurname());
            if (emp.getSurname().equals("Tsundere")) {
                emp2 = emp;
                testId = emp2.getId();
            }
        }
        System.out.println("before update: " + emp2);
        emp2.setName("Mega");
        emp2.setSurname("Man");
//        emp2.setPhone("7000000000");
        System.out.println("class  changed: " + emp2);
        employeeDao.update(emp2);
        employee = employeeDao.get(testId);
        Assert.assertEquals("Expected Mega", "Mega", employee.getName());
        Assert.assertEquals("Expected Man", "Man", employee.getSurname());
    }

    @Test(expected = DaoException.class)
    @Transactional("txManagerDatasource")
    @Category(Destroy.class)
    public void delete() throws DaoException {
        int testId = 41;
        Assert.assertEquals("Expected Employee.class", Employee.class, employeeDao.get(testId).getClass());
        employeeDao.delete(testId);
        Assert.assertEquals("Expected null", null, employeeDao.get(testId));
    }


}
