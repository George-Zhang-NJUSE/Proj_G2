package testcase;

import bl.orderbl.OrderBL;
import bl.orderbl.mock.MockOrderBL;
import org.junit.Test;
import utility.OrderState;
import utility.ResultMessage;

/**
 * Created by George on 2016/11/6.
 */
public class OrderBLTest {

    @Test
    public void testModifyState() {
        OrderBL orderBL = new MockOrderBL();
        assertEquals(ResultMessage.SUCCESS, orderBL.modifyState(123, OrderState.executed));
    }

}
