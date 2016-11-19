package bl.orderbl;

import blservice.OrderListServ;
import utility.IDType;
import vo.OrderListVO;

import java.util.Date;

/**
 * Created by George on 2016/11/6.
 */
public class OrderList implements OrderListBL, OrderListServ{


    @Override
    public OrderListVO getAllOrderList(int id, IDType type) {
        return null;
    }

    @Override
    public OrderListVO getExecutedOrderList(int id, IDType type) {
        return null;
    }

    @Override
    public OrderListVO getUnexecutedOrderList(int id, IDType type) {
        return null;
    }

    @Override
    public OrderListVO getNewOrderList(Date date) {
        return null;
    }

    @Override
    public OrderListVO getRevokedOrderList(int id, IDType type) {
        return null;
    }

    @Override
    public OrderListVO getAbnormalOrderList(int id, IDType type) {
        return null;
    }
}
