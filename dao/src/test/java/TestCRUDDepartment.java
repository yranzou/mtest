import com.mtest.dao.DepartmentDao;
import com.mtest.dao.exceptions.DaoException;
import com.mtest.model.Department;
import com.mtest.model.Employee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.List;

/**
 * Created by yuri on 13.01.18.
 */
public class TestCRUDDepartment {
    interface Create { /* category marker */ }
    interface Read { /* category marker */ }
    interface Update { /* category marker */ }
    interface Destroy { /* category marker */ }

    private DepartmentDao departmentDao;
    private int testId;
    private Department department;
    private Department dep2;
    private List<Department> departments;

    @Before
    public void init()
    {
        departmentDao = new DepartmentDao();
        department = new Department();
        department.setName("SegaFans");
        department.setChiefId(1);
        dep2= new Department();

    }
    public TestCRUDDepartment() {
    }

    @Test
    @Category(TestCRUDDepartment.Create.class)
    public void createDepartment() throws DaoException {

        departmentDao.persist(department);
        departments = departmentDao.getAll();
//        for (Department dep:departments
//                ) {
//            System.out.println(dep.getName());
//            if (dep.getName().equals("SegaFans"))
//                dep2 = dep;
//
//        }
//
//        Assert.assertEquals("Expected SegaFans", "SegaFans", dep2.getName());

    }


    @Test
    @Category(TestCRUDDepartment.Read.class)
    public void selectByName() throws DaoException {

        Department dep3 = new Department();
        List<Department> departments;
        departments = departmentDao.getAll();
        for (Department dep:departments
                ) {
            System.out.println(dep.getName());
            if (dep.getName().equals("TEST"))
                dep3 = dep;

        }
        Assert.assertEquals("Expected TEST", "TEST", dep3.getName());
    }

    @Test
    @Category(Read.class)
    public void searchByName() {
        Department dep3 = new Department();
        List<Department> departments;
        departments = departmentDao.search("NAME", "na");
        for (Department dep:departments
                ) {
            System.out.println(dep.getName());
            if (dep.getName()!=null && dep.getName().equals("FUN"))
                dep3 = dep;
        }
        Assert.assertEquals("Expected FUN", "FUN", dep3.getName());
    }

    @Test
    @Category(TestCRUDDepartment.Update.class)
    public void updateByName() throws DaoException {
        Department dep4 = new Department();
        departments = departmentDao.getAll();
        for (Department dep:departments
                ) {
            System.out.println(dep.getName());
            if (dep.getName().equals("FUN")) {
                dep4 = dep;
                testId = dep4.getId();
            }
        }
        System.out.println("In updateByName: " + dep4);
        dep4.setName("MegaFans");
        dep4.setChiefId(3);
        departmentDao.update(dep4);
        department = departmentDao.get(testId);
        Assert.assertEquals("Expected MegaFans", "MegaFans", department.getName());
    }

    @Test
    @Category(TestCRUDDepartment.Destroy.class)
    public void delete() throws DaoException {
        departmentDao.delete(departmentDao.get(1));
        Assert.assertEquals("Expected Null", null, departmentDao.get(1));
    }

    @Test
    @Category(TestCRUDDepartment.Destroy.class)
    public void deleteById() throws DaoException {
        departmentDao.delete(9);
//        List<Employee> employees = departmentDao =.getAll();
//        for (Employee emp:employees
//                ) {
//            System.out.println(emp.getName());
//        }
        Assert.assertEquals("Expected Null", null, departmentDao.get(9));
    }
}
