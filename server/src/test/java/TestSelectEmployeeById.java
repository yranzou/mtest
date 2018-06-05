import com.mtest.server.common.EmployeeService;
import com.mtest.server.exception.ServerException;
import org.junit.Assert;
import org.junit.Test;

/**
 *  Created by yuri on 27.11.17.
 */
public class TestSelectEmployeeById {
    private EmployeeService employeeService = new EmployeeService();

    @Test
    public void selectById() throws ServerException {
        Assert.assertEquals(employeeService.get(1).getName(),"TINA");
    }

}
