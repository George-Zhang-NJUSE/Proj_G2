package group2.grade15.njuse.bl.orderbl;

import group2.grade15.njuse.blservice.OrderListServ;
import group2.grade15.njuse.blservice.OrderServ;
import group2.grade15.njuse.po.OrderPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.OrderState;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.OrderListVO;
import group2.grade15.njuse.vo.OrderVO;

import java.rmi.RemoteException;
import java.util.Date;

/**
 * Created by George on 2016/11/13.
 */
public class OrderController implements ModifyOrderStateBL, OrderServ, OrderListServ {

    private Order order;
    private OrderList orderList;

    public OrderController(){
        this.order = new Order();
        this.orderList = new OrderList();
    }

    public OrderVO getOrder(int orderId) {
        return order.getInfo(orderId);
    }

    public ResultMessage modifyState(int orderId, OrderState state) {
        return order.modifyState(orderId, state);
    }

    public ResultMessage addOrder(OrderVO vo) {
        return order.createPO(vo.toPO());
    }

    @Override
    public OrderListVO getAllOrderList(int id) {
        return orderList.getAllOrderList(id);
    }

    @Override
    public OrderListVO getExecutedOrderList(int id) {
        return orderList.getExecutedOrderList(id);
    }

    @Override
    public OrderListVO getUnexecutedOrderList(int id) {
        return orderList.getUnexecutedOrderList(id);
    }

    @Override
    public OrderListVO getNewOrderList(Date date) {
        return orderList.getNewOrderList(date);
    }

    @Override
    public OrderListVO getRevokedOrderList(int id) {
        return orderList.getRevokedOrderList(id);
    }

    @Override
    public OrderListVO getAbnormalOrderList(int id) {
        return orderList.getAbnormalOrderList(id);
    }

    @Override
    public OrderListVO getExecutedOrderListInHotel(int id, int hotelID) {
        return orderList.getExecutedOrderListInHotel(id, hotelID);
    }

    @Override
    public OrderListVO getRevokedOrderListInHotel(int id, int hotelID) {
        return orderList.getRevokedOrderListInHotel(id, hotelID);
    }

    @Override
    public OrderListVO getAbnormalOrderList(int id, int hotelID) {
        return orderList.getAbnormalOrderList(id, hotelID);
    }

}
