package bl.orderbl;

import blservice.OrderServ;
import utility.OrderState;
import utility.ResultMessage;
import vo.OrderVO;

/**
 * Created by George on 2016/11/13.
 */
public class OrderController implements OrderBL, OrderServ{

    @Override
    public OrderVO getOrder(int orderId) {
        return null;
    }

    @Override
    public ResultMessage modifyState(int orderId, OrderState state) {
        return null;
    }

    @Override
    public ResultMessage addOrder(OrderVO vo) {
        return null;
    }
}
