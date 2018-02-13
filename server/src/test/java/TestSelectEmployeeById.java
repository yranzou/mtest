import com.mtest.dao.EmployeeDao;
import com.mtest.server.EmployeeService;
import org.junit.Assert;
import org.junit.Test;

/**
 *  Created by yuri on 27.11.17.
 */
public class TestSelectEmployeeById {
    private EmployeeService employeeService = new EmployeeService();

    @Test
    public void selectById() {
        Assert.assertEquals(employeeService.get(1).getName(),"TINA");
    }

}
