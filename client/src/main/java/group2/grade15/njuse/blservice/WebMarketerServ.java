package group2.grade15.njuse.blservice;

import group2.grade15.njuse.utility.OrderState;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.*;

/**
 * 网站营销人员的层间接口
 * 供界面层的网站营销人员界面调用
 * 职责是处理网站营销人员相关的逻辑功能
 * @author Guo
 */
public interface WebMarketerServ {
    /**
     * 根据ID获取网站营销人员的VO
     * VO中包含网站营销人员的账户信息
     * @param webMarketerId int型，界面层传递的酒店管理人员
     * @return 成功含有酒店管理人员信息的WebAdminVO
     *         失败返回null
     */
    public WebMarketerVO getInfo(int webMarketerId);

    /**
     * 创建新的网站优惠策略
     * @param  promotionInfo WebPromotionVO型，界面层传递来的网站优惠策略信息的数据对象
     * @return 成功返回ResultMessage.SUCCESS
     *         失败返回ResultMessage.FAILED
     *         网络问题返回ResultMessage.CONNECTION_EXCEPTION
     */
    public ResultMessage createWebPromotion(WebPromotionVO promotionInfo);

    /**
     * 获取网站的优惠策略列表
     * @return 成功返回对应的HotelPromotionListVO
     *         失败返回null
     */
    public WebPromotionListVO getWebPromotionList();

    /**
     * 修改网站优惠策略的信息
     * @param  promotion WebPromotionVO型，界面层传递来修改后的网站优惠策略信息的数据对象
     * @return 成功返回ResultMessage.SUCCESS
     *         失败返回ResultMessage.FAILED
     *         网络问题则返回ResultMessage.CONNECTION_EXCEPTION
     */
    public ResultMessage modifyWebPromotion(WebPromotionVO promotion);

    /**
     * 删除网站优惠策略的状态
     * @param  webPromotionID int型，界面层传递来的需要删除的网站优惠策略的ID
     * @return 成功返回ResultMessage.SUCCESS
     *         失败返回ResultMessage.FAILED
     *         网络问题则返回ResultMessage.CONNECTION_EXCEPTION
     */
    public ResultMessage deleteWebPromotion(int webPromotionID);

    /**
     * 修改网站优惠策略的状态
     * @param  promotionVO WebPromotionVO型，界面层传递来含有状态修改后的网站优惠策略信息的数据对象
     * @return 成功返回ResultMessage.SUCCESS
     *         失败返回ResultMessage.FAILED
     *         网络问题则返回ResultMessage.CONNECTION_EXCEPTION
     */
    public ResultMessage changeState(WebPromotionVO promotionVO);

    /**
     * 获取用户的全部异常订单
     * @param customerID int型，界面层传来的用户ID
     * @return 成功返回含有全部异常订单的OrderListVO
     *         失败返回null
     */
    public OrderListVO getAbnomalOrderList(int customerID);

    /**
     * 修改用户的信用值
     * @param credit CreditVO型，界面层传来的含有信用信息的数据对象
     * @return 成功返回ResultMessage.SUCCESS
     *         失败返回ResultMessage.FAILED
     *         网络问题则返回ResultMessage.CONNECTION_EXCEPTION
     */
    public ResultMessage modifyCredit(CreditVO credit);

    /**
     * 修改订单状态
     * @param orderID int型，界面层传递来的需要修改的订单ID
     * @param state OrderState型，界面层传递来的需要修改为的订单状态
     * @return 成功返回ResultMessage.SUCCESS
     *         失败返回ResultMessage.FAILED
     *         网络问题则返回ResultMessage.CONNECTION_EXCEPTION
     */
    public ResultMessage modifyState(int orderID, OrderState state);
}

