import com.mtest.hmodel.Employee;
import com.mtest.jpadao.JpaEmployeeDao;
import org.junit.Assert;

import java.util.List;

/**
 * Created by yuri on 04.12.17.
 */
public class Test {
    private JpaEmployeeDao employeeDao = new JpaEmployeeDao();

    @org.junit.Test
    public void selectById() {
        Assert.assertEquals(employeeDao.findById(1).getName(),"TINA");
    }

//    @org.junit.Test
//    public void getAllEmployees() {
//        List<Employee> employees = employeeDao.findAll();
//        StringBuilder sb = new StringBuilder("<html><head><title>Employees</title></head><body><table>");
//        for (Employee employee:employees
//                ) {
//            sb.append("<tr> <td>");
//            sb.append(employee.getId());
//            sb.append("</td><td>");
//            sb.append(employee.getName());
//            sb.append("</td><td>");
//            sb.append(employee.getSurname());
//            sb.append("</td></tr>");
//        }
//        sb.append("</table></body></html>");
//        System.out.println(sb.toString());
//        Assert.assertEquals(employeeDao.findById(1).getPhone(),"phoneH");
//
//    }
}
