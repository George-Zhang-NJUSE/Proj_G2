package group2.grade15.njuse.bl.orderbl;

import group2.grade15.njuse.utility.OrderState;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.OrderVO;


public interface OrderBL {
    public OrderVO getInfo(int orderID);

    public double getOriginalPrice(OrderVO orderVO);

    public ResultMessage modifyState(int orderId, OrderState state);


    public ResultMessage updateTime(java.sql.Date checkIn, java.sql.Date checkOut, int orderID);
}
