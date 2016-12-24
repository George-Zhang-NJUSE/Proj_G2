package group2.grade15.njuse.bl.orderbl;

import group2.grade15.njuse.vo.OrderListVO;

import java.util.Date;

/**
 * Created by George on 2016/11/6.
 */
public interface OrderListBL {

    public OrderListVO getAllOrderListByCustomerID(int id);


    public OrderListVO getAbnormalOrderList(int id);

    public OrderListVO getExecutedOrderListToday(Date begin, Date end);

}
