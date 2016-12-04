package group2.grade15.njuse.blservice;

import group2.grade15.njuse.utility.IDType;
import group2.grade15.njuse.vo.OrderListVO;

import java.util.Date;

/**
 * Created by George on 2016/11/13.
 */
public interface OrderListServ {

    /**
     * 获取用户的全部订单
     */
    public OrderListVO getAllOrderList(int id);

    /**
     * 获取用户所有已执行订单
     */
    public OrderListVO getExecutedOrderList(int id);

    /**
     * 获取用户所有未执行订单
     */
    public OrderListVO getUnexecutedOrderList(int id);

    /**
     * 获取所有的新订单
     */
    public OrderListVO getNewOrderList(Date date);

    /**
     * 获取用户所有撤销订单
     */
    public OrderListVO getRevokedOrderList(int id);

    /**
     * 获取用户所有异常订单
     */
    public OrderListVO getAbnormalOrderList(int id);

    /**
     * 获取用户在某家酒店的所有已执行订单
     */
    public OrderListVO getExecutedOrderListInHotel(int id, int hotelID);

    /**
     * 获取用户在某家酒店的所有撤销订单
     */
    public OrderListVO getRevokedOrderListInHotel(int id, int hotelID);

    /**
     * 获取用户在某家酒店的所有异常订单
     */
    public OrderListVO getAbnormalOrderList(int id, int hotelID);

}
