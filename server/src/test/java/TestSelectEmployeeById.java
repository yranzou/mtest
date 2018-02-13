import com.mtest.dao.EmployeeDao;
import org.junit.Assert;
import org.junit.Test;

/**
 *  Created by yuri on 27.11.17.
 */
public class TestSelectEmployeeById {
    private EmployeeDao employeeDao = new EmployeeDao();

    @Test
    public void selectById() {
        Assert.assertEquals(employeeDao.get(1).getName(),"TINA");
    }

}
