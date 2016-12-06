package group2.grade15.njuse.blservice;

import group2.grade15.njuse.utility.OrderState;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.OrderVO;

public interface OrderServ {

    public OrderVO getOrder(int orderId);

    public ResultMessage modifyState(int orderID, OrderState state);

    /**
     * 与创建订单的界面相对应
     * 创建订单界面每一次数据的改动，都应调用该方法
     * 然后该方法根据优惠政策计算出价格放入新的VO并返回界面
     */
    public OrderVO createOrder(OrderVO vo);

    /**
     * 客户在创建订单的界面点击确定后才调用该方法
     * 该方法会将数据通过数据层持久化保存
     */
    public ResultMessage saveOrder(OrderVO vo);
}
