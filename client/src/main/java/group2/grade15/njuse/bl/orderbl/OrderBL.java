package group2.grade15.njuse.bl.orderbl;

import group2.grade15.njuse.vo.OrderVO;


public interface OrderBL {
    public OrderVO getInfo(int orderID);

    public double getOriginalPrice(OrderVO orderVO);
}
