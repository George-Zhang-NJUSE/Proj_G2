package group2.grade15.njuse.bl.orderbl;

import group2.grade15.njuse.blservice.OrderServ;
import group2.grade15.njuse.po.OrderPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.OrderState;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.OrderVO;

import java.rmi.RemoteException;

/**
 * Created by George on 2016/11/13.
 */
public class OrderController implements ModifyOrderStateBL, OrderServ{

    public OrderVO getOrder(int orderId) {
        Order order = null;
        OrderPO orderPO = null;

        try {
            orderPO = RemoteHelper.getInstance().getOrderDataService().getOrder(orderId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        if(orderPO != null){
            order = new Order(orderPO);
        }

        if(order != null){
            return order.getInfo();
        } else {
            return null;
        }

    }

    public ResultMessage modifyState(int orderId, OrderState state) {
        return null;
    }

    public ResultMessage addOrder(OrderVO vo) {
        return null;
    }
}
