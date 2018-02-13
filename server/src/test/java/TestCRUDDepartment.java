import com.mtest.dao.DepartmentDao;
import com.mtest.model.Department;
import com.mtest.server.DepartmentService;
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

//    private DepartmentDao departmentDao;
    private DepartmentService departmentService;
    private int testId;
    private Department department;
    private Department dep2;
    private List<Department> departments;

    @Before
    public void init()
    {
        departmentService = new DepartmentService();
        department = new Department();
        department.setName("SegaFans");
        department.setChiefId(1);
        dep2= new Department();

    }
    public TestCRUDDepartment() {
    }

    @Test
    @Category(Create.class)
    public void createDepartment() {

        departmentService.create(department);
        departments = departmentService.getAll();
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
    @Category(Read.class)
    public void selectByName() {

        Department dep3 = new Department();
        List<Department> departments;
        departments = departmentService.getAll();
        for (Department dep:departments
                ) {
            System.out.println(dep.getName());
            if (dep.getName().equals("TEST"))
                dep3 = dep;

        }
        Assert.assertEquals("Expected TEST", "TEST", dep3.getName());
    }

    @Test
    @Category(Update.class)
    public void updateByName() {
        Department dep4 = new Department();
        departments = departmentService.getAll();
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
        departmentService.update(dep4);
        department = departmentService.get(testId);
        Assert.assertEquals("Expected MegaFans", "MegaFans", department.getName());
    }

    @Test
    @Category(Destroy.class)
    public void delete() {
        departmentService.delete(departmentService.get(1));
        Assert.assertEquals("Expected Null", null, departmentService.get(1));
    }

    @Test
    @Category(Destroy.class)
    public void deleteById() {
        departmentService.delete(9);
//        List<Employee> employees = departmentDao =.getAll();
//        for (Employee emp:employees
//                ) {
//            System.out.println(emp.getName());
//        }
        Assert.assertEquals("Expected Null", null, departmentService.get(9));
    }
}
