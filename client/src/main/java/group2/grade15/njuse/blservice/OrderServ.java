package group2.grade15.njuse.blservice;

import group2.grade15.njuse.utility.OrderState;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.OrderVO;

import java.sql.Timestamp;

/**
 * 订单业务的层间接口
 * 供界面层的酒店的订单界面调用
 * 职责是处理订单相关的逻辑功能
 * @author Guo
 */
public interface OrderServ {
    /**
     * 获取单个订单
     * @param orderID int型，界面层传递来的订单ID
     * @return 成功返回对应的OrderVO
     *         失败返回null
     */
    public OrderVO getOrder(int orderID);

    /**
     * 修改订单状态
     * @param orderID int型，界面层传递来的需要修改的订单ID
     * @param state OrderState型，界面层传递来的需要修改为的订单状态
     * @return 成功返回ResultMessage.SUCCESS
     *         失败返回ResultMessage.FAILED
     *         网络问题则返回ResultMessage.CONNECTION_EXCEPTION
     */
    public ResultMessage modifyState(int orderID, OrderState state);

    /**
     * 创建订单的方法
     * 与创建订单的界面相对应
     * 创建订单界面每一次数据的改动，都应调用该方法
     * 然后该方法根据优惠政策计算出价格放入新的VO并返回界面
     * @param  orderVO OrderVO型，界面层传递来的存储有订单信息的数据对象
     * @return 成功返回OrderVO
     *         失败返回null
     */
    public OrderVO createOrder(OrderVO orderVO);

    /**
     * 确定订单的方法
     * 客户在创建订单的界面点击确定后才调用该方法
     * 该方法会将订单通过数据层持久化保存
     * @param  orderVO OrderVO型，界面层传递来的存储有订单信息的数据对象
     * @return 成功返回ResultMessage.SUCCESS
     *         失败返回ResultMessage.FAILED
     *         信用值非法返回ResultMessage.ILLEGAL
     *         网络问题则返回ResultMessage.CONNECTION_EXCEPTION
     */
    public ResultMessage saveOrder(OrderVO orderVO);

    /**
     * 更新房间的实际入住时间和实际退出时间
     * @param checkIn Date型，实际入住时间
     * @param checkOut Date型，实际退房时间
     * @param  orderID int型，界面层传来的订单ID
     * @return 成功返回ResultMessage.SUCCESS
     *         失败返回ResultMessage.FAILED
     *         网络问题则返回ResultMessage.CONNECTION_EXCEPTION
     */

    public ResultMessage updateTime(Timestamp checkIn, Timestamp checkOut, int orderID);
}
