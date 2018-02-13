import com.mtest.dao.EmployeeDao;
import com.mtest.model.Employee;
import com.mtest.server.EmployeeService;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by yuri on 19.12.17.
 */
public class TestGetAllEmployees {
    private EmployeeService employeeService = new EmployeeService();

    @Test
    public void getAllEmployees() {
        List<Employee> employees = employeeService.getAll();
        StringBuilder sb = new StringBuilder("<html><head><title>Employees</title></head><body><table>");
        for (Employee employee:employees
                ) {
//            sb.append("<tr><td>");
//            sb.append(employee.getId());
//            sb.append("</td><td>");
//            sb.append(employee.getName());
//            sb.append("</td><td>");
//            sb.append(employee.getSurname());
//            sb.append("</td></tr>");
            System.out.println(employee);
        }
        sb.append("</table></body></html>");
//        System.out.println(sb.toString());
        Assert.assertEquals(employeeService.get(1).getPhone(),"phoneH");

    }
}
