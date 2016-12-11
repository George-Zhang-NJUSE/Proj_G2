package group2.grade15.njuse.blservice;

import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.WebPromotionListVO;
import group2.grade15.njuse.vo.WebPromotionVO;

/**
 * 网站优惠策略的层间接口
 * 供界面层的网站优惠策略界面调用
 * 职责是处理网站优惠策略相关的逻辑功能
 * @author Guo
 */
public interface WebPromotionServ {
    /**
     * 创建新的网站优惠策略
     * @param  webPromotionInfo WebPromotionVO型，界面层传递来的网站优惠策略信息的数据对象
     * @return 成功返回ResultMessage.SUCCESS
     *         失败返回ResultMessage.FAILED
     *         网络问题则返回ResultMessage.CONNECTION_EXCEPTION
     */
    public ResultMessage createWebPromotion(WebPromotionVO webPromotionInfo);

    /**
     * 获取单个网站优惠策略
     * @param  webPromotionID int型，界面层传递来的网站优惠策略ID
     * @return 成功返回对应的HotelPromotionVO
     *         失败返回null
     */
    public WebPromotionVO getWebPromotion(int webPromotionID);

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
     * 修改网站优惠策略的状态
     * @param  promotion WebPromotionVO型，界面层传递来含有状态修改后的网站优惠策略信息的数据对象
     * @return 成功返回ResultMessage.SUCCESS
     *         失败返回ResultMessage.FAILED
     *         网络问题则返回ResultMessage.CONNECTION_EXCEPTION
     */
    public ResultMessage changeState(WebPromotionVO promotion);
}
