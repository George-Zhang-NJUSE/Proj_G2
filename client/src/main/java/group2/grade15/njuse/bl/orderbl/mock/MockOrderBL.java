package bl.orderbl.mock;

import bl.orderbl.OrderBL;
import utility.OrderState;
import utility.ResultMessage;

/**
 * Created by George on 2016/11/6.
 */
public class MockOrderBL implements OrderBL{

    @Override
    public ResultMessage modifyState(int orderId, OrderState state) {
        return ResultMessage.SUCCESS;
    }

}
