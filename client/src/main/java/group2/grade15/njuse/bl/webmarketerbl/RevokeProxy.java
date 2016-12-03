package group2.grade15.njuse.bl.webmarketerbl;

import group2.grade15.njuse.bl.orderbl.GetOrderBL;
import group2.grade15.njuse.bl.orderbl.OrderController;
import group2.grade15.njuse.blservice.CreditModificationServ;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.OrderState;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.CreditVO;
import group2.grade15.njuse.vo.OrderVO;

import java.rmi.RemoteException;

/**
 * Created by Guo on 2016/11/30.
 */
public class RevokeProxy implements CreditModificationServ {

    @Override
    public ResultMessage modifyCredit(CreditVO credit) {
        ResultMessage result = ResultMessage.FAILED;

        try {
            RemoteHelper.getInstance().getCreditDataService().add(credit.toPO());
        } catch (RemoteException e) {
            e.printStackTrace();
            result = ResultMessage.CONNECTION_EXCEPTION;
        }

        return result;
    }

    public ResultMessage modifyState(int orderId, OrderState state) {
        return null;
    }
}
