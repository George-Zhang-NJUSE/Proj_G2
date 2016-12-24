package group2.grade15.njuse.blservice;

import group2.grade15.njuse.vo.OrderListVO;

import java.util.Date;

/**
 * 订单列表的层间接口
 * 供界面层的管理人员管理订单界面调用
 * 职责是处理订单列表相关的逻辑功能
 * @author Guo
 */
public interface OrderListServ {
    /**
     * 获取用户的全部订单
     * @param customerID int型，界面层传来的用户ID
     * @return 成功返回含有全部订单的OrderListVO
     *         失败返回null
     */
    public OrderListVO getAllOrderListByCustomerID(int customerID);

    /**
     * 获取用户的全部已执行订单
     * @param customerID int型，界面层传来的用户ID
     * @return 成功返回含有全部已执行订单的OrderListVO
     *         失败返回null
     */
    public OrderListVO getExecutedOrderListByCustomerID(int customerID);

    /**
     * 获取用户的全部未执行订单
     * @param customerID int型，界面层传来的用户ID
     * @return 成功返回含有全部未执行订单的OrderListVO
     *         失败返回null
     */
    public OrderListVO getUnexecutedOrderListByCustomerID(int customerID);

    /**
     * 获取用户的全部撤销订单
     * @param customerID int型，界面层传来的用户ID
     * @return 成功返回含有全部撤销订单的OrderListVO
     *         失败返回null
     */
    public OrderListVO getRevokedOrderListByCustomerID(int customerID);

    /**
     * 获取用户的全部异常订单
     * @param customerID int型，界面层传来的用户ID
     * @return 成功返回含有全部异常订单的OrderListVO
     *         失败返回null
     */
    public OrderListVO getAbnormalOrderListByCustomerID(int customerID);

    /**
     ** 获取用户在某家酒店的所有已执行订单
     * @param customerID int型，界面层传来的用户ID
     * @param hotelID int型，界面层传来的酒店ID
     * @return 成功返回该客户在对应酒店的含有全部已执行订单的OrderListVO
     *         失败返回null
     */
    public OrderListVO getExecutedOrderListInHotel(int customerID, int hotelID);

    /**
     ** 获取用户在某家酒店的所有未执行订单
     * @param customerID int型，界面层传来的用户ID
     * @param hotelID int型，界面层传来的酒店ID
     * @return 成功返回该客户在对应酒店的含有全部未执行订单的OrderListVO
     *         失败返回null
     */
    public OrderListVO getUnexecutedOrderListInHotel(int customerID, int hotelID);

    /**
     ** 获取用户在某家酒店的所有撤销订单
     * @param customerID int型，界面层传来的用户ID
     * @param hotelID int型，界面层传来的酒店ID
     * @return 成功返回该客户在对应酒店的含有全部撤销订单的OrderListVO
     *         失败返回null
     */
    public OrderListVO getRevokedOrderListInHotel(int customerID, int hotelID);

    /**
     ** 获取用户在某家酒店的所有异常订单
     * @param customerID int型，界面层传来的用户ID
     * @param hotelID int型，界面层传来的酒店ID
     * @return 成功返回该客户在对应酒店的含有全部异常订单的OrderListVO
     *         失败返回null
     */
    public OrderListVO getAbnormalOrderList(int customerID, int hotelID);

    /**
     ** 获取某家酒店的所有订单
     * @param hotelID int型，界面层传来的酒店ID
     * @return 成功返回含有对应酒店的全部订单的OrderListVO
     *         失败返回null
     */
    public OrderListVO getAllOrderListByHotelID(int hotelID);

    /**
     ** 获取该网站的所有异常订单
     * @return 成功返回该网站的含有全部异常订单的OrderListVO
     *         失败返回null
     */
    public OrderListVO getAbnormalOrderList();

    /**
     ** 获取该网站的当日所有未执行订单
     * @param begin Date型，当天时间的起始点
     * @param end Date型，当天时间的结束点
     * @return 成功返回该网站的含有当天全部未执行订单的OrderListVO
     *         失败返回null
     */
    public OrderListVO getExecutedOrderListToday(Date begin, Date end);
}
