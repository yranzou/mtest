import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by yuri on 04.01.18.
 */

@RunWith(Categories.class)
@Categories.IncludeCategory({
        TestCRUDEmployee.Create.class,
        TestCRUDEmployee.Read.class,
        TestCRUDEmployee.Update.class,
        TestCRUDEmployee.Destroy.class,
//        TestCRUDDepartment.Create.class,
//        TestCRUDDepartment.Read.class,
//        TestCRUDDepartment.Update.class,
//        TestCRUDDepartment.Destroy.class,

})
@Suite.SuiteClasses({
        TestCRUDEmployee.class,
        TestCRUDDepartment.class})
public class RunTests {
    @Before
    public void init() {
        System.out.println("Before tests");
    }
}
