package group2.grade15.njuse.bl.orderbl;

import group2.grade15.njuse.blservice.OrderListServ;
import group2.grade15.njuse.utility.IDType;
import group2.grade15.njuse.vo.OrderListVO;

import java.util.Date;

/**
 * Created by George on 2016/11/6.
 */
public class OrderList implements OrderListBL, OrderListServ{

    public OrderListVO getAllOrderList(int id, IDType type) {
        return null;
    }

    public OrderListVO getExecutedOrderList(int id, IDType type) {
        return null;
    }

    public OrderListVO getUnexecutedOrderList(int id, IDType type) {
        return null;
    }

    public OrderListVO getNewOrderList(Date date) {
        return null;
    }

    public OrderListVO getRevokedOrderList(int id, IDType type) {
        return null;
    }

    public OrderListVO getAbnormalOrderList(int id, IDType type) {
        return null;
    }
}
