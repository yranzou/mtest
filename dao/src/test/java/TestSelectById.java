import com.mtest.dao.EmployeeDao;
import com.mtest.model.Employee;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by yuri on 27.11.17.
 */
public class TestSelectById {
    private EmployeeDao employeeDao = new EmployeeDao();

    @Test
    public void selectById() {
        Assert.assertEquals(employeeDao.get(1).getName(),"TINA");
    }

    @Test
    public void getAllEmployees() {
        List<Employee> employees = employeeDao.getAll();
        StringBuilder sb = new StringBuilder("<html><head><title>Employees</title></head><body><table>");
        for (Employee emmployee:employees
             ) {
            sb.append("<tr><td>");
            sb.append(emmployee.getId());
            sb.append("</td><td>");
            sb.append(emmployee.getName());
            sb.append("</td><td>");
            sb.append(emmployee.getSurname());
            sb.append("</td></tr>");
        }
        sb.append("</table></body></html>");
        System.out.println(sb.toString());
        Assert.assertEquals(employeeDao.get(1).getPhone(),"phoneH");

    }
}
