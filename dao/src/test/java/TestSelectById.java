import com.mtest.lesson16.EmployeeDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

/**
 * Created by yuri on 27.11.17.
 */
public class TestSelectById {
    private EmployeeDao employeeDao = new EmployeeDao();

    @Test
    public void selectById() {
        Assert.assertEquals(employeeDao.get(1).getName(),"TINA");
    }
}
