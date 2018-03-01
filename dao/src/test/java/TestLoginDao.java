import com.mtest.dao.LoginDao;
import org.junit.Assert;
import org.junit.Test;

public class TestLoginDao {

        @Test
        public void testLogin() {


            Assert.assertEquals(LoginDao.validate("admin","1234"),true);
            Assert.assertEquals(LoginDao.validate("admin","12d34"),false);
        }
    }
