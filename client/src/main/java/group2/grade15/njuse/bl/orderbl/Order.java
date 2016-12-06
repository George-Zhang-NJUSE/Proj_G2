package group2.grade15.njuse.bl.orderbl;

import group2.grade15.njuse.po.OrderPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.OrderState;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.OrderVO;

import java.rmi.RemoteException;

/**
 * Created by George on 2016/11/6.
 */
public class Order {

    public OrderVO getInfo(int orderID) {
        OrderPO po = null;

        try {
            po = RemoteHelper.getInstance().getOrderDataService().getOrder(orderID);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        if (po != null) {
            return new OrderVO(po);
        } else {
            return null;
        }
    }

    public ResultMessage modifyState(int orderID, OrderState state) {
        try {
            return RemoteHelper.getInstance().getOrderDataService().modifyOrder(orderID, state);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.CONNECTION_EXCEPTION;
        }
    }

    public ResultMessage savePO(OrderVO order) {
        try {
            return RemoteHelper.getInstance().getOrderDataService().addOrder(order.toPO());
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.CONNECTION_EXCEPTION;
        }
    }

    public OrderVO createPO(OrderVO orderVO){
        return null;
    }

}
