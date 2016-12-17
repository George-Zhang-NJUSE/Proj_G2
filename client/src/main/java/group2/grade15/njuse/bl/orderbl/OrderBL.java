package group2.grade15.njuse.bl.orderbl;

import group2.grade15.njuse.utility.OrderState;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.OrderVO;

import java.sql.Timestamp;


public interface OrderBL {
    public OrderVO getInfo(int orderID);

    public double getOriginalPrice(OrderVO orderVO);

    public ResultMessage modifyState(int orderId, OrderState state);

    public ResultMessage updateTime(Timestamp checkIn, Timestamp checkOut, int orderID);
}
