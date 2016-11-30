package group2.grade15.njuse.bl.webmarketerbl;

import group2.grade15.njuse.bl.orderbl.OrderBL;
import group2.grade15.njuse.blservice.CreditModificationServ;
import group2.grade15.njuse.utility.OrderState;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.CreditVO;

/**
 * Created by Guo on 2016/11/30.
 */
public class RevokeProxy implements OrderBL, CreditModificationServ {

    @Override
    public ResultMessage modifyCredit(CreditVO Credit) {
        return null;
    }

    @Override
    public ResultMessage modifyState(int orderId, OrderState state) {
        return null;
    }
}
