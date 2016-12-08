package group2.grade15.njuse.bl.orderbl;

import group2.grade15.njuse.po.OrderPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.OrderState;
import group2.grade15.njuse.vo.CustomerVO;
import group2.grade15.njuse.vo.OrderListVO;
import group2.grade15.njuse.vo.OrderVO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by George on 2016/11/6.
 */
public class OrderList implements OrderListBL {


    public OrderListVO getAllOrderListByCustomerID(int customerID) {
        ArrayList<OrderPO> orderPOList = new ArrayList();
        try {
            orderPOList = RemoteHelper.getInstance().getOrderDataService().getListByCustomer(customerID);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        ArrayList<OrderVO> orderList = new ArrayList();
        for(OrderPO orderPO : orderPOList){
            orderList.add(new OrderVO(orderPO));
        }

        if(orderPOList != null){
            return new OrderListVO(orderList);
        } else {
            return null;
        }
    }

    public OrderListVO getExecutedOrderList(int customerID) {
        return filterOrderByState(customerID, OrderState.executed);
    }

    public OrderListVO getUnexecutedOrderList(int customerID) {
        return filterOrderByState(customerID, OrderState.unexecuted);
    }

    public OrderListVO getRevokedOrderList(int customerID) {
        return filterOrderByState(customerID, OrderState.revoked);
    }

    public OrderListVO getAbnormalOrderList(int customerID) {
        return filterOrderByState(customerID, OrderState.abnormal);
    }

    public OrderListVO getExecutedOrderListInHotel(int customerID, int hotelID) {
        OrderListVO orderListVO = getExecutedOrderList(customerID);
        return filterOrderByHotelID(hotelID, orderListVO);
    }

    public OrderListVO getRevokedOrderListInHotel(int customerID, int hotelID) {
        OrderListVO orderListVO = getRevokedOrderList(customerID);
        return filterOrderByHotelID(hotelID, orderListVO);
    }

    public OrderListVO getAbnormalOrderListInHotel(int customerID, int hotelID) {
        OrderListVO orderListVO = getAbnormalOrderList(customerID);
        return filterOrderByHotelID(hotelID, orderListVO);
    }

    public OrderListVO getAllOrderListByHotelID(int hotelID){
        ArrayList<OrderPO> orderPOList = new ArrayList();
        try {
            orderPOList = RemoteHelper.getInstance().getOrderDataService().getListByHotel(hotelID);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        ArrayList<OrderVO> orderList = new ArrayList();
        for(OrderPO orderPO : orderPOList){
            orderList.add(new OrderVO(orderPO));
        }

        if(orderPOList != null){
            return new OrderListVO(orderList);
        } else {
            return null;
        }
    }

    /**
     * 根据所需状态对客户的订单进行筛选
     */
    private OrderListVO filterOrderByState(int customerID, OrderState state){
        OrderListVO orderListVO = getAllOrderListByCustomerID(customerID);
        ArrayList<OrderVO> orderList = orderListVO.getOrderList();
        ArrayList<OrderVO> filterOrderList = new ArrayList();

        for(OrderVO orderVO : orderList){
            if(orderVO.getState() == state){
                filterOrderList.add(orderVO);
            }
        }

        return new OrderListVO(filterOrderList);
    }

    /**
     * 根据所需状态对客户在特定酒店的订单进行筛选
     */
    private OrderListVO filterOrderByHotelID(int hotelID, OrderListVO orderListVO){
        ArrayList<OrderVO> orderList = orderListVO.getOrderList();
        ArrayList<OrderVO> filterOrderList = new ArrayList();

        for(OrderVO orderVO : orderList){
            if(orderVO.getHotelID() == hotelID){
                filterOrderList.add(orderVO);
            }
        }

        return new OrderListVO(filterOrderList);
    }
}
