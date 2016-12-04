package group2.grade15.njuse.blservice;

import group2.grade15.njuse.utility.OrderState;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.OrderVO;

public interface OrderServ {

    public OrderVO getOrder(int orderId);

    public ResultMessage modifyState(int orderID, OrderState state);

    public ResultMessage addOrder(OrderVO vo);

}
