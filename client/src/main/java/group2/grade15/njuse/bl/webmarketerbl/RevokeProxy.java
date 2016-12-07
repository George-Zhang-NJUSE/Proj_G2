package group2.grade15.njuse.bl.webmarketerbl;

import group2.grade15.njuse.bl.orderbl.ModifyOrderStateBL;
import group2.grade15.njuse.bl.orderbl.OrderController;
import group2.grade15.njuse.utility.OrderState;
import group2.grade15.njuse.utility.ResultMessage;


/**
 * 取消异常订单职责的代理者
 * 真正的任务通过层内接口ModifyOrderStateBL代理给OrderController完成
 */
public class RevokeProxy {

    private ModifyOrderStateBL modifyOrderStateBL;

    public RevokeProxy() {
        modifyOrderStateBL = new OrderController();
    }

    public ResultMessage modifyState(int orderId, OrderState state) {
        return modifyOrderStateBL.modifyState(orderId, state);
    }
}
