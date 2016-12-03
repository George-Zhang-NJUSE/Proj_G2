package group2.grade15.njuse.bl.orderbl;

import group2.grade15.njuse.blservice.OrderServ;
import group2.grade15.njuse.utility.OrderState;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.OrderVO;

/**
 * Created by George on 2016/11/13.
 */
public class OrderController implements GetOrderBL, OrderServ{


    public OrderVO getOrder(int orderId) {
        return null;
    }

    public ResultMessage modifyState(int orderId, OrderState state) {
        return null;
    }

    public ResultMessage addOrder(OrderVO vo) {
        return null;
    }
}
