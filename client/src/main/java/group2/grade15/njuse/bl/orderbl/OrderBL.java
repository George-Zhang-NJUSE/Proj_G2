package bl.orderbl;

import utility.OrderState;
import utility.ResultMessage;

/**
 * Created by George on 2016/11/6.
 */
public interface OrderBL {

    public ResultMessage modifyState(int orderId, OrderState state);
}
