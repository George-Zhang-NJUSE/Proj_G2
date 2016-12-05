package group2.grade15.njuse.bl.orderbl;

import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.vo.OrderListVO;

import java.util.Date;

/**
 * Created by George on 2016/11/6.
 */
public class OrderList implements OrderListBL {

    public OrderListVO getAllOrderList(int id) {
        return null;
    }

    public OrderListVO getExecutedOrderList(int id) {
        return null;
    }

    public OrderListVO getUnexecutedOrderList(int id) {
        return null;
    }

    public OrderListVO getNewOrderList(Date date) {
        return null;
    }

    public OrderListVO getRevokedOrderList(int id) {
        return null;
    }

    public OrderListVO getAbnormalOrderList(int id) {
        return null;
    }

    public OrderListVO getExecutedOrderListInHotel(int id, int hotelID) {
        return null;
    }

    public OrderListVO getRevokedOrderListInHotel(int id, int hotelID) {
        return null;
    }

    public OrderListVO getAbnormalOrderList(int id, int hotelID) {
        return null;
    }
}
