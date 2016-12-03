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

    private OrderPO po;

    public Order(OrderPO po) {
        this.po = po;
    }

    public OrderVO getInfo(){
        OrderPO po = null;

        try {
            po = RemoteHelper.getInstance().getOrderDataService().getOrder(po.getOrderID());
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        if(po != null) {
            return new OrderVO(po);
        } else {
            return null;
        }
    }

    public ResultMessage modifyState(OrderState state){
        return ResultMessage.SUCCESS;
    }

    public OrderPO createPO(){
        return null;
    }
}
