package group2.grade15.njuse.bl.webmarketerbl;

import group2.grade15.njuse.bl.orderbl.Order;
import group2.grade15.njuse.bl.orderbl.OrderBL;
import group2.grade15.njuse.utility.OrderState;
import group2.grade15.njuse.utility.ResultMessage;


/**
 * 取消异常订单职责的代理者
 * 真正的任务通过层内接口ModifyOrderStateBL代理给OrderController完成
 */
public class RevokeProxy {

    private OrderBL orderBL;

    public RevokeProxy() {
        orderBL = new Order();
    }

    public ResultMessage modifyState(int orderId, OrderState state) {
        return orderBL.modifyState(orderId, state);
    }
}
