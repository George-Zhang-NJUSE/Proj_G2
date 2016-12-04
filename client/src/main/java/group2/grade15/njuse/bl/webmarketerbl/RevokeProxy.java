package group2.grade15.njuse.bl.webmarketerbl;

import group2.grade15.njuse.bl.orderbl.ModifyOrderStateBL;
import group2.grade15.njuse.bl.orderbl.OrderController;
import group2.grade15.njuse.utility.OrderState;
import group2.grade15.njuse.utility.ResultMessage;


/**
 * Created by Guo on 2016/11/30.
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
