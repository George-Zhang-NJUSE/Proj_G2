package group2.grade15.njuse.bl.orderbl;

import group2.grade15.njuse.blservice.OrderListServ;
import group2.grade15.njuse.blservice.OrderServ;
import group2.grade15.njuse.utility.OrderState;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.OrderListVO;
import group2.grade15.njuse.vo.OrderVO;

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

    @Override
    public OrderVO createOrder(OrderVO vo) {
        return order.createPO(vo);
    }

    @Override
    public ResultMessage saveOrder(OrderVO vo) {
        return order.savePO(vo);
    }

    @Override
    public OrderListVO getAllOrderListByCustomerID(int id) {
        return orderList.getAllOrderListByCustomerID(id);
    }

    @Override
    public OrderListVO getExecutedOrderListByCustomerID(int id) {
        return orderList.getExecutedOrderList(id);
    }

    @Override
    public OrderListVO getUnexecutedOrderListByCustomerID(int id) {
        return orderList.getUnexecutedOrderList(id);
    }

    @Override
    public OrderListVO getRevokedOrderListByCustomerID(int id) {
        return orderList.getRevokedOrderList(id);
    }

    @Override
    public OrderListVO getAbnormalOrderListByCustomerID(int id) {
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
        return orderList.getAbnormalOrderListInHotel(id, hotelID);
    }

    @Override
    public OrderListVO getAllOrderListByHotelID(int hotelID) {
        return orderList.getAllOrderListByHotelID(hotelID);
    }

}
