package testcase;

import bl.customerbl.CustomerBL;
import bl.customerbl.mock.MockCustomerBL;
import org.junit.Test;
import utility.ResultMessage;
import vo.CustomerVO;

import static org.junit.Assert.assertEquals;

/**
 * Created by George on 2016/11/6.
 */
public class CustomerBLTest {

    @Test
    public void testGetInfo() {
        CustomerBL customerBL = new MockCustomerBL();
        CustomerVO vo = new CustomerVO(123456, "jack", "password", "13256984562", "533666196806231368", 200);
        assertEquals(vo,customerBL.getInfo(123456));
    }

    @Test
    public void testModifyInfo() {
        CustomerBL customerBL = new MockCustomerBL();
        CustomerVO vo = new CustomerVO(123456, "jack", "password", "13256984666", "533666196806231368", 200);
        assertEquals(ResultMessage.SUCCESS,customerBL.modifyInfo(vo));
    }

}
