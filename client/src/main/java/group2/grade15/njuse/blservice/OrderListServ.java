package group2.grade15.njuse.blservice;

import group2.grade15.njuse.vo.OrderListVO;

import java.util.Date;

/**
 * Created by George on 2016/11/13.
 */
public interface OrderListServ {

    /**
     * 获取用户的全部订单
     */
    public OrderListVO getAllOrderListByCustomerID(int id);

    /**
     * 获取用户所有已执行订单
     */
    public OrderListVO getExecutedOrderListByCustomerID(int id);

    /**
     * 获取用户所有未执行订单
     */
    public OrderListVO getUnexecutedOrderListByCustomerID(int id);

    /**
     * 获取用户所有撤销订单
     */
    public OrderListVO getRevokedOrderListByCustomerID(int customerID);

    /**
     * 获取用户所有异常订单
     */
    public OrderListVO getAbnormalOrderListByCustomerID(int customerID);

    /**
     * 获取用户在某家酒店的所有已执行订单
     */
    public OrderListVO getExecutedOrderListInHotel(int customerID, int hotelID);

    /**
     * 获取用户在某家酒店的所有已执行订单
     */
    public OrderListVO getUnexecutedOrderListInHotel(int customerID, int hotelID);

    /**
     * 获取用户在某家酒店的所有撤销订单
     */
    public OrderListVO getRevokedOrderListInHotel(int customerID, int hotelID);

    /**
     * 获取用户在某家酒店的所有异常订单
     */
    public OrderListVO getAbnormalOrderList(int customerID, int hotelID);

    /**
     * 获取某家酒店的所有订单
     */
    public OrderListVO getAllOrderListByHotelID(int hotelID);
}
