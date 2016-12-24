package group2.grade15.njuse.bl.orderbl;

import group2.grade15.njuse.cache.CacheManager;
import group2.grade15.njuse.po.OrderPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.OrderState;
import group2.grade15.njuse.vo.CustomerVO;
import group2.grade15.njuse.vo.OrderListVO;
import group2.grade15.njuse.vo.OrderVO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * Created by George on 2016/11/6.
 */
public class OrderList implements OrderListBL {

    public OrderListVO getAllOrderListByCustomerID(int customerID) {
        ArrayList<OrderPO> orderPOList = null;

        try {
            orderPOList = RemoteHelper.getInstance().getOrderDataService().getListByCustomer(customerID);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        if (orderPOList != null) {
            ArrayList<OrderVO> orderList = orderPOList.stream()
                                                      .map(OrderVO::new)
                                                      .collect(Collectors.toCollection(ArrayList::new));
            return new OrderListVO(orderList);
        } else {
            return null;
        }
    }

    public OrderListVO getExecutedOrderList(int customerID) {
        OrderListVO excutedOrderListVO = filterOrderByState(customerID, OrderState.executed);
        OrderListVO completedOrderListVO = filterOrderByState(customerID, OrderState.complete);
        ArrayList<OrderVO> orderVOs = new ArrayList<>();
        orderVOs.addAll(excutedOrderListVO.getOrderList());
        orderVOs.addAll(completedOrderListVO.getOrderList());
        return new OrderListVO(orderVOs);
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

    public OrderListVO getUnexecutedOrderListInHotel(int customerID, int hotelID) {
        OrderListVO orderListVO = getUnexecutedOrderList(customerID);
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

    public OrderListVO getAllOrderListByHotelID(int hotelID) {
        ArrayList<OrderPO> orderPOList = null;
        try {
            orderPOList = RemoteHelper.getInstance().getOrderDataService().getListByHotel(hotelID);
        } catch (RemoteException e) {
            e.printStackTrace();
        }


        if (orderPOList != null) {
            ArrayList<OrderVO> orderList = orderPOList.stream()
                                                      .map(OrderVO::new)
                                                      .collect(Collectors.toCollection(ArrayList::new));
            return new OrderListVO(orderList);
        } else {
            return null;
        }
    }

    /**
     * 根据所需状态对客户的订单进行筛选
     */
    private OrderListVO filterOrderByState(int customerID, OrderState state) {
        OrderListVO orderListVO = getAllOrderListByCustomerID(customerID);
        ArrayList<OrderVO> orderList = orderListVO.getOrderList();

        if(orderListVO == null){
            return null;
        }

        ArrayList<OrderVO> filterOrderList = orderList.stream()
                                                      .filter(orderVO -> orderVO.getState() == state)
                                                      .collect(Collectors.toCollection(ArrayList::new));

        if(filterOrderList.size() != 0){
            return new OrderListVO(filterOrderList);
        } else {
            return null;
        }
    }

    /**
     * 根据所需状态对客户在特定酒店的订单进行筛选
     */
    private OrderListVO filterOrderByHotelID(int hotelID, OrderListVO orderListVO) {

        if(orderListVO == null){
            return null;
        }

        ArrayList<OrderVO> orderList = orderListVO.getOrderList();
        ArrayList<OrderVO> filterOrderList = orderList.stream()
                                                      .filter(orderVO -> orderVO.getHotelID() == hotelID)
                                                      .collect(Collectors.toCollection(ArrayList::new));

        if (filterOrderList.size() != 0) {
            return new OrderListVO(filterOrderList);
        } else {
            return null;
        }
    }

    public OrderListVO getAbnormalOrderList() {
        ArrayList<OrderPO> orderPOList = null;
        try {
            orderPOList = RemoteHelper.getInstance().getOrderDataService().getAbnormalList();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        if (orderPOList != null) {
            ArrayList<OrderVO> orderList = orderPOList.stream()
                                                      .map(OrderVO::new)
                                                      .collect(Collectors.toCollection(ArrayList::new));
            return new OrderListVO(orderList);
        } else {
            return null;
        }
    }
}