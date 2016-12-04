package testcase;

import bl.orderbl.OrderListBL;
import bl.orderbl.mock.MockOrderListBL;
import org.junit.Test;
import utility.IDType;
import vo.OrderListVO;

/**
 * Created by George on 2016/11/6.
 */
public class OrderListBLTest {

    @Test
    public void testGetAbnormalOrderList() {
        OrderListBL orderListBL = new MockOrderListBL();
        assertEquals(new OrderListVO(null), orderListBL.getAbnormalOrderList(123456, IDType.customer));
    }
}
