package blservice;

import utility.OrderState;
import utility.ResultMessage;
import vo.OrderVO;

public interface OrderServ {

	public OrderVO getOrder(int orderId);

	public ResultMessage modifyState(int orderID, OrderState state);

	public ResultMessage addOrder(OrderVO vo);

}
