package group2.grade15.njuse.bl.orderbl;

import group2.grade15.njuse.utility.OrderState;
import group2.grade15.njuse.utility.ResultMessage;

/**
 * Created by George on 2016/11/6.
 */
public interface OrderBL {

    public ResultMessage modifyState(int orderId, OrderState state);
}
