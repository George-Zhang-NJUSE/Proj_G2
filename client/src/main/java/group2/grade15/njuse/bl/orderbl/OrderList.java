package group2.grade15.njuse.bl.orderbl;

import group2.grade15.njuse.po.OrderPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.OrderState;
import group2.grade15.njuse.vo.OrderListVO;
import group2.grade15.njuse.vo.OrderVO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by George on 2016/11/6.
 */
public class OrderList implements OrderListBL {

    public OrderListVO getAllOrderList(int customerID) {
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
        OrderListVO orderListVO = getAllOrderList(customerID);
        ArrayList<OrderVO> orderList = orderListVO.getOrderList();
        ArrayList<OrderVO> executedOrderList = new ArrayList();

        for(OrderVO orderVO : orderList){
            if(orderVO.getState() == OrderState.executed){
                executedOrderList.add(orderVO);
            }
        }

        return new OrderListVO(executedOrderList);
    }

    public OrderListVO getUnexecutedOrderList(int customerID) {
        OrderListVO orderListVO = getAllOrderList(customerID);
        ArrayList<OrderVO> orderList = orderListVO.getOrderList();
        ArrayList<OrderVO> unexecutedOrderList = new ArrayList();

        for(OrderVO orderVO : orderList){
            if(orderVO.getState() == OrderState.unexecuted){
                unexecutedOrderList.add(orderVO);
            }
        }

        return new OrderListVO(unexecutedOrderList);
    }

    public OrderListVO getNewOrderList(Date date) {
        return null;
    }

    public OrderListVO getRevokedOrderList(int customerID) {
        OrderListVO orderListVO = getAllOrderList(customerID);
        ArrayList<OrderVO> orderList = orderListVO.getOrderList();
        ArrayList<OrderVO> revokedOrderList = new ArrayList();

        for(OrderVO orderVO : orderList){
            if(orderVO.getState() == OrderState.revoked){
                revokedOrderList.add(orderVO);
            }
        }

        return new OrderListVO(revokedOrderList);
    }

    public OrderListVO getAbnormalOrderList(int customerID) {
        OrderListVO orderListVO = getAllOrderList(customerID);
        ArrayList<OrderVO> orderList = orderListVO.getOrderList();
        ArrayList<OrderVO> abnormalOrderList = new ArrayList();

        for(OrderVO orderVO : orderList){
            if(orderVO.getState() == OrderState.abnormal){
                abnormalOrderList.add(orderVO);
            }
        }

        return new OrderListVO(abnormalOrderList);
    }

    public OrderListVO getExecutedOrderListInHotel(int customerID, int hotelID) {
        OrderListVO orderListVO = getExecutedOrderList(customerID);
        ArrayList<OrderVO> orderList = orderListVO.getOrderList();
        ArrayList<OrderVO> executedOrderList = new ArrayList();

        for(OrderVO orderVO : orderList){
            if(orderVO.getHotelID() == hotelID){
                executedOrderList.add(orderVO);
            }
        }

        return new OrderListVO(executedOrderList);
    }

    public OrderListVO getRevokedOrderListInHotel(int customerID, int hotelID) {
        OrderListVO orderListVO = getRevokedOrderList(customerID);
        ArrayList<OrderVO> orderList = orderListVO.getOrderList();
        ArrayList<OrderVO> RevokedOrderList = new ArrayList();

        for(OrderVO orderVO : orderList){
            if(orderVO.getHotelID() == hotelID){
                RevokedOrderList.add(orderVO);
            }
        }

        return new OrderListVO(RevokedOrderList);
    }

    public OrderListVO getAbnormalOrderList(int customerID, int hotelID) {
        OrderListVO orderListVO = getAbnormalOrderList(customerID);
        ArrayList<OrderVO> orderList = orderListVO.getOrderList();
        ArrayList<OrderVO> abnormalOrderList = new ArrayList();

        for(OrderVO orderVO : orderList){
            if(orderVO.getHotelID() == hotelID){
                abnormalOrderList.add(orderVO);
            }
        }

        return new OrderListVO(abnormalOrderList);
    }

    public OrderListVO getAllOrderListByHotelID(int hotelID){
        return null;
    }
}
